package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.CTO.Form;
import com.lgcns.erp.tapps.viewModel.CTO.FormModel;
import com.lgcns.erp.tapps.viewModel.PersonalInformationViewModel;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Muslimbek on 11/28/2016.
 */
@Controller
public class CTOController {

    @RequestMapping(value = "/CTO/Profile", method = RequestMethod.GET)
    public ModelAndView Hrprofile(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/IndexCTO");
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping (value = "/CTO/Profile/Appointment", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView HrAppointmentrec(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/CTO/AppointmentRec");
        AppointmentrecViewModel appointmentrecViewModel = UserController.getAppointmentByUsername(principal.getName());
        mav.addObject("appointmentrecVM", appointmentrecViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping (value = "/CTO/Profile/Salary", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrSalaryrec(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/CTO/SalaryDetails");
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<SalaryVewModel> salaryVewModel = UserController.getSalaryByUser(user);

        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        mav.addObject("salaryVM", salaryVewModel);
        return mav;
    }

    @RequestMapping (value = "/CTO/Profile/Edu", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrEdu(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/CTO/EducationCer");
        EduViewModel hreduViewModel = UserController.getEducationByUsername(principal);
        mav.addObject("eduVM", hreduViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping (value = "/CTO/Profile/Jobexp", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrJobexp(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/CTO/JobExp");
        List<JobexpViewModel> jobexpViewModel = UserController.getJobExperience(principal.getName());
        mav.addObject("jobexpVM", jobexpViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping (value = "/CTO/Profile/Train", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrTrain(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/CTO/TrainingRec");
        List<TrainViewModel> trainViewModel = UserController.getTrainingRecord(principal);
        mav.addObject("trainVM", trainViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping (value = "/CTO/Userslist", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrUserslist(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/CTO/Userslist");
        List<ProfileViewModel> users = getUsers();

        mav.addObject("hrUserslistVM", users);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping(value = "CTO/changepass", method = RequestMethod.GET)
    public ModelAndView ChangePass(Principal principal){
        ModelAndView mav = new ModelAndView();
        ChangepassViewModel changepassViewModel = new ChangepassViewModel ();
        mav.setViewName("Home/CTO/changepass");
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        mav.addObject("changepassVM", changepassViewModel);
        return mav;
    }

    @RequestMapping(value = "/CTO/user/{userId}/{path}", method = RequestMethod.GET)
    public ModelAndView UpdateFamInfo(Principal principal, @PathVariable("userId") int userId, @PathVariable("path") String path){
        System.out.println("ID: " + userId);
        if(path.compareTo("geninfo")==0) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("Home/CTO/userInfo/geninfo");
            String username = UserService.getUsernameById(userId);
            System.out.printf("I am still working");
            ProfileViewModel userProfile = UserController.getProfileByUsername(username);
            System.out.println(userProfile.getFirstName()[2] + "  " + userProfile.getLastName()[2]);
            mav.addObject("userProfile", userProfile);
            return mav;
        }
        if(path.compareTo("salary")==0) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("Home/CTO/userInfo/salary");
            String username = UserService.getUsernameById(userId);
            ProfileViewModel userProfile = UserController.getProfileByUsername(username);
            mav.addObject("userProfile", userProfile);
            return mav;
        }

        return null;
    }

    @RequestMapping ( value = "/CTO/Evaluation", method = RequestMethod.POST )
    public String UpdateEvaluation(Model model, FormModel form, BindingResult result, Principal principal){
        int id = UserService.getIdByUsername(principal.getName());
        insertEvaluations(form, id);
        return null;
    }

    private void insertEvaluations(FormModel form, int id) {
        for (Form f:
                form.getForms()) {
            if(f.getGrade().compareTo(" ")!=0){
                System.out.println("Grade: "+f.getGrade());
                UserService.insertEvaluation(f, id);
            }
        }
    }

    @RequestMapping (value = "/CTO/Evaluation", method = RequestMethod.GET)
    public ModelAndView  CTOUserslist(Model model, Principal principal){
        List<ProfileViewModel> users = getUsers();
        List<Form> users2 = new LinkedList<Form>();
        Form f=new Form();
        for (ProfileViewModel pvm :
                users) {
            f=new Form();
            f.setFirstName(pvm.getFirstName()[2]);
            f.setLastName(pvm.getLastName()[2]);
            f.setId(pvm.getId());
            users2.add(f);
        }
        FormModel formModel = new FormModel();
        formModel.setForms(users2);

        model.addAttribute("form", formModel);
        return new ModelAndView("Home/CTO/Evaluation" , "formModel", formModel);
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

}
