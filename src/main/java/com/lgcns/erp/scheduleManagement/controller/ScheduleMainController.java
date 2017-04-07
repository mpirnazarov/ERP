package com.lgcns.erp.scheduleManagement.controller;

import com.lgcns.erp.scheduleManagement.service.ScheduleMainService;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
import freemarker.template.SimpleDate;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam String start,
                               @RequestParam String end){
        start = start.replace('T', ' ');
        end = end.replace('T', ' ');
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startDate = null, endDate = null;
        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView("scheduleManagement/create/scheduleCreate");
        ScheduleVM scheduleVM = new ScheduleVM();
        mav.addObject("schedule", scheduleVM);



        if(startDate != null && endDate !=null) {

        }else{
            mav = new ModelAndView("scheduleManagement/create/error");
            mav.addObject("message", "Wrong format!!!!");
        }

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
