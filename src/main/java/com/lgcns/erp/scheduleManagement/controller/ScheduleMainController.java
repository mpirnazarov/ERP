package com.lgcns.erp.scheduleManagement.controller;

import com.lgcns.erp.scheduleManagement.service.ScheduleMainService;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by DS on 05.04.2017.
 */

@RequestMapping("/ScheduleManagement")
@Controller
public class ScheduleMainController {
    @Autowired
    ScheduleMainService service;

    @RequestMapping(value = "/main")
    public String getMainSchedule(){
        return "scheduleManagement/main/scheduleIndex";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mav = new ModelAndView("scheduleManagement/create/scheduleCreate");
        ScheduleVM scheduleVM = new ScheduleVM();
        mav.addObject("schedule", scheduleVM);

        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, params = "Submit")
    public String submit(@ModelAttribute ScheduleVM scheduleVM, BindingResult result, Principal principal){
        service.getScheduleList();
        System.out.println(scheduleVM);
        return "redirect:/ScheduleManagement/main";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, params = "Save")
    public String save(@ModelAttribute ScheduleVM scheduleVM, BindingResult result, Principal principal){

        System.out.println(scheduleVM);
        return "redirect:/ScheduleManagement/main";
    }
}
