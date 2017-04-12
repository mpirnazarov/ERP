package com.lgcns.erp.scheduleManagement.controller;

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

    @RequestMapping(value = "/api/scheduleList")
    public @ResponseBody List<HashMap<String, Object>> getAllSchedules(){
        List<ScheduleVM> scheduleVMList = service.getScheduleList();
        List<HashMap<String, Object>> fullSchedule = ScheduleMainControllerUtil.putScheduleEventsToMap(scheduleVMList);

        return fullSchedule;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(){

        ModelAndView mav = new ModelAndView("scheduleManagement/create/scheduleCreate");
        ScheduleVM scheduleVM = new ScheduleVM();
        scheduleVM.setDateFrom(startDate);
        scheduleVM.setDateTo(endDate);
        mav.addObject("schedule",scheduleVM);


        return mav;
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST, params = "Submit")
    public String create(@ModelAttribute ScheduleVM scheduleVM, Principal principal) throws IOException {
        scheduleVM.setAuthorId(UserService.getIdByUsername(principal.getName()));
        scheduleVM.setParticipants(participantsGlobal);
        scheduleVM.setReferences(referencesGlobal);
        /* Write into database schedule data */
        int scheduleId = service.insertSchedule(ScheduleMainMapper.mapScheduleFromVMToEntity(scheduleVM));
        if (participantsGlobal != null){
            for(int participant : scheduleVM.getParticipants()) {
                service.insertParticipants(ScheduleMainMapper.mapParticipantInSchedule(scheduleId, participant));
            }
        }

        if (referencesGlobal!=null){
            for(int reference : scheduleVM.getReferences()) {
                service.insertReference(ScheduleMainMapper.mapReferenceInSchedule(scheduleId, reference));
            }
        }

        ScheduleMainControllerUtil.uploadFile(scheduleVM, scheduleId, service);
        return "redirect: /ScheduleManagement/main";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, params = "Save")
    public String save(@ModelAttribute ScheduleVM scheduleVM, BindingResult result, Principal principal){

        System.out.println(scheduleVM);
        return "redirect:/ScheduleManagement/main";
    }

    @RequestMapping(value = "/ScheduleMembersAjax", method = RequestMethod.POST)
    public @ResponseBody
    int[] ScheduleMembersPostAjax(@RequestParam("participants") int[] participants, @RequestParam("references") int[] references){

        participantsGlobal = participants;
        referencesGlobal = references;

        return participants;
    }
}
