package com.lgcns.erp.tapps.controller;


import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.model.UserInfo;
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
 * Created by Rafatdin on 15.09.2016.
 */

@Controller
public class UserController {


    @RequestMapping(value = "/User/Login", method = RequestMethod.GET)
    public ModelAndView Login(){

        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");

        return model;
    }

    @RequestMapping(value = "/User/LoginAjax", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public JSONObject LoginAjax(@RequestBody String json) throws IOException {
        UserInfo curUser = new UserInfo();
        ObjectMapper mapper = new ObjectMapper();
        LoginViewModel requesValue = mapper.readValue(json, LoginViewModel.class);
        curUser.setUsername(requesValue.getUserName().trim().toLowerCase());
        curUser.setPassword(requesValue.getPassword());

        JSONObject response = new JSONObject();

        if(UserService.Authenticate(curUser)==1)
        {
            response.put("Url", "/");
        }

        return response;
    }

    @RequestMapping (value = "/User/Register", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Register(@ModelAttribute("registrationVM") RegistrationViewModel registrationViewModel ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/register");
        mav.addObject("registrationVM", registrationViewModel);
        Map<Integer, String> heads = new LinkedHashMap<Integer, String>();      //return data

        Collection<UsersEntity> usersWithInfo = UserService.getDirectHeads();   //Getting list of users with localization info
        Iterator itr = usersWithInfo.iterator();                                //info for iterator
        UsersEntity userTemp;                                                   //info for iterator
        UserLocalizationsEntity userLocTemp;                                    //info for iterator

        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            userTemp =  (UsersEntity)obj[0];
            userLocTemp =  (UserLocalizationsEntity)obj[1];
            if(userLocTemp != null)
                heads.put(userTemp.getId(),userLocTemp.getFirstName() + " " + userLocTemp.getFirstName());
            else
                heads.put(userTemp.getId(),userTemp.getUserName());
        }
        //mav.addObject("heads", UserService.getDirectHeads());
        mav.addObject("heads", heads);
        return mav;
    }

    @RequestMapping (value = "/User/Register", method = RequestMethod.POST)
    @ResponseBody public ModelAndView RegisterPost(@ModelAttribute("registrationVM") RegistrationViewModel registrationViewModel ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/register");

        return mav;
    }

    @RequestMapping (value = "/User/Profile", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Profile(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/IndexUser");
        ProfileViewModel profileViewModel = new ProfileViewModel();
        mav.addObject("profileVM", profileViewModel);
        return mav;
    }
    @RequestMapping (value = "/User/Profile/Appointment", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Appointmentrec(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/AppointmentRec");
        AppointmentrecViewModel appointmentrecViewModel = new AppointmentrecViewModel();
        mav.addObject("appointmentrecVM", appointmentrecViewModel);
        return mav;
    }
    @RequestMapping (value = "/User/Profile/Edu", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Edu(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/EducationCer");
        EduViewModel eduViewModel = new EduViewModel();
        mav.addObject("eduVM", eduViewModel);
        return mav;
    }
    @RequestMapping (value = "/User/Profile/Jobexp", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Jobexp(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/JobExp");
        JobexpViewModel jobexpViewModel = new JobexpViewModel();
        mav.addObject("jobexpVM", jobexpViewModel);
        return mav;
    }
    @RequestMapping (value = "/User/Profile/Train", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Train(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/TrainingRec");
        TrainViewModel trainViewModel = new TrainViewModel();
        mav.addObject("trainVM", trainViewModel);
        return mav;
    }
}
