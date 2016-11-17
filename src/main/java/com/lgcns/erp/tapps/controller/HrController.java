package com.lgcns.erp.tapps.controller;


import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.HR.DocsViewModel;
import com.lgcns.erp.tapps.viewModel.HR.HrJobexpViewModel;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

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
    @RequestMapping (value = "/Hr/Profile/Userslist", method = RequestMethod.GET)
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
    @RequestMapping (value = "/Hr/edit/geninfo", method = RequestMethod.GET)
    @ResponseBody public ModelAndView GenInfo(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/geninfo");
        HrJobexpViewModel hrjobexpViewModel = new HrJobexpViewModel();
        mav.addObject("hrjobexpVM", hrjobexpViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping (value = "/Hr/edit/test", method = RequestMethod.GET)
    @ResponseBody public ModelAndView testInfo(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/testinfo");
        List<ProfileViewModel> users = getUsers();

        mav.addObject("users", users);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal);
        mav.addObject("userProfile", userProfile);
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
}
