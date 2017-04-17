package com.lgcns.erp.scheduleManagement.controller;

import com.lgcns.erp.scheduleManagement.enums.ActionTypeId;
import com.lgcns.erp.scheduleManagement.mapper.ScheduleMainMapper;
import com.lgcns.erp.scheduleManagement.service.ScheduleMainService;
import com.lgcns.erp.scheduleManagement.util.ScheduleMainControllerUtil;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import freemarker.template.SimpleDate;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Date startDate = null, endDate = null;

    @Autowired
    ScheduleMainService service;

    @RequestMapping(value = "/main")
    public ModelAndView getMainSchedule(Principal principal){

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
     * This method is called after Main Schedule page is opened
     * @return  List of schedules mapped
     * @throws ParseException
     */
    @RequestMapping(value = "/api/scheduleList", method = RequestMethod.POST)
    public @ResponseBody List<HashMap<String, Object>> getAllSchedules(Principal principal, @RequestParam Date from, @RequestParam Date to) throws ParseException {

        System.out.println(from+" "+to);

        List<ScheduleVM> scheduleVMList = service.getScheduleList();
        List<HashMap<String, Object>> fullSchedule = ScheduleMainControllerUtil.putScheduleEventsToMap(scheduleVMList);

        return fullSchedule;
    }

    /**
     * This method is responsible for creation and update of schedule entities. It decides to use create or update after checking ActionTypeId.
     * ToDo logically finalize update of participants, references, Attachments
     * @param scheduleVM
     * @param principal
     * @return Redirects to Main Schedule page after finishing the processes
     * @throws IOException
     */
    @RequestMapping(value = "/main", method = RequestMethod.POST, params = "Submit")
    public String create(@ModelAttribute ScheduleVM scheduleVM, Principal principal) throws IOException {
        scheduleVM.setAuthorId(UserService.getIdByUsername(principal.getName()));
        scheduleVM.setParticipants(participantsGlobal);
        scheduleVM.setReferences(referencesGlobal);

        if (scheduleVM.getActionTypeId() == ActionTypeId.Update.getValue())
            service.updateSchedule(ScheduleMainMapper.mapScheduleFromVMToEntity(scheduleVM));
        else {
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
        }

        return "redirect: /ScheduleManagement/main";
    }


    /**
     * ToDo identify needed parameters and return type based on front end needs and capabilities
     */
    @RequestMapping(value = "/ParticipantDecision")
    public void decide(){

    }

    /**
     * This method consumes participants and references sent from client side, and assigns them to global variables
     * @param participants
     * @param references
     * @return Jason of participants and references
     */
    @RequestMapping(value = "/ScheduleMembersAjax", method = RequestMethod.POST)
    public @ResponseBody
    int[] ScheduleMembersPostAjax(@RequestParam("participants") int[] participants, @RequestParam("references") int[] references){

        participantsGlobal = participants;
        referencesGlobal = references;

        return participants;
    }
}
