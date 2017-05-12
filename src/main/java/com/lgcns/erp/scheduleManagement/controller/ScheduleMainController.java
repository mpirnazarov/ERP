package com.lgcns.erp.scheduleManagement.controller;

import com.lgcns.erp.scheduleManagement.enums.ActionTypeId;
import com.lgcns.erp.scheduleManagement.enums.ScheduleEventInvolvement;
import com.lgcns.erp.scheduleManagement.mapper.ScheduleMainMapper;
import com.lgcns.erp.scheduleManagement.service.ScheduleMainService;
import com.lgcns.erp.scheduleManagement.util.ScheduleMainControllerUtil;
import com.lgcns.erp.scheduleManagement.util.email.EmailUtil;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import freemarker.template.SimpleDate;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DS on 05.04.2017.
 */

@RequestMapping("/ScheduleManagement")
@Controller
public class ScheduleMainController {
   int[] participantsGlobal = null;
   int[] referencesGlobal = null;

    @Value("${message.create.to.author}")
    private String createToAuthor;

    @Value("${message.create.to.participant}")
    private String createToParticipant;

    @Value("${message.create.to.reference}")
    private String createToReference;

    @Autowired
    ScheduleMainService service;


    @RequestMapping(value = "/main")
    public ModelAndView getMainSchedule(Principal principal){
        String temp = String.format(createToAuthor, "name", "title");
        System.out.println(temp);
        ModelAndView mav = new ModelAndView("scheduleManagement/main/scheduleIndex");
        int userId = UserService.getIdByUsername(principal.getName());
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        mav.addObject("UserslistJson", WorkflowService.getUsersJson(principal));
        mav.addObject("scheduleVM", new ScheduleVM());
        mav.addObject("userId", userId);
        return mav;
    }

    /**
     * This method is responsible for creation and update of schedule entities. It decides to use create or update after checking ActionTypeId.
     * @param scheduleVM
     * @param principal
     * @return Redirects to Main Schedule page after finishing the processes
     * @throws IOException
     */
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String create(@ModelAttribute ScheduleVM scheduleVM, BindingResult result, Principal principal) throws IOException {
        scheduleVM.setAuthorId(UserService.getIdByUsername(principal.getName()));
        scheduleVM.setParticipants(participantsGlobal);
        scheduleVM.setReferences(referencesGlobal);
        /* Write into database schedule data */
        int scheduleId = service.insertSchedule(ScheduleMainMapper.mapScheduleFromVMToEntity(scheduleVM));
        if (participantsGlobal != null) {
            for (int participant : scheduleVM.getParticipants()) {
                service.insertParticipants(ScheduleMainMapper.mapParticipantInSchedule(scheduleId, participant));
            }
        }
        if (referencesGlobal != null) {
            for (int reference : scheduleVM.getReferences()) {
                service.insertReference(ScheduleMainMapper.mapReferenceInSchedule(scheduleId, reference));
            }
        }
        ScheduleMainControllerUtil.uploadFile(scheduleVM, scheduleId, service);

        int[] author = new int[1];
        author[0] = UserService.getIdByUsername(principal.getName());

        EmailUtil.sendEmailToAuthor(scheduleId, author, ActionTypeId.Create.getValue(), createToAuthor);
        EmailUtil.sendEmailToParticipants(scheduleId, participantsGlobal, ActionTypeId.Create.getValue(), createToParticipant);
        EmailUtil.sendEmailToReferences(scheduleId, referencesGlobal, ActionTypeId.Create.getValue(), createToReference);


        return "redirect: /ScheduleManagement/main";
    }

    /**
     * Returns list of schedules for the requested week
     * @param principal
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/api/scheduleList", method = RequestMethod.POST)
    public @ResponseBody List<HashMap<String, Object>> getAllSchedules(Principal principal, @RequestParam("start") String start, @RequestParam("end") String end) throws ParseException {
        int userId = UserService.getIdByUsername(principal.getName());

        List<ScheduleVM> scheduleVMList = service.getScheduleList(convertStringToTimeStamp(start), convertStringToTimeStamp(minusOneDay(end)), userId);
        List<HashMap<String, Object>> weeklySchedule = ScheduleMainControllerUtil.putScheduleEventsToMap(scheduleVMList);

        return weeklySchedule;
    }
    /**
     * Converts Date type to TimeStamp
     * @param date
     * @return Timestamp
     */
    private Timestamp convertStringToTimeStamp(String date){
        Timestamp timestamp = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            e.printStackTrace();
        }

        return timestamp;
    }

    /**
     * Subtracts one day from the given date and returns
     * @param date
     * @return parsed date in String type
     * @throws ParseException
     */
    private String minusOneDay(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parsedDate);
        calendar.add(Calendar.DATE, -1);
        parsedDate = calendar.getTime();

        return dateFormat.format(parsedDate);
    }

    /**
     * This method consumes participants and references sent from client side, and assigns them to global variables
     * @param participants
     * @param references
     * @return Json of participants and references
     */
    @RequestMapping(value = "/ScheduleMembersAjax", method = RequestMethod.POST)
    public @ResponseBody
    int[] ScheduleMembersPostAjax(@RequestParam("participants") int[] participants, @RequestParam("references") int[] references){
        participantsGlobal = participants;
        referencesGlobal = references;

        return participants;
    }

    /*@RequestMapping(value = "/Filter", method = RequestMethod.POST)
    public @ResponseBody List<HashMap<String, Object>> filter(@RequestParam("eventInvolvement")int eventInvolvement,
                                                              @RequestParam("start") String start,
                                                              @RequestParam("end") String end,
                                                              Principal principal) throws ParseException {
        int userId = UserService.getIdByUsername(principal.getName());
        List<ScheduleVM> scheduleVMList;
        if (eventInvolvement== ScheduleEventInvolvement.Author.getValue())
            scheduleVMList = service.getSchedulesWhereUserIsAuthor(convertStringToTimeStamp(start), convertStringToTimeStamp(minusOneDay(end)), userId);
        else if (eventInvolvement==ScheduleEventInvolvement.Participant.getValue())
            scheduleVMList = service.getSchedulesWhereUserIsParticipant(convertStringToTimeStamp(start), convertStringToTimeStamp(minusOneDay(end)), userId);
        else
            scheduleVMList = service.getSchedulesWhereUserIsReference(convertStringToTimeStamp(start), convertStringToTimeStamp(minusOneDay(end)), userId);

        List<HashMap<String, Object>> authorsSchedule = ScheduleMainControllerUtil.putScheduleEventsToMap(scheduleVMList);

        return authorsSchedule;
    }*/

    @RequestMapping(value = "/Filter", method = RequestMethod.POST)
    public @ResponseBody List<HashMap<String, Object>> filter(@RequestParam("userId")int userId,
                                                              @RequestParam("author") boolean isAuthor,
                                                              @RequestParam("participant") boolean isParticipant,
                                                              @RequestParam("reference")boolean isReference,
                                                              @RequestParam("start") String start,
                                                              @RequestParam("end") String end,
                                                              Principal principal) throws ParseException {
        List<ScheduleVM> scheduleVMList = null;
        if (isAuthor)
            scheduleVMList.addAll(service.
                    getSchedulesWhereUserIsAuthor(convertStringToTimeStamp(start), convertStringToTimeStamp(minusOneDay(end)), userId));
        else if (isParticipant)
            scheduleVMList.addAll(service.
                    getSchedulesWhereUserIsParticipant(convertStringToTimeStamp(start), convertStringToTimeStamp(minusOneDay(end)), userId));
        else if (isReference)
            scheduleVMList.addAll(service.
                    getSchedulesWhereUserIsReference(convertStringToTimeStamp(start), convertStringToTimeStamp(minusOneDay(end)), userId));

        List<HashMap<String, Object>> authorsSchedule = ScheduleMainControllerUtil.putScheduleEventsToMap(scheduleVMList);

        return authorsSchedule;
    }
}
