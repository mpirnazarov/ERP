package com.lgcns.erp.tapps.controller;


import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.model.UserInfo;
import com.lgcns.erp.tapps.viewModel.HR.*;
import com.lgcns.erp.tapps.viewModel.LoginViewModel;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.RegistrationViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.AppointmentrecViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.EduViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.JobexpViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.TrainViewModel;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Dell on 26-Oct-16.
 */
@Controller
public class HrController {

    @RequestMapping (value = "/Hr/Profile", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Hrprofile(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/IndexHR");
        HrprofileViewModel hrprofileViewModel = new HrprofileViewModel();
        mav.addObject("hrprofileVM", hrprofileViewModel);
        return mav;
    }

    @RequestMapping (value = "/Hr/Profile/Appointment", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrAppointmentrec(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/AppointmentRec");
        HrAppointmentrecViewModel hrappointmentrecViewModel = new HrAppointmentrecViewModel();
        mav.addObject("hrappointmentrecVM", hrappointmentrecViewModel);
        return mav;
    }
    @RequestMapping (value = "/Hr/Profile/Edu", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrEdu(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/EducationCer");
        HrEduViewModel hreduViewModel = new HrEduViewModel();
        mav.addObject("hreduVM", hreduViewModel);
        return mav;
    }
    @RequestMapping (value = "/Hr/Profile/Jobexp", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrJobexp(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/JobExp");
        HrJobexpViewModel hrjobexpViewModel = new HrJobexpViewModel();
        mav.addObject("hrjobexpVM", hrjobexpViewModel);
        return mav;
    }
    @RequestMapping (value = "/Hr/Profile/Train", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrTrain(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/TrainingRec");
        HrTrainViewModel hrtrainViewModel = new HrTrainViewModel();
        mav.addObject("hrtrainVM", hrtrainViewModel);
        return mav;
    }
}
