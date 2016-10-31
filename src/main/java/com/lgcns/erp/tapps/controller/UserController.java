package com.lgcns.erp.tapps.controller;


//import com.lgcns.erp.tapps.DAO.UserProfileDAO;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.Enums.Appoint;
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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView Login() {

        ModelAndView model = new ModelAndView();
        model.setViewName("login_new");

        return model;
    }

    @RequestMapping(value = "/User/LoginAjax", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject LoginAjax(@RequestBody String json) throws IOException {
        UserInfo curUser = new UserInfo();
        ObjectMapper mapper = new ObjectMapper();
        LoginViewModel requesValue = mapper.readValue(json, LoginViewModel.class);
        curUser.setUsername(requesValue.getUserName().trim().toLowerCase());
        curUser.setPassword(requesValue.getPassword());

        JSONObject response = new JSONObject();

        if (UserService.Authenticate(curUser) == 1) {
            response.put("Url", "/");
        }

        return response;
    }

    @RequestMapping(value = "/User/Register", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Register(/*@ModelAttribute("registrationVM") RegistrationViewModel registrationViewModel */) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/register");

        RegistrationViewModel rvm = new RegistrationViewModel(UserService.getLanguageIdAndName());
        mav.addObject("registrationVM", rvm);

        Map<Integer, String> heads = getDirectHeadIdAndName();
        mav.addObject("heads", heads);

        Map<Integer, String> departments = getDepartmentsIdAndName();
        mav.addObject("departments", departments);

        Map<Integer, String> statuses = getStatusesIdAndName();
        mav.addObject("statuses", statuses);

        Map<Integer, String> roles = getRolesIdAndName();
        mav.addObject("roles", roles);

        return mav;
    }



    @RequestMapping(value = "/User/Register", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView RegisterPost(@Valid @ModelAttribute("registrationVM") RegistrationViewModel registrationViewModel, BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/register");
        mav.addObject("registrationVM", registrationViewModel);
        if (bindingResult.hasErrors()) {
            mav.addObject("heads", getDirectHeadIdAndName());
            mav.addObject("departments", getDepartmentsIdAndName());
            mav.addObject("statuses", getStatusesIdAndName());
            mav.addObject("org.springframework.validation.BindingResult.registrationVM", bindingResult);
            return mav;
        }

        //adding user and userLocalization info into DB
        int userId = UserService.insertUser(UserMapper.mapRegModelToUserInfo(registrationViewModel));
        UserService.insertUserLoc(UserMapper.mapRegModelToUserLocInfo(registrationViewModel, userId));


        mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/Userslist");
        return mav;
    }

    @RequestMapping (value = "/User/Profile", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Profile(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/IndexUser");


        ProfileViewModel userProfile = getProfileByUsername(principal); //userProfileDAO.findByUserName(principal.getName());
        try {
            System.out.println(userProfile.toString());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }




        mav.addObject("userProfile", userProfile);



        return mav;
    }

    @RequestMapping(value = "/User/Profile/Appointment", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Appointmentrec() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/AppointmentRec");
        AppointmentrecViewModel appointmentrecViewModel = new AppointmentrecViewModel();

        mav.addObject("appointmentrecVM", appointmentrecViewModel);
        return mav;
    }


    @RequestMapping(value = "/User/Profile/Edu", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Edu() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/EducationCer");
        EduViewModel eduViewModel = new EduViewModel();
        mav.addObject("eduVM", eduViewModel);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Jobexp", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Jobexp() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/JobExp");
        JobexpViewModel jobexpViewModel = new JobexpViewModel();
        mav.addObject("jobexpVM", jobexpViewModel);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Train", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Train() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/TrainingRec");
        TrainViewModel trainViewModel = new TrainViewModel();
        mav.addObject("trainVM", trainViewModel);
        return mav;
    }

    private Map<Integer, String> getDirectHeadIdAndName() {
        Map<Integer, String> heads = new LinkedHashMap<Integer, String>();
        Collection<UsersEntity> usersWithInfo = UserService.getDirectHeads();   //Getting list of users with localization info
        Iterator itr = usersWithInfo.iterator();                                //info for iterator
        UsersEntity userTemp;                                                   //info for iterator
        UserLocalizationsEntity userLocTemp;                                    //info for iterator

        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            userTemp = (UsersEntity) obj[0];
            userLocTemp = (UserLocalizationsEntity) obj[1];
            if (userLocTemp != null)
                heads.put(userTemp.getId(), userLocTemp.getFirstName() + " " + userLocTemp.getFirstName());
            else
                heads.put(userTemp.getId(), userTemp.getUserName());
        }

        return heads;
    }

    private Map<Integer, String> getDepartmentsIdAndName() {
        Map<Integer, String> departments = new LinkedHashMap<Integer, String>();        //Getting departments and adding to model and view
        for (DepartmentLocalizationsEntity depLoc : UserService.getDepartments()) {
            departments.put(depLoc.getDepartmentId(), depLoc.getName());
        }
        return departments;
    }

    private Map<Integer, String> getStatusesIdAndName() {
        Map<Integer, String> statuses = new LinkedHashMap<Integer, String>();           //Getting statuses and adding to model and view
        for (StatusLocalizationsEntity statLoc : UserService.getStatuses()) {
            statuses.put(statLoc.getStatusId(), statLoc.getName());
        }
        return statuses;
    }

    private Map<Integer, String> getRolesIdAndName() {
        Map<Integer, String> roles = new LinkedHashMap<Integer, String>();           //Getting statuses and adding to model and view
        for (RoleLocalizationsEntity roleLoc : UserService.getRolesLoc()) {
            roles.put(roleLoc.getRoleId(), roleLoc.getName());
        }
        return roles;
    }

    private ProfileViewModel getProfileByUsername(Principal principal){
        ProfileViewModel returning = new ProfileViewModel();

        // Getting data from users db
        UsersEntity user = UserService.getUserByUsername(principal.getName());

        // Getting its localization data
        List<UserLocalizationsEntity> userLocalizationsEntities = UserService.getUserLocByUserId(user.getId());
        for (UserLocalizationsEntity ul:
            userLocalizationsEntities ) {
            returning.addData1(user.getId(), ul.getFirstName(), ul.getLastName(), ul.getFatherName(), ul.getAddress(), ul.getLanguageId());
        }

        //Getting department name
        returning.setDepartment(UserService.getDepartments().get(3).getName());

        //Getting Position from user_in_roles
        returning.setPosition(UserService.getRoleLoc(user).getName());

        //Getting Joint Type
        returning.setJointType(Appoint.values()[UserService.getJointType(user).getAppointType()].toString());

        //Getting status
        returning.setStatus(UserService.getStatuses().get(3).getName());

        //Getting job title
        int postId =  UserService.getJointType(user).getPostId();
        returning.setJobTitle(UserService.getJobTitle(postId, 3).getName());

        //RoleLocalizationsEntity roleLoc = UserService.getPosition(userInRoles);
        //returning.setPosition();

        //Getting passport number
        returning.setPassportNumber(user.getPassport());

        //Getting Date of Birth
        returning.setDateOfBirth(user.getDateOfBirth());

        //Getting Home phone
        returning.setHomePhone(user.getHomePhone());

        //Getting Mobile Phone
        returning.setMobilePhone(user.getMobilePhone());

        //Getting company e-mail
        returning.setCompanyEmail(user.geteMail());

        //Getting personal e-mail
        returning.setPersonalEmail(user.getPersonalEmail());

        //Getting family information
        List<FamilyInfosEntity> familyInfosEntities = UserService.getFamilyInfos();

        return returning;
    }


}
