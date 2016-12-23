package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.mapper.UserMapper;
import com.lgcns.erp.tapps.model.DbEntities.PersonalEvalutionsEntity;
import com.lgcns.erp.tapps.model.DbEntities.RoleLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.Manager.Form;
import com.lgcns.erp.tapps.viewModel.Manager.FormModel;
import com.lgcns.erp.tapps.viewModel.PersonalInformationViewModel;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Muslimbek on 11/28/2016.
 */
@Controller
public class ManagerController {

    @RequestMapping(value = "/Manager/Profile", method = RequestMethod.GET)
    public ModelAndView Hrprofile(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/Index");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        return mav;
    }

    @RequestMapping (value = "/Manager/Profile/Appointment", method = RequestMethod.GET)
    public ModelAndView HrAppointmentrec(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/AppointmentRec");
        AppointmentrecViewModel appointmentrecViewModel = UserController.getAppointmentByUsername(principal.getName());
        mav.addObject("appointmentrecVM", appointmentrecViewModel);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping (value = "/Manager/Profile/Docs", method = RequestMethod.GET)
    public ModelAndView CTODocs(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/Docs");
        List<DocsViewModel> docsVM = UserController.getDocuments(principal.getName());
        mav.addObject("docsVM", docsVM);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping (value = "/Manager/Profile/Salary", method = RequestMethod.GET)
    public ModelAndView HrSalaryrec(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/SalaryDetails");
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<SalaryVewModel> salaryVewModel = UserController.getSalaryByUser(user.getUserName());
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("salaryVM", salaryVewModel);
        return mav;
    }

    @RequestMapping (value = "/Manager/Profile/Edu", method = RequestMethod.GET)
    public ModelAndView HrEdu(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/EducationCer");
        EduViewModel hreduViewModel = UserController.getEducationByUsername(principal.getName());
        mav.addObject("eduVM", hreduViewModel);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }
    @RequestMapping (value = "/Manager/Profile/Jobexp", method = RequestMethod.GET)
    public ModelAndView HrJobexp(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/JobExp");
        List<JobexpViewModel> jobexpViewModel = UserController.getJobExperience(principal.getName());
        mav.addObject("jobexpVM", jobexpViewModel);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }
    @RequestMapping (value = "/Manager/Profile/Train", method = RequestMethod.GET)
    public ModelAndView HrTrain(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/TrainingRec");
        List<TrainViewModel> trainViewModel = UserController.getTrainingRecord(principal.getName());
        mav.addObject("trainVM", trainViewModel);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/Manager/Profile/Project", method = RequestMethod.GET)
    public ModelAndView Project(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/Project");
       /* List<DocsViewModel> docsViewModel = getDocuments(principal);*/
        mav = UP.includeUserProfile(mav, principal);
        /*mav.addObject("docsVM", docsViewModel);*/
        return mav;
    }
    @RequestMapping (value = "/Manager/Userslist", method = RequestMethod.GET)
    public ModelAndView HrUserslist(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/Userslist");
        List<ProfileViewModel> users = getUsers();
        mav.addObject("hrUserslistVM", users);
        Map<Integer, String> roles = new HashMap<Integer, String>();
        List<RoleLocalizationsEntity> roleLocalizationsEntityList = UserService.getRolesLoc();
        for (RoleLocalizationsEntity role :
                roleLocalizationsEntityList) {
            if(role.getLenguageId()==3)
                roles.put(role.getRoleId(), role.getName());
        }
        mav.addObject("roles", roles);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "Manager/changepass", method = RequestMethod.GET)
    public ModelAndView ChangePass(Principal principal){
        ModelAndView mav = new ModelAndView();
        ChangepassViewModel changepassViewModel = new ChangepassViewModel ();
        mav.setViewName("user/changepass");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("changepassVM", changepassViewModel);
        return mav;
    }

    @RequestMapping(value = "/Manager/user/{userId}/{path}", method = RequestMethod.GET)
    public ModelAndView UpdateFamInfo(Principal principal, @PathVariable("userId") int userId, @PathVariable("path") String path){
        String username = UserService.getUsernameById(userId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("path", "Manager");
        ProfileViewModel userProfile = UserController.getProfileByUsername(username);
        mav.addObject("userProfileUser", userProfile);
        mav = UP.includeUserProfile(mav, principal);
        if(path.compareTo("Geninfo")==0) {

            mav.setViewName("Home/IndexView");

            return mav;
        }
        if(path.compareTo("Salary")==0) {
            mav.setViewName("Home/viewmenu/SalaryDetails");
            List<SalaryVewModel> salaryVewModel = UserController.getSalaryByUser(username);

            mav.addObject("salaryVM", salaryVewModel);
            return mav;
        }
        if(path.compareTo("Edu")==0) {
            mav.setViewName("Home/viewmenu/EducationCer");
            EduViewModel eduViewModel = UserController.getEducationByUsername(username);
            mav.addObject("eduVM", eduViewModel);
            return mav;
        }
        if(path.compareTo("Jobexp")==0) {
            mav.setViewName("Home/viewmenu/JobExp");
            List<JobexpViewModel> jobExperience = UserController.getJobExperience(username);
            mav.addObject("jobVM", jobExperience);
            return mav;
        }
        if(path.compareTo("Train")==0) {
            mav.setViewName("Home/viewmenu/TrainingRec");
            List<TrainViewModel> trainViewModels = UserController.getTrainingRecord(username);
            mav.addObject("trainVM", trainViewModels);

            return mav;
        }
        if(path.compareTo("Docs")==0) {
            mav.setViewName("Home/viewmenu/Docs");
            List<DocsViewModel> docsViewModels = UserController.getDocuments(username);
            mav.addObject("docsVM", docsViewModels);

            return mav;
        }
        return null;
    }

    @RequestMapping ( value = "/Manager/Evaluation", method = RequestMethod.POST )
    public String UpdateEvaluation(Model model, FormModel form, BindingResult result, Principal principal){
        int id = UserService.getIdByUsername(principal.getName());
        insertEvaluations(form, id);
        return null;
    }

    private void insertEvaluations(FormModel form, int id) {
        for (Form form1:
                form.getForms()) {
            if(form1.getGrade() != 0){
                UserService.insertEvaluation(UserMapper.mapCTOEvaluation(form1, id));
            }
        }
    }

    @RequestMapping(value = "/Manager/Profile/Evaluation", method = RequestMethod.GET)
    public ModelAndView Evaluation(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/EvaluationHistory");
        List<PersonalEvalutionsEntity> evaluations = UserService.getEvaluationsByUserId(UserService.getUserIdByUsername(principal.getName()));
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("evaluationsVM", evaluations);
        return mav;
    }

    @RequestMapping (value = "/Manager/Evaluation", method = RequestMethod.GET)
    public ModelAndView  CTOUserslist(Model model, Principal principal){
        ModelAndView mav = new ModelAndView();
        List<ProfileViewModel> users = getUsers();
        List<Form> users2 = new LinkedList<Form>();
        Form f=new Form();
        for (ProfileViewModel pvm :
                users) {
            if(pvm.getChiefId()==UserService.getIdByUsername(principal.getName())) {
                f = new Form();
                f.setFirstName(pvm.getFirstName()[2]);
                f.setLastName(pvm.getLastName()[2]);
                f.setId(pvm.getId());
                users2.add(f);
            }
        }
        FormModel formModel = new FormModel();
        formModel.setForms(users2);
        model.addAttribute("formModel", formModel);
        mav = UP.includeUserProfile(mav, principal);
        mav.setViewName("shared/menu/Evaluation");
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
            if(user.getChiefId()!=null)
            {
                userProfile.setChiefId(userEntity.getChiefId());
            }
            if(user.getRoleId()!=null)
            {
                userProfile.setRoleId(user.getRoleId());
            }

            returning.add(userProfile);
        }
        return returning;

    }

}
