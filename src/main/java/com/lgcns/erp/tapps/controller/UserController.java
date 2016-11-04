package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.Enums.Appoint;
import com.lgcns.erp.tapps.Enums.Language;
import com.lgcns.erp.tapps.Enums.Language_Ranking;
import com.lgcns.erp.tapps.mapper.UserMapper;
import com.lgcns.erp.tapps.model.DbEntities.*;
import com.lgcns.erp.tapps.model.UserInfo;
import com.lgcns.erp.tapps.viewModel.LoginViewModel;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.RegistrationViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.*;
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
import java.sql.Date;
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
            mav.addObject("roles", getRolesIdAndName());
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

        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Appointment", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Appointmentrec(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/AppointmentRec");
        List<AppointmentrecViewModel> appointmentrecViewModel = getAppointmentByUsername(principal);

        mav.addObject("appointmentrecVM", appointmentrecViewModel);
        return mav;
    }


    @RequestMapping(value = "/User/Profile/Edu", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Edu(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/EducationCer");
        EduViewModel eduViewModel = getEducationByUsername(principal);

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
    @RequestMapping(value = "/User/Profile/Docs", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Docs() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/Docs");
        DocsViewModel docsViewModel = new DocsViewModel();
        mav.addObject("docsVM", docsViewModel);
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
        returning.setJointType(Appoint.values()[getMax(UserService.getUserInPost(user)).getContractType()-1].toString());

        //Getting status
        returning.setStatus(UserService.getStatuses().get(3).getName());

        //Getting job title
        int postId =  getMax(UserService.getUserInPost(user)).getPostId();
        returning.setJobTitle(UserService.getJobTitle(postId, 3).getName());

        //RoleLocalizationsEntity roleLoc = UserService.getPosition(userInRoles);
        //returning.setPosition();

        //Getting passport number
        returning.setPassportNumber(user.getPassport());

        //Getting birth place
        //System.out.println("Birth Place: " + UserService.getUserLocalizations(user).getBirthPlace());
        returning.setBirthPlace(UserService.getUserLocalizations(user).getBirthPlace().toString());

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
        List<FamilyInfosEntity> familyInfosEntities = UserService.getFamilyInfos(user);
        List<FamilyMember> familyMembers = new LinkedList<FamilyMember>();
        for (FamilyInfosEntity fie :
                familyInfosEntities) {
            FamiliyInfoLocalizationsEntity familyLoc2 = UserService.getFamilyInfosLoc(fie, 3);
            familyMembers.add(new FamilyMember(familyLoc2.getRelation(), familyLoc2.getLastName()+" "+familyLoc2.getFirstName(), fie.getDateOfBirth(), familyLoc2.getJobTitle()));
        }
        returning.setFamilyLoc(familyMembers);

        //Getting and setting entry date
        returning.setEntryDate(user.getHiringDate());

        // (MUST BE FINISHED!) Getting and setting vacation days
        returning.setVacationDaysLeft(0);
        returning.setVacationDaysAll(12);
        return returning;
    }


    private List<AppointmentrecViewModel> getAppointmentByUsername(Principal principal) {
        List<AppointmentrecViewModel> returning = new LinkedList<AppointmentrecViewModel>();

        // Getting data from users db
        UsersEntity user = UserService.getUserByUsername(principal.getName());

        List<UserInPostsEntity> usersInPost = UserService.getUserInPost(user);

        UserInPostsEntity userInPost = usersInPost.get(0);

        for (UserInPostsEntity uip :
                usersInPost) {
            returning.add(new AppointmentrecViewModel(uip.getDateFrom(), Appoint.values()[uip.getContractType() - 1].toString(), UserService.getDepartments().get(user.getDepartmentId()).getName(), UserService.getJobTitle(uip.getPostId(), 3).getName()));
        }
        /*// Setting appoint date
        returning.setAppointDate(userInPost.getDateFrom());

        // Setting appoint type
        returning.setAppointmentType(Appoint.values()[userInPost.getAppointType()-1].toString());

        // Setting department
        returning.setDepartment(UserService.getDepartments().get(user.getDepartmentId()).getName());

        //Setting Role (Post)
        returning.setRole(UserService.getJobTitle(userInPost.getPostId(), 3).getName());
*/
        return returning;
    }

    private EduViewModel getEducationByUsername(Principal principal) {
        EduViewModel eduReturn = new EduViewModel();
        EducationLocalizationsEntity eduLoc = null;
        UsersEntity user = UserService.getUserByUsername(principal.getName());

        // Getting and setting Educations module
        List<EducationsEntity> educations = UserService.getEducationsByUsername(user);
        for (EducationsEntity edu:
             educations) {
            eduLoc = UserService.getEducationLocalization(edu, 3);
            eduReturn.addEducation(eduLoc.getName(), eduLoc.getMajor(), eduLoc.getDegree(), edu.getStartDate(), edu.getEndDate());
        }

        // Getting and setting Language Summary module
        List<UserInLanguagesEntity> languageSummaries = UserService.getUserInLanguages(user);
        for (UserInLanguagesEntity lan :
                languageSummaries) {
            eduReturn.addLanguageSummary(Language.values()[lan.getLanguageId()-1].toString(), Language_Ranking.values()[lan.getListening()-1].toString(), Language_Ranking.values()[lan.getReading()].toString(), Language_Ranking.values()[lan.getWriting()].toString(), Language_Ranking.values()[lan.getSpeaking()].toString());
        }

        // Getting and setting Certificates module
        List<CertificatesEntity> certificatesEntities = UserService.getCertificates(user);
        for (CertificatesEntity cert :
                certificatesEntities) {
            CertificateLocalizationsEntity certificatesLocEntitie = UserService.getCertificatesLoc(cert, 3);
            System.out.println(certificatesLocEntitie.getName()+" "+certificatesLocEntitie.getOrganization()+" "+cert.getDateTime()+" "+cert.getMark());
            eduReturn.addCertificate(certificatesLocEntitie.getName(), certificatesLocEntitie.getOrganization(), cert.getDateTime(),cert.getMark());
        }

        return eduReturn;
    }

    private UserInPostsEntity getMax(List<UserInPostsEntity> usersInPost) {
        UserInPostsEntity uip = new UserInPostsEntity();
        long num = 0;

        uip.setDateFrom(new Date(num));
        for (UserInPostsEntity up :
                usersInPost) {
            if(up.getDateFrom().compareTo(uip.getDateFrom())>0)
            {
                uip=up;
            }
        }
        return uip;
    }

}
