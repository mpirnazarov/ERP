package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.Enums.Appoint;
import com.lgcns.erp.tapps.Enums.Document_Type;
import com.lgcns.erp.tapps.mapper.UserMapper;
import com.lgcns.erp.tapps.model.DbEntities.PersonalEvalutionsEntity;
import com.lgcns.erp.tapps.model.DbEntities.RoleLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.Manager.Form;
import com.lgcns.erp.tapps.viewModel.Manager.FormModel;
import com.lgcns.erp.tapps.viewModel.Manager.Tree;
import com.lgcns.erp.tapps.viewModel.PersonInfo;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.*;

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

    @RequestMapping (value = "/Manager/Docs", method = RequestMethod.GET)
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
        Map<Integer, String> contracts = new HashMap<>();
        for (Appoint ap :
                Appoint.values()) {
            contracts.put(ap.getValue(), ap.name().replace("_", " "));
        }
        mav.addObject("contracts", contracts);
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

        // Get the list of child in the form of List of PersonInfos
        List<PersonInfo> childs = getChildList(principal);

        // Send child list to view
        mav.addObject("hrUserslistVM", childs);

        // Create roles list. To be used in view
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

    private List<PersonInfo> getChildList(Principal principal) {
        // First we get all users
        List<PersonInfo> users = getUsers();

        // We create tree
        Tree<Integer, String> tree = null;
        for (PersonInfo personInfo
                :users) {
            // Check for Boss and insert root in tree
            if(personInfo.getChiefId()==personInfo.getEmployeeId())
                tree =  new Tree<>(personInfo.getEmployeeId(), personInfo.getFirstName());
        }

        // We sort for the reason that the child can not be entered before ancestor
        Collections.sort(users);
        PersonInfo per=null;
        for (PersonInfo p :
                users) {
            if (p.getEmployeeId() == p.getChiefId())
                per = p;
        }
        users.remove(per);
        for (PersonInfo person :
                users) {
            // Build tree
            tree.addChild(person.getChiefId(), person.getEmployeeId(), person.getFirstName());
        }
        return tree.subTree(UserService.getIdByUsername(principal.getName()));
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

        // First check if the user can be viewed by Manager. Overcome the change in path
        List<PersonInfo> personInfoList = getChildList(principal);
        boolean flag=false;
        for (PersonInfo person :
                personInfoList) {
            if (userId == person.getEmployeeId())
                flag = true;
        }
        if(!flag)
            return new ModelAndView("/error");

        String username = UserService.getUsernameById(userId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("path", "Manager");
        ProfileViewModel userProfile = UserController.getProfileByUsername(username);
        mav.addObject("userProfileUser", userProfile);
        mav.addObject("fullName", userProfile.getFirstName()[2]+" "+userProfile.getLastName()[2]);
        mav.addObject("jobTitle", userProfile.getJobTitle());
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
            mav.addObject("jobexpVM", jobExperience);
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
            Map<Integer, String> doctype = new HashMap<>();
            for (Document_Type doct :
                    Document_Type.values()) {
                doctype.put(doct.getValue(), doct.name().replace("_ ", " "));
            }
            mav.addObject("docType", doctype);
            return mav;
        }
        return null;
    }

    @RequestMapping (value = "/Manager/Evaluation", method = RequestMethod.GET)
    public ModelAndView  CTOUserslist(Model model, Principal principal){
        ModelAndView mav = new ModelAndView();
        List<PersonInfo> users = getUsers();
        List<Form> users2 = new LinkedList<Form>();
        Form f=new Form();
        for (PersonInfo pvm :
                users) {
            if(pvm.getChiefId()==UserService.getIdByUsername(principal.getName()) && pvm.getEmployeeId()!=UserService.getIdByUsername(principal.getName())) {
                f = new Form();
                f.setFirstName(pvm.getFirstName());
                f.setLastName(pvm.getLastName());
                f.setId(String.format("%05d", pvm.getEmployeeId()));
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

    @RequestMapping ( value = "/Manager/Evaluation", method = RequestMethod.POST )
    public String UpdateEvaluation(FormModel form, Principal principal){
        int id = UserService.getIdByUsername(principal.getName());
        insertEvaluations(form, id);
        return "redirect: /Manager/Evaluation";
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
        List<PersonalEvalutionsEntity> evaluations = null;
        PersonalEvalutionsEntity evalutionsEntity=null;
        try{
            evaluations = UserService.getEvaluationsByUserId(UserService.getUserIdByUsername(principal.getName()));
        }catch (Exception e){
            e.printStackTrace();
        }
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("evaluationsVM", evaluations);
        return mav;
    }

    @RequestMapping(value = "/Manager/user/{id}/Docs/Download/{docId}")
    public String downloadFile(Principal principal, HttpServletResponse response, @PathVariable("id") int id, @PathVariable("docId") int docId) throws IOException {
        File file = null;
        ClassLoader classLoader = this.getClass().getClassLoader();
        String filePath = UserService.getDocumentById(docId, id).getLink();
        file = new File(filePath);

        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return "redirect: /Hr/Docs/";
        }

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : "+mimeType);

        response.setContentType(mimeType);

        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));


        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());



        return "redirect: ../";
    }


    public static List<PersonInfo> getUsers() {
        List<PersonInfo> returning = new LinkedList<PersonInfo>();
        PersonInfo userProfile;
        List<UsersEntity> usersEntityList = UserService.getAllUsers();


        for (UsersEntity userEntity:
                usersEntityList) {
            if(userEntity.isEnabled()==true) {
                userProfile = new PersonInfo();
                // Getting data from users db
                userProfile.setEmployeeId(userEntity.getId());
                userProfile.setId(String.format("%05d", userEntity.getId()));
                userProfile.setRoleId(userEntity.getRoleId());
                if (userEntity.getChiefId() != null)
                    userProfile.setChiefId(userEntity.getChiefId());
                else
                    userProfile.setChiefId(userEntity.getId());
                userProfile.setUserName(userEntity.getUserName());

                // Getting its localization data
                UserLocalizationsEntity userLoc = UserService.getUserLocByUserId(userEntity.getId(), 3);
                userProfile.setFirstName(userLoc.getFirstName());
                userProfile.setLastName(userLoc.getLastName());

                returning.add(userProfile);
            }
        }
        return returning;

    }

}
