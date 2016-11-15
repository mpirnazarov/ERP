package com.lgcns.erp.tapps.controller;


import com.lgcns.erp.tapps.viewModel.HR.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping (value = "/Hr/Profile/Userslist", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrUserslist(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/Userslist");
        HrUserslistViewModel hrUserslistViewModel = new HrUserslistViewModel();
        mav.addObject("hrUserslistVM", hrUserslistViewModel);
        return mav;
    }
    @RequestMapping(value = "/Hr/Profile/Docs", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Docs() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/Docs");
        DocsViewModel docsViewModel = new DocsViewModel();
        mav.addObject("docsVM", docsViewModel);
        return mav;
    }
    @RequestMapping (value = "/Hr/edit/geninfo", method = RequestMethod.GET)
    @ResponseBody public ModelAndView GenInfo(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/geninfo");
        HrJobexpViewModel hrjobexpViewModel = new HrJobexpViewModel();
        mav.addObject("hrjobexpVM", hrjobexpViewModel);
        return mav;
    }
}
