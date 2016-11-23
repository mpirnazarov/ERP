package com.lgcns.erp.tapps.controller;


import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.Enums.Appoint;
import com.lgcns.erp.tapps.model.DbEntities.*;
import com.lgcns.erp.tapps.viewModel.HR.DocsViewModel;
import com.lgcns.erp.tapps.viewModel.HR.HrJobexpViewModel;
import com.lgcns.erp.tapps.viewModel.PersonalInformationViewModel;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.*;
import com.lgcns.erp.tapps.viewModel.usermenu.personalInfo.BirthPlace;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 26-Oct-16.
 */
@Controller
public class HrController {

    @RequestMapping (value = "/Hr/Profile", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Hrprofile(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/IndexHR");
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping (value = "/Hr/Profile/Appointment", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrAppointmentrec(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/AppointmentRec");
        AppointmentrecViewModel appointmentrecViewModel = UserController.getAppointmentByUsername(principal);
        mav.addObject("appointmentrecVM", appointmentrecViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping (value = "/Hr/Profile/Salary", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrSalaryrec(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/SalaryDetails");
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<SalaryVewModel> salaryVewModel = UserController.getSalaryByUser(user);

        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
        mav.addObject("salaryVM", salaryVewModel);
        return mav;
    }

    @RequestMapping (value = "/Hr/Profile/Edu", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrEdu(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/EducationCer");
        EduViewModel hreduViewModel = UserController.getEducationByUsername(principal);
        mav.addObject("eduVM", hreduViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping (value = "/Hr/Profile/Jobexp", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrJobexp(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/JobExp");
        List<JobexpViewModel> jobexpViewModel = UserController.getJobExperience(principal);
        mav.addObject("jobexpVM", jobexpViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping (value = "/Hr/Profile/Train", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrTrain(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/TrainingRec");
        List<TrainViewModel> trainViewModel = UserController.getTrainingRecord(principal);
        mav.addObject("trainVM", trainViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping (value = "/Hr/Userslist", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrUserslist(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/Userslist");
        List<ProfileViewModel> users = getUsers();

        mav.addObject("hrUserslistVM", users);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping(value = "/Hr/Profile/Docs", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Docs(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/Docs");
        DocsViewModel docsViewModel = new DocsViewModel();
        mav.addObject("docsVM", docsViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping(value = "/Hr/user/{id}/update/{path}", method = RequestMethod.GET)
    @ResponseBody public ModelAndView UpdateInfo(Principal principal, Model model, @ModelAttribute("user") ProfileViewModel person, @PathVariable("id") int id, @PathVariable("path") String path){
        ModelAndView mav = new ModelAndView();
        System.out.print("Path: ");
       if(path.compareTo("geninfo")==0) {
           System.out.println(path);
           mav.setViewName("Home/editmenu/geninfo");
           ProfileViewModel userProfile = getProfileById(id);
           person = userProfile;
           model.addAttribute("person",  person);
           // Getting list of departments and send to view
           Map<Integer, String> deps = new HashMap<Integer, String>();
           for (DepartmentLocalizationsEntity dep :
                   UserService.getDepartments(3)) {
                deps.put(dep.getId(), dep.getName());
           }
           model.addAttribute("departments",  deps);

           //Getting positions list from RoleLocalizations
           Map<Integer, String> positions = new HashMap<Integer, String>();
           for (RoleLocalizationsEntity pos :
                   UserService.getRolesLoc()) {
               positions.put(pos.getId(), pos.getName());
           }
           model.addAttribute("positions",  positions);

           //Getting jobTitles list from RoleLocalizations
           Map<Integer, String> jobTitles = new HashMap<Integer, String>();
           for (PostLocalizationsEntity pos :
                   UserService.getPostLocalizations(3)) {
               jobTitles.put(pos.getId(), pos.getName());
           }
           model.addAttribute("jobTitles",  jobTitles);

           //Getting jobTitles list from RoleLocalizations
           Map<Integer, String> statuses = new HashMap<Integer, String>();
           int i=0;
           for (StatusLocalizationsEntity statusesEntity :
                   UserService.getStatuses()) {
               statuses.put(statusesEntity.getId(), statusesEntity.getName());
           }
           model.addAttribute("statuses",  statuses);

           // Setting list of joint types
           Map<Integer, String> jointType = new HashMap<Integer, String>();
           for (Appoint a :
                   Appoint.values()) {
               jointType.put(a.getValue(), a.name());
           } 

           model.addAttribute("jointType", jointType);

           return mav;
       }
       else if(path.compareTo("")==0){


       }
       else{

       }
        mav.setViewName("Home/editmenu/geninfo");
        HrJobexpViewModel hrjobexpViewModel = new HrJobexpViewModel();
        mav.addObject("hrjobexpVM", hrjobexpViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping (value = "/Hr/user/{id}/update/{path}", method = RequestMethod.POST )
    @ResponseBody public void UpdateInfo(Principal principal,  @ModelAttribute @Validated ProfileViewModel person){
        System.out.println( person.toString());

    }



    @RequestMapping(value = "Hr/changepass", method = RequestMethod.GET)
    public ModelAndView ChangePass(Principal principal){
        ModelAndView mav = new ModelAndView();
        ChangepassViewModel changepassViewModel = new ChangepassViewModel ();
        mav.setViewName("user/changepass");
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
        mav.addObject("changepassVM", changepassViewModel);
        return mav;
    }



    public static List<ProfileViewModel> getUsers() {
        List<ProfileViewModel> returning = new LinkedList<ProfileViewModel>();
        ProfileViewModel userProfile;
        List<UsersEntity> usersEntityList = UserService.getAllUsers();


        for (UsersEntity userEntity:
             usersEntityList) {
            userProfile = new ProfileViewModel();
            // Getting data from users db
            UsersEntity user = UserService.getUserByUsername(userEntity.getUserName());
            // Getting its localization data
            userProfile.setUsername(userEntity.getUserName());
            List<UserLocalizationsEntity> userLocalizationsEntities = UserService.getUserLocByUserId(user.getId());
            PersonalInformationViewModel personalInformationViewModel = new PersonalInformationViewModel();
            personalInformationViewModel.setEmailCompany(userEntity.geteMail());
            userProfile.setPersonalInfo(personalInformationViewModel);
            for (UserLocalizationsEntity ul :
                    userLocalizationsEntities) {
                userProfile.addData1(String.format("%05d", user.getId()), ul.getFirstName(), ul.getLastName(), ul.getFatherName(), ul.getAddress(), ul.getLanguageId());
            }
            /*//Getting department name
            userProfile.setDepartment(UserService.getDepartments().get(3).getName());

            //Getting Position from user_in_roles
            userProfile.setPosition(UserService.getRoleLoc(user).getName());

            //Getting is political
            if (user.getPolitical())
                userProfile.setPolitical("Yes");
            else
                userProfile.setPolitical("No");

            //Getting Joint Type
            userProfile.setJointType(Appoint.values()[UserController.getMax(UserService.getUserInPost(user)).getContractType() - 1].toString());

            //Getting status
            userProfile.setStatus(UserService.getStatuses().get(3).getName());

            //Getting job title
            int postId = UserController.getMax(UserService.getUserInPost(user)).getPostId();
            userProfile.setJobTitle(UserService.getJobTitle(postId, 3).getName());

            //RoleLocalizationsEntity roleLoc = UserService.getPosition(userInRoles);
            //returning.setPosition();

            //Getting passport number
            userProfile.setPassportNumber(user.getPassport());

            //Getting and setting entry date
            userProfile.setEntryDate(user.getHiringDate());

            PersonalInformationViewModel personalInfo = new PersonalInformationViewModel();

            //Getting birth place
            //System.out.println("Birth Place: " + UserService.getUserLocalizations(user).getBirthPlace());
            //returning.setBirthPlace(UserService.getUserLocalizations(user).getBirthPlace().toString());
            int i = 0;
            for (UserLocalizationsEntity userLoc :
                    UserService.getUserLocalizations(user)) {
                personalInfo.addBirthPlace(userLoc.getBirthPlace(), userLoc.getLanguageId());
            }


            personalInfo.setDateOfBirth(user.getDateOfBirth());
            personalInfo.setHomePhone(user.getHomePhone());
            personalInfo.setMobilePhone(user.getMobilePhone());
            personalInfo.setEmailCompany(user.geteMail());
            personalInfo.setEmailPersonal(user.getPersonalEmail());

            userProfile.setPersonalInfo(personalInfo);

            List<BirthPlace> bPlace = userProfile.getPersonalInfo().getBirthPlace();

            //Getting family information
            List<FamilyInfosEntity> familyInfosEntities = UserService.getFamilyInfos(user);
            List<FamilyMember> familyMembers = new LinkedList<FamilyMember>();
            List<FamiliyInfoLocalizationsEntity> familyLoc2;
            for (FamilyInfosEntity fie :
                    familyInfosEntities) {
                familyLoc2 = UserService.getFamilyInfosLoc(fie);

                FamilyMember familyMember = new FamilyMember(familyLoc2.size());
                for (FamiliyInfoLocalizationsEntity faInLoEn :
                        familyLoc2) {
                    familyMember.add(faInLoEn.getRelation(), faInLoEn.getLastName() + " " + faInLoEn.getFirstName(), fie.getDateOfBirth(), faInLoEn.getJobTitle(), faInLoEn.getLanguageId());
                    // System.out.println( familyMember.getRelation()[faInLoEn.getLanguageId()-1]+ " " + familyMember.getFullName()[faInLoEn.getLanguageId()-1] + " " + familyMember.getDateOfBirth() + " " + familyMember.getJobTitle()[faInLoEn.getLanguageId()-1]);
                }
                familyMembers.add(familyMember);
            }
            userProfile.setFamilyLoc(familyMembers);

            // (MUST BE FINISHED!) Getting and setting vacation days
            userProfile.setVacationDaysLeft(0);
            userProfile.setVacationDaysAll(12);*/
            returning.add(userProfile);
        }
        return returning;

    }

    // Getting user from ID
    public static ProfileViewModel getProfileById(int id){
        ProfileViewModel returning = new ProfileViewModel();

        // Getting data from users db
        UsersEntity user = UserService.getUserById(id);

        // Getting its localization data
        List<UserLocalizationsEntity> userLocalizationsEntities = UserService.getUserLocByUserId(user.getId());

        for (UserLocalizationsEntity ul:
                userLocalizationsEntities ) {
            returning.addData1(String.format("%05d", user.getId()), ul.getFirstName(), ul.getLastName(), ul.getFatherName(), ul.getAddress(), ul.getLanguageId());
        }
        //Getting department name
        try {
            returning.setDepartment(UserService.getDepartments().get(3).getName());
        }catch (Exception e){
            e.printStackTrace();
        }


        //Getting Position from user_in_roles
        try {
            returning.setPosition(UserService.getRoleLoc(user).getName());
        }catch (Exception e){
            e.printStackTrace();
        }


        //Getting is political
        try {
            if (user.getPolitical())
                returning.setPolitical("Yes");
            else
                returning.setPolitical("No");
        }catch (Exception e){
            e.printStackTrace();
        }
        try {

            //Getting Joint Type
            returning.setJointType(Appoint.values()[getMax(UserService.getUserInPost(user)).getContractType() - 1].toString());

            //Getting status
            returning.setStatus(UserService.getStatuses().get(3).getName());

            //Getting job title
            int postId = getMax(UserService.getUserInPost(user)).getPostId();
            returning.setJobTitle(UserService.getJobTitle(postId, 3).getName());

            //RoleLocalizationsEntity roleLoc = UserService.getPosition(userInRoles);
            //returning.setPosition();

            //Getting passport number
            returning.setPassportNumber(user.getPassport());

            //Getting and setting entry date
            returning.setEntryDate(user.getHiringDate());

            PersonalInformationViewModel personalInfo = new PersonalInformationViewModel();

            //Getting birth place
            //System.out.println("Birth Place: " + UserService.getUserLocalizations(user).getBirthPlace());
            //returning.setBirthPlace(UserService.getUserLocalizations(user).getBirthPlace().toString());
            int i = 0;
            for (UserLocalizationsEntity userLoc :
                    UserService.getUserLocalizations(user)) {
                personalInfo.addBirthPlace(userLoc.getBirthPlace(), userLoc.getLanguageId());
            }


            personalInfo.setDateOfBirth(user.getDateOfBirth());
            personalInfo.setHomePhone(user.getHomePhone());
            personalInfo.setMobilePhone(user.getMobilePhone());
            personalInfo.setEmailCompany(user.geteMail());
            personalInfo.setEmailPersonal(user.getPersonalEmail());

            returning.setPersonalInfo(personalInfo);

            String[] bPlace = returning.getPersonalInfo().getBirthPlace();

            //Getting family information
            List<FamilyInfosEntity> familyInfosEntities = UserService.getFamilyInfos(user);
            List<FamilyMember> familyMembers = new LinkedList<FamilyMember>();
            List<FamiliyInfoLocalizationsEntity> familyLoc2;
            for (FamilyInfosEntity fie :
                    familyInfosEntities) {
                familyLoc2 = UserService.getFamilyInfosLoc(fie);

                FamilyMember familyMember = new FamilyMember(familyLoc2.size());
                for (FamiliyInfoLocalizationsEntity faInLoEn :
                        familyLoc2) {
                    familyMember.add(faInLoEn.getRelation(), faInLoEn.getLastName() + " " + faInLoEn.getFirstName(), fie.getDateOfBirth(), faInLoEn.getJobTitle(), faInLoEn.getLanguageId());
                    // System.out.println( familyMember.getRelation()[faInLoEn.getLanguageId()-1]+ " " + familyMember.getFullName()[faInLoEn.getLanguageId()-1] + " " + familyMember.getDateOfBirth() + " " + familyMember.getJobTitle()[faInLoEn.getLanguageId()-1]);
                }
                familyMembers.add(familyMember);
            }
            returning.setFamilyLoc(familyMembers);
        }catch (Exception e){
            e.printStackTrace();
        }
        // (MUST BE FINISHED!) Getting and setting vacation days
        returning.setVacationDaysLeft(0);
        returning.setVacationDaysAll(12);
        return returning;
    }

    public static UserInPostsEntity getMax(List<UserInPostsEntity> usersInPost) {
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

