package com.lgcns.erp.hr.controller;

import com.lgcns.erp.hr.viewModel.WorkloadViewModels.CalendarReturningModel;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.DbContext.WorkloadServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rafatdin on 31.10.2016.
 */
@Controller
public class WorkloadController {

    @RequestMapping(value = "/Workload", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Calendar(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("workload/calendar");

        int userId = UserService.getUserByUsername(principal.getName()).getId();

        CalendarReturningModel model = new CalendarReturningModel();
        model.setMonday(getMonday(new Date()));
        model.setProjects(WorkloadServices.getUsersAllProjects(userId, model.getMonday(), addDays(model.getMonday(), 7)));
        model.setWorkloads(WorkloadServices.getWorkloadsByUserid(userId, model.getMonday(), addDays(model.getMonday(), 7)));

        mav.addObject("model", model);
        mav.addObject("userRole", UserService.getUserByUsername(principal.getName()));
        return mav;
    }

    private Date getMonday(Date today) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return c.getTime();
    }

    private Date addDays(Date day, int addDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.add(Calendar.DATE, addDays);
        return c.getTime();
    }
}
