package com.lgcns.erp.scheduleManagement.controller;

import com.lgcns.erp.scheduleManagement.mapper.ScheduleMainMapper;
import com.lgcns.erp.scheduleManagement.service.ScheduleMainService;
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

        for(int participant : scheduleVM.getParticipants()) {
            service.insertParticipants(ScheduleMainMapper.mapParticipantInSchedule(scheduleId, participant));
        }
        for(int reference : scheduleVM.getReferences()) {
            service.insertReference(ScheduleMainMapper.mapReferenceInSchedule(scheduleId, reference));
        }
        /* File upload */
        MultipartFile[] multipartFiles=null;
        if(!scheduleVM.getFile()[0].isEmpty()) {
            multipartFiles = scheduleVM.getFile();

            // Uploading files attached to C:/files/documents/workflow. Create folder if doesn't exist.
            File f = new File("C:/files/documents/schedule/" + scheduleVM.getScheduleId()+"/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/schedule/" + scheduleId + "/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }
        if(!scheduleVM.getFile()[0].isEmpty()) {
            for(MultipartFile multipartFile: scheduleVM.getFile()){
                service.insertAttachment(ScheduleMainMapper.mapAttachmentInSchedule(scheduleId, multipartFile));
            }
        }

        return "redirect: /ScheduleManagement/main";
    }

    /*@RequestMapping(value = "/create", method = RequestMethod.POST, params = "Submit")
    public String submit(@ModelAttribute ScheduleVM scheduleVM, BindingResult result, Principal principal){

        System.out.println(scheduleVM);
        return "redirect:/ScheduleManagement/main";
    }*/

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
