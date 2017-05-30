package com.lgcns.erp.attendanceManagement.controller.daily;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Muslimbek on 5/30/2017.
 */
@Controller
@RequestMapping(value = "/Attendance")
public class AttendanceDailyController {

    @RequestMapping(value = "/Daily", method = RequestMethod.GET)
    public ModelAndView WorkflowGET(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("attendance/newForm/businessTripForm");
        return mav;
    }

}
