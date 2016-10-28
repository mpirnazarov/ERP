package com.lgcns.erp.tapps.controller;


import com.lgcns.erp.tapps.DAO.UserProfileDAO;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.mapper.UserMapper;
import com.lgcns.erp.tapps.model.DbEntities.*;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.memory.UserMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
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
    @ResponseBody public ModelAndView Register(/*@ModelAttribute("registrationVM") RegistrationViewModel registrationViewModel */){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/register");

        RegistrationViewModel rvm = new RegistrationViewModel(UserService.getLanguageIdAndName());
        mav.addObject("registrationVM", rvm);
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
        mav.addObject("heads", heads);


        Map<Integer, String> departments = new LinkedHashMap<Integer, String>();        //Getting departments and adding to model and view
        for (DepartmentLocalizationsEntity depLoc : UserService.getDepartments()){
            departments.put(depLoc.getDepartmentId(),depLoc.getName());
        }
        mav.addObject("departments", departments);

        Map<Integer, String> statuses = new LinkedHashMap<Integer, String>();           //Getting statuses and adding to model and view
        for (StatusLocalizationsEntity statLoc : UserService.getStatuses()){
            statuses.put(statLoc.getStatusId(),statLoc.getName());
        }
        mav.addObject("statuses", statuses);

        return mav;
    }

    @RequestMapping (value = "/User/Register", method = RequestMethod.POST)
    @ResponseBody public ModelAndView RegisterPost(@Valid RegistrationViewModel registrationViewModel, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/register");
        mav.addObject("registrationVM", registrationViewModel);
        if(bindingResult.hasErrors()){
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
            mav.addObject("heads", heads);


            Map<Integer, String> departments = new LinkedHashMap<Integer, String>();        //Getting departments and adding to model and view
            for (DepartmentLocalizationsEntity depLoc : UserService.getDepartments()){
                departments.put(depLoc.getDepartmentId(),depLoc.getName());
            }
            mav.addObject("departments", departments);

            Map<Integer, String> statuses = new LinkedHashMap<Integer, String>();           //Getting statuses and adding to model and view
            for (StatusLocalizationsEntity statLoc : UserService.getStatuses()){
                statuses.put(statLoc.getStatusId(),statLoc.getName());
            }
            mav.addObject("statuses", statuses);
            return mav;
        }

        //adding user and userLocalization info into DB
        int userId = UserService.insertUser(UserMapper.mapRegModelToUserInfo(registrationViewModel));
        UserService.insertUserLoc(UserMapper.mapRegModelToUserLocInfo(registrationViewModel,userId));


        mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/Userslist");
        return mav;
    }

    @RequestMapping (value = "/User/Profile", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Profile(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/IndexUser");


        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-module.xml");
        UserProfileDAO userProfileDAO = (UserProfileDAO) context.getBean("userProfileDAO");
        ProfileViewModel userProfile = userProfileDAO.findByUserName(principal.getName());
        try {
            System.out.println(userProfile.toString());
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }




        mav.addObject("userProfile", userProfile);



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
