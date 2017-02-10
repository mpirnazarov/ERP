package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.DocxDocumentMergerAndConverter;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.Enums.Appoint;
import com.lgcns.erp.tapps.Enums.Document_Type;
import com.lgcns.erp.tapps.Enums.Language_Ranking;
import com.lgcns.erp.tapps.mapper.UserMapper;
import com.lgcns.erp.tapps.model.DbEntities.*;
import com.lgcns.erp.tapps.validator.UserFormValidator;
import com.lgcns.erp.tapps.viewModel.*;
import com.lgcns.erp.tapps.viewModel.HR.DocsViewModel;
import com.lgcns.erp.tapps.viewModel.HR.HrJobexpViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.Appointment.AppointmentSummary;
import com.lgcns.erp.tapps.viewModel.usermenu.*;
import com.lgcns.erp.tapps.viewModel.usermenu.Education.Certificates;
import com.lgcns.erp.tapps.viewModel.usermenu.Education.Educations;
import com.lgcns.erp.tapps.viewModel.usermenu.Education.LanguageSummary;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Dell on 26-Oct-16.
 */
@Controller
public class HrController {

    private UserFormValidator validator;
    public static final String uploadingdir = "C://files/photos/";

    @RequestMapping(value = "/Hr/Register", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Register(Principal principal/*@ModelAttribute("registrationVM") RegistrationViewModel registrationViewModel */) {
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

        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/Hr/Register", method = RequestMethod.POST)
    public ModelAndView RegisterPost(@Valid @ModelAttribute("registrationVM") RegistrationViewModel registrationViewModel, BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes, Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("registrationVM", registrationViewModel);
        System.out.println("ERROR2!!!");
        if (bindingResult.hasErrors()) {
            System.out.println("ERROR!!!");
            mav.addObject("heads", getDirectHeadIdAndName());
            mav.addObject("departments", getDepartmentsIdAndName());
            mav.addObject("statuses", getStatusesIdAndName());
            mav.addObject("roles", getRolesIdAndName());
            mav.addObject("org.springframework.validation.BindingResult.registrationVM", bindingResult);
            return mav;
        }
        // Deletes all space and hidden characters
        registrationViewModel.getUserName().replaceAll("\\s+","");
        registrationViewModel.getPassword().replaceAll("\\s+","");
        //adding user and userLocalization info into DB
        int userId = UserService.insertUser(UserMapper.mapRegModelToUserInfo(registrationViewModel));
        UserService.insertUserLoc(UserMapper.mapRegModelToUserLocInfo(registrationViewModel, userId));

        mav = new ModelAndView();
        return new ModelAndView("redirect:/Hr/Userslist");
    }

    // This controller manages view of users (not update)
    @RequestMapping(value = "/Hr/user/{userId}/{path}", method = RequestMethod.GET)
    public ModelAndView UpdateFamInfo(Principal principal, @PathVariable("userId") int userId, @PathVariable("path") String path){
        String username = UserService.getUsernameById(userId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("path", "Hr");
        // UserProfileUser is used to view the user data
        ProfileViewModel userProfile = UserController.getProfileByUsername(username);
        mav.addObject("userProfileUser", userProfile);
        mav.addObject("fullName", userProfile.getFirstName()[2]+" "+userProfile.getLastName()[2]);
        mav.addObject("jobTitle", userProfile.getJobTitle());
        mav.addObject("external", userProfile.getExternal());
        ProfileViewModel userProfile2 = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile2);
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
            List<com.lgcns.erp.tapps.viewModel.usermenu.DocsViewModel> docsViewModels = UserController.getDocuments(username);
            mav.addObject("docsVM", docsViewModels);
            return mav;
        }
        return null;
    }

    // Hierarchy among all the pages is shown here
    @RequestMapping (value = "/Hierarchy", method = RequestMethod.GET)
    public ModelAndView Hierarchy(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/Hierarchy");
        mav = UP.includeUserProfile(mav, principal);

        // Create the JSON object using data from db
        JSONObject root = new JSONObject();
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        // Below data is necessary for GoJs. Must be included
        root.put("class", "go.TreeModel");
        UserLocalizationsEntity userLoc=null;
        for (UsersEntity user :
                UserService.getAllUsers()) {
            if(user.isEnabled()==true) {

                jsonObject = new JSONObject();
                try {
                    userLoc = UserService.getUserLocByUserId(user.getId(), 3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Inserting Id as key, fullname as name, jobTitle as title
                jsonObject.put("key", user.getId());
                jsonObject.put("name", userLoc.getFirstName() + " " + userLoc.getLastName());
                jsonObject.put("title", UserController.getProfileByUsername(user.getUserName()).getJobTitle());
                // and chiefId if available
                if (user.getChiefId() != null)
                    jsonObject.put("parent", user.getChiefId());
                // add object to array
                jsonArray.add(jsonObject);
            }
        }
        // add JSON array to ModelAndView
        root.put("nodeDataArray", jsonArray);
        mav.addObject("jsonData", root);
        return mav;
    }

    @RequestMapping (value = "/Hr/Profile", method = RequestMethod.GET)
    public ModelAndView Hrprofile(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/Index");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        return mav;
    }

    @RequestMapping (value = "/Hr/Profile/Appointment", method = RequestMethod.GET)
    public ModelAndView HrAppointmentrec(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/AppointmentRec");
        AppointmentrecViewModel appointmentrecViewModel= null;
        try{
            appointmentrecViewModel = UserController.getAppointmentByUsername(principal.getName());
        } catch (Exception e){
            e.printStackTrace();
        }
        mav.addObject("appointmentrecVM", appointmentrecViewModel);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping (value = "/Hr/Profile/Salary", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrSalaryrec(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/SalaryDetails");
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<SalaryVewModel> salaryVewModel = UserController.getSalaryByUser(user.getUserName());

        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("salaryVM", salaryVewModel);
        return mav;
    }

    @RequestMapping (value = "/Hr/Profile/Edu", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrEdu(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/EducationCer");
        EduViewModel hreduViewModel = UserController.getEducationByUsername(principal.getName());
        mav.addObject("eduVM", hreduViewModel);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }
    @RequestMapping (value = "/Hr/Profile/Jobexp", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrJobexp(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/JobExp");
        List<JobexpViewModel> jobexpViewModel = UserController.getJobExperience(principal.getName());
        mav.addObject("jobexpVM", jobexpViewModel);
        Map<Integer, String> contracts = new HashMap<>();
        for (Appoint ap :
                Appoint.values()) {
            contracts.put(ap.getValue(), ap.name().replace("_", " "));
        }
        mav.addObject("contracts", contracts);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }
    @RequestMapping (value = "/Hr/Profile/Train", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrTrain(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/TrainingRec");
        List<TrainViewModel> trainViewModel = UserController.getTrainingRecord(principal.getName());
        mav.addObject("trainVM", trainViewModel);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }
    @RequestMapping(value = "/Hr/Profile/Project", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Project(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/Project");
       /* List<DocsViewModel> docsViewModel = getDocuments(principal);*/
        mav = UP.includeUserProfile(mav, principal);
        /*mav.addObject("docsVM", docsViewModel);*/
        return mav;
    }
    @RequestMapping (value = "/Hr/Userslist", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrUserslist(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/Userslist");
        List<PersonInfo> users = getUsers();
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
    /*@RequestMapping (value = "/Hr/Head", method = RequestMethod.GET)
    public ModelAndView HrHead(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/head");
        List<ProfileViewModel> users = getUsers();
        mav.addObject("hrUserslistVM", users);
        *//*Map<Integer, String> userLocs = new HashMap<Integer, String>();
        for (ProfileViewModel user :
                users) {
            userLocs.put(Integer.parseInt(user.getId()), user.getFirstName()[2]+" "+user.getLastName()[2]);
        }*//*
        Map<Integer, String> availableSocialProfiles = new HashMap<Integer, String>();
        availableSocialProfiles.put(1, "LinkedIN");
        availableSocialProfiles.put(2, "FaceBook");
        availableSocialProfiles.put(3, "Twitter");
        availableSocialProfiles.put(4, "Google Plus");
        mav.addObject("users", availableSocialProfiles);
        mav.addObject("person", new PersonInfo());
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }*/
    @RequestMapping (value = "/Hr/user/{id}/update/Head", method = RequestMethod.POST)
     public String HrHeadPost(Principal principal, PersonInfo personInfo, @PathVariable("id") int headId){
        System.out.println("Person Info: "+personInfo.getIsChecked() + " / Head ID: "+headId);
        List<Integer> isChecked = personInfo.getIsChecked();
        for (Integer userId :
                isChecked) {
            UserService.updateHead(userId, headId);
        }
        return "redirect: /Hierarchy";
    }

    @RequestMapping(value = "/Hr/Profile/Evaluation", method = RequestMethod.GET)
    public ModelAndView Evaluation(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/EvaluationHistory");
        List<PersonalEvalutionsEntity> evaluations = null;
        try{
            evaluations = UserService.getEvaluationsByUserId(UserService.getUserIdByUsername(principal.getName()));
        }catch (Exception e){
            e.printStackTrace();
        }
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("evaluationsVM", evaluations);
        List<UserLocalizationsEntity> usersEntities = UserService.getAllUserLocs();
        Map<Integer, String> users = new HashMap<Integer, String>();
        for (UserLocalizationsEntity u :
                usersEntities) {
                users.put(u.getUserId(), u.getFirstName() + " " + u.getLastName());
        }
        mav.addObject("users", users);
        return mav;
    }

    @RequestMapping(value = "/Hr/GenerateDocs", method = RequestMethod.GET)
    public ModelAndView Docs(Principal principal, Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/GenerateDocs");
        DocsViewModel docsViewModel = new DocsViewModel();

        List<DocumentsEntity> documentsEntities = UserService.getDocumentsGen(7);
        Map<Integer, String> documentsEntitiesFinal = new HashMap<Integer, String>();

        for (DocumentsEntity docs :
                documentsEntities) {
                documentsEntitiesFinal.put(docs.getId(), docs.getName());
        }
        Map<Integer, String> users = new HashMap<Integer, String>();
        for (UserLocalizationsEntity user2 :
                UserService.getAllUserLocs()) {
            if (user2.getLanguageId()==3)
            {
                users.put(user2.getUserId(), user2.getFirstName() + " " + user2.getLastName());
            }
        }

        model.addAttribute("docs", docsViewModel);
        model.addAttribute("documents", documentsEntitiesFinal);
        model.addAttribute("users", users);
        mav.addObject("docs", docsViewModel);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/Hr/Docs/Manage/Del/{docId}")
    public String deleteDoc(Principal principal, @PathVariable("docId") int docId) throws IOException {
        String path = UserService.getDocumentByDocId(docId).getLink();
        File file = new File(path);
        if(file.delete())
            System.out.println(file.getName() + " is deleted!");
        else
            System.out.println("Delete operation is failed.");

        UserService.deleteDocument(docId);
        return "redirect: /Hr/Docs/Manage";
    }

    @RequestMapping(value = "/Hr/Docs/Manage", method = RequestMethod.GET)
    public ModelAndView ManageDocs(Principal principal, Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/ManageDocs");
        List<DocumentsEntity> documentsEntities = UserService.getDocumentsGen(7);
        Map<Integer, String> documentsEntitiesFinal = new HashMap<Integer, String>();

        for (DocumentsEntity docs :
                documentsEntities) {
            documentsEntitiesFinal.put(docs.getId(), docs.getName());
        }
        model.addAttribute("documents", documentsEntities);

        FileBucket fileBucket = new FileBucket();
        model.addAttribute("fileBucket", fileBucket);

        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping(value = "/Hr/Docs/Manage", method = RequestMethod.POST)
    public String ManageDocsUpdate(FileBucket fileBucket, Principal principal, Model model) throws IOException {
        MultipartFile multipartFile = fileBucket.getFile();

        //Now do something with file...
        int id = UserService.getUserIdByUsername(principal.getName());
        File f =  new File("C:/files/template");
        boolean b = f.mkdir();
        if (b)
        {
            System.out.println("DIRECTORY CREATED SECCESFULLY");
        }
        FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File("C:/files/template/" + fileBucket.getFile().getOriginalFilename())) ;
        DocsViewModel docsViewModel = new DocsViewModel();
        docsViewModel.setUserId(id);
        docsViewModel.setDocumentType(7);
        docsViewModel.setLink("C:/files/template/" + fileBucket.getFile().getOriginalFilename());
        docsViewModel.setName(fileBucket.getName());
        UserService.insertDocumentUser(UserMapper.mapDocuments(docsViewModel));
        String fileName = multipartFile.getOriginalFilename();
        return "redirect: /Hr/Docs/Manage";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Appointment/Edit/{appId}", method = RequestMethod.GET)
    public ModelAndView updateApp(Principal principal, Model model, @PathVariable("userId") int userId, @PathVariable("appId") int appId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/edit/appointment");

        AppointmentSummary appointment = new AppointmentSummary();
        UserInPostsEntity userInPostsEntity = UserService.getUserInPostById(appId);
        appointment.setUserId(userInPostsEntity.getUserId());
        appointment.setAppointDate(userInPostsEntity.getDateFrom());
        appointment.setDepartmentId(userInPostsEntity.getDepartmentId());
        appointment.setContractType(userInPostsEntity.getContractType());
        appointment.setPostId(userInPostsEntity.getPostId());
        appointment.setEndDate(userInPostsEntity.getDateEnd());
        appointment.setRoleId(UserService.getUserByUsername(principal.getName()).getRoleId());
        appointment.setExternalId(userInPostsEntity.getExternalId());
        model.addAttribute("appointmentVM", appointment);

        List<UserLocalizationsEntity> users = UserService.getAllUserLocs(3);
        model.addAttribute("users", users);

        Map<Integer, String> appointmentTypes = new HashMap<Integer, String>();
        for (Appoint ap :
                Appoint.values()) {
            appointmentTypes.put(ap.getValue(), ap.name().replace("_", " "));
        }
        model.addAttribute("types", appointmentTypes);

        Map<Integer, String> departmentsList = new HashMap<Integer, String>();
        for (DepartmentLocalizationsEntity dep :
                UserService.getDepartments(3)) {
            departmentsList.put(dep.getDepartmentId(), dep.getName());
        }
        model.addAttribute("departments", departmentsList);

        Map<Integer, String> posts = new HashMap<Integer, String>();
        for (PostLocalizationsEntity postLocalizationsEntity:
                UserService.getPostLocalizations(3)){
            posts.put(postLocalizationsEntity.getPostId(), postLocalizationsEntity.getName());
        }
        model.addAttribute("posts", posts);

        Map<Integer, String> externals = new HashMap<Integer, String>();
        for(ExternalLocalizationsEntity ext:
                UserService.getExternalLoc()){
            externals.put(ext.getId(), ext.getExternalName());
        }
        model.addAttribute("externals", externals);

        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Appointment/Edit/{appId}", method = RequestMethod.POST )
    public String updateAppPost(Principal principal, @ModelAttribute  AppointmentSummary appointmentSummary, BindingResult result, @PathVariable("appId") int appId, @PathVariable("userId") String userId){
        UserService.updateUserInPosts(UserMapper.mapUserInPosts(appointmentSummary, userId), appId);
        /*UserService.updateDepartmentId(appointmentSummary.getDepartmentId(), Integer.parseInt(userId));*/
        return "redirect: /Hr/user/"+userId+"/update/Appointment";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Appointment/Add", method = RequestMethod.GET)
    public ModelAndView addApp(Principal principal, Model model, @PathVariable("userId") int userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/new/appointment");

        AppointmentSummary appointmentSummary = new AppointmentSummary();
        mav.addObject("appointmentVM", appointmentSummary);

        Map<Integer, String> appointmentTypes = new HashMap<Integer, String>();
        appointmentTypes.put(0, "--------");
        for (Appoint ap :
                Appoint.values()) {
            appointmentTypes.put(ap.getValue(), ap.name().replace(" ", " "));
        }
        model.addAttribute("types", appointmentTypes);

        Map<Integer, String> departmentsList = new HashMap<Integer, String>();
        departmentsList.put(0, "-------");
        for (DepartmentLocalizationsEntity dep :
                UserService.getDepartments(3)) {
            departmentsList.put(dep.getDepartmentId(), dep.getName());
        }
        model.addAttribute("departments", departmentsList);

        Map<Integer, String> posts = new HashMap<Integer, String>();
        posts.put(0, "------");
        for (PostLocalizationsEntity postLocalizationsEntity:
                UserService.getPostLocalizations(3)){
            posts.put(postLocalizationsEntity.getPostId(), postLocalizationsEntity.getName());
        }
        model.addAttribute("posts", posts);

        Map<Integer, String> externals = new HashMap<Integer, String>();
        externals.put(0, "------");
        for(ExternalLocalizationsEntity ext:
                UserService.getExternalLoc()){
            externals.put(ext.getId(), ext.getExternalName());
        }
        model.addAttribute("externals", externals);

        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Appointment/Add", method = RequestMethod.POST )
    public String addAppPost(Principal principal, @ModelAttribute  AppointmentSummary appointmentSummary, BindingResult result, @PathVariable("userId") String userId){
        UserService.insertUserInPosts(UserMapper.mapUserInPosts(appointmentSummary, userId));
        /*UserService.updateDepartmentId(appointmentSummary.getDepartmentId(), Integer.parseInt(userId));*/
        return "redirect: /Hr/user/"+userId+"/update/Appointment";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Jobexp/add", method = RequestMethod.GET)
    public ModelAndView addJob(Principal principal, Model model, @PathVariable("userId") int userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/new/job");
        mav = UP.includeUserProfile(mav, principal);

        Map<Integer, String> contracts = new HashMap<Integer, String>();
        for (Appoint app :
                Appoint.values()) {
            contracts.put(app.getValue(), app.name());
        }
        model.addAttribute("contracts", contracts);

        JobexpViewModel jobexpViewModel = new JobexpViewModel();
        model.addAttribute("jobexpVM", jobexpViewModel);

        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Jobexp/add", method = RequestMethod.POST )
    public String AddJobPost(Principal principal, @ModelAttribute  JobexpViewModel jobexpViewModel, BindingResult result, @PathVariable("userId") String userId){
        System.out.println("Job Exp: " + jobexpViewModel.toString());
        addJobExp(jobexpViewModel, userId);
        return "redirect: /Hr/user/"+userId+"/update/Jobexp";
    }
    private void addJobExp(JobexpViewModel jobexpViewModel, String userId) {
        int id = UserService.insertWorks(UserMapper.mapAddWorks(jobexpViewModel, userId));
        UserService.insertWorksLoc(UserMapper.mapAddWorksLoc(jobexpViewModel, id));
        //UserService.insertUsersFamilyInfoLocEn(UserMapper.mapAddFamilyLoc(familyProfile, id, 3));
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Jobexp/updateJob/{jobId}", method = RequestMethod.GET)
    public ModelAndView updateJob(Principal principal, Model model, @PathVariable("userId") int userId, @PathVariable("jobId") int jobId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/edit/job");

        JobexpViewModel jobexpViewModel = new JobexpViewModel();
        WorksEntity worksEntity = UserService.getWorkEntity(jobId);
        WorkLocalizationsEntity workLocalizationsEntity = UserService.getWorkLocal(worksEntity);
        jobexpViewModel.setStartDate(worksEntity.getStartDate());
        jobexpViewModel.setEndDate(worksEntity.getEndDate());
        jobexpViewModel.setContractType(worksEntity.getContractType());
        jobexpViewModel.setOrganization(workLocalizationsEntity.getOrganization());
        jobexpViewModel.setPosition(workLocalizationsEntity.getPost());
        model.addAttribute("jobexpVM", jobexpViewModel);
        
        Map<Integer, String> contractTypes = new HashMap<Integer, String>();
        for (Appoint app :
                Appoint.values()) {
            contractTypes.put(app.getValue(), app.name());
        }
        model.addAttribute("contractTypes", contractTypes);

        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Jobexp/updateJob/{jobId}", method = RequestMethod.POST )
    public String updateJobPost(Principal principal, @ModelAttribute  JobexpViewModel jobexpViewModel, BindingResult result, @PathVariable("userId") String userId, @PathVariable("jobId") int jobId){
        UserService.updateWorks(UserMapper.mapAddWorks(jobexpViewModel, userId), jobId);
        UserService.updateWorksLoc(UserMapper.mapAddWorksLoc(jobexpViewModel, jobId));
        return "redirect: /Hr/user/"+userId+"/update/Jobexp";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Train/add", method = RequestMethod.GET)
    public ModelAndView addTrain(Principal principal, Model model, @PathVariable("userId") int userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/new/train");

        TrainViewModel trainViewModel = new TrainViewModel();
        model.addAttribute("trainVM", trainViewModel);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Train/add", method = RequestMethod.POST )
    public String AddTrainPost(Principal principal, @ModelAttribute  TrainViewModel trainViewModel, BindingResult result, @PathVariable("userId") String userId){
        int id = UserService.insertTrainings(UserMapper.mapTrainings(trainViewModel, userId));
        System.out.println("ID= "+id);
        UserService.insertTrainingLoc(UserMapper.mapTrainingLoc(trainViewModel, id));
        return "redirect: /Hr/user/"+userId+"/update/Train";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Train/edit/{trainId}", method = RequestMethod.GET)
    public ModelAndView updateTrain(Principal principal, Model model, @PathVariable("userId") int userId, @PathVariable("trainId") int trainId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/edit/train");
        TrainViewModel trainViewModel = getTrainVM(trainId, userId);
        mav = UP.includeUserProfile(mav, principal);
        model.addAttribute("trainVM", trainViewModel);
        return mav;
    }
    private TrainViewModel getTrainVM(int trainId, int userId) {
        TrainViewModel trainViewModel = new TrainViewModel();
        TrainingsEntity trainingsEntity = UserService.getTrainingEntity(trainId);
        TrainingLocalizationsEntity trainingLocalizationsEntity = UserService.getTrainingLoc(trainingsEntity);

        // Creating TrainVM
        trainViewModel.setName(trainingLocalizationsEntity.getName());
        trainViewModel.setOrganization(trainingLocalizationsEntity.getOrganization());
        trainViewModel.setDateFrom(trainingsEntity.getDateFrom());
        trainViewModel.setDateTo(trainingsEntity.getDateTo());
        trainViewModel.setNumberOfHours(trainingsEntity.getNumberOfHours());
        trainViewModel.setMark(trainingsEntity.getMark());

        return trainViewModel;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Train/edit/{trainId}", method = RequestMethod.POST )
    public String updateTrainPost(Principal principal, @ModelAttribute  TrainViewModel trainViewModel, BindingResult result, @PathVariable("userId") String userId, @PathVariable("trainId") String trainId){
        UserService.updateTrainVM(UserMapper.mapTrainings(trainViewModel, userId), Integer.parseInt(trainId));
        return "redirect: /Hr/user/"+userId+"/update/Train";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Edu/AddLang", method = RequestMethod.GET)
    public ModelAndView addLang(Principal principal, Model model, @PathVariable("userId") int userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/new/edu/lang");
        Map<Integer, String> languageRanking = new HashMap<Integer, String>();
        for (Language_Ranking lang :
                Language_Ranking.values()) {
            languageRanking.put(lang.getCode(), lang.name());
        }
        model.addAttribute("rankings", languageRanking);

        Map<Integer, String> languages = new HashMap<Integer, String>();
        for (LanguageLocalizationsEntity lang :
                UserService.getLanguageLocalizations(3)) {
            languages.put(lang.getLanguageId(), lang.getName());
        }
        model.addAttribute("languages", languages);

        LanguageSummary languageSummary = new LanguageSummary();
        model.addAttribute("lang", languageSummary);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Edu/AddLang", method = RequestMethod.POST )
    public String AddLangPost(Principal principal, @ModelAttribute  LanguageSummary languageSummary, BindingResult result, @PathVariable("userId") String userId){
        System.out.println("Languages: " + languageSummary.toString());
        UserService.insertUserInLanguages(UserMapper.mapUserInLanguages(languageSummary, userId));

        return "redirect: /Hr/user/"+userId+"/update/Edu";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Edu/EditLang/{langId}", method = RequestMethod.GET)
    public ModelAndView editLang(Principal principal, Model model, @PathVariable("userId") int userId, @PathVariable("langId") int langId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/edit/edu/lang");
        Map<Integer, String> languageRanking = new HashMap<Integer, String>();
        for (Language_Ranking lang :
                Language_Ranking.values()) {
            languageRanking.put(lang.getCode(), lang.name());
        }
        model.addAttribute("rankings", languageRanking);

        UserInLanguagesEntity languageSummary = UserService.getUserInLanguage(langId);
        model.addAttribute("lang", languageSummary);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Edu/EditLang/{langId}", method = RequestMethod.POST )
    public String EditLangPost(Principal principal, @ModelAttribute  UserInLanguagesEntity languagesEntity, BindingResult result, @PathVariable("userId") String userId, @PathVariable("langId") String langId){
        System.out.println("Languages: "+languagesEntity);
        UserService.updateUserInLanguages(languagesEntity, userId, langId);

        return "redirect: /Hr/user/"+userId+"/update/Edu";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Edu/AddEdu", method = RequestMethod.GET)
    public ModelAndView addEdu(Principal principal, Model model, @PathVariable("userId") int userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/new/edu/edu");

        Educations education = new Educations();
        model.addAttribute("edu", education);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Edu/AddEdu", method = RequestMethod.POST )
    public String AddEduPost(Principal principal, @ModelAttribute  Educations educations, BindingResult result, @PathVariable("userId") String userId){
        System.out.println("EDUCATIONS: " + educations.toString());
        int eduId = UserService.insertEducations(UserMapper.mapEducations(educations, userId));
        UserService.insertEducationLocalizations(UserMapper.mapEducationLocalizations(educations, eduId));

        return "redirect: /Hr/user/"+userId+"/update/Edu";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Edu/EditEdu/{eduId}", method = RequestMethod.GET)
    public ModelAndView EditEdu(Principal principal, Model model, @PathVariable("userId") int userId, @PathVariable("eduId") int eduId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/edit/edu/edu");

        Educations education = getEducations(userId, eduId);
        model.addAttribute("edu", education);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }
    private Educations getEducations(int userId, int eduId) {
        Educations educations = new Educations();
        EducationsEntity educationsEntity = UserService.getEducationById(eduId);
        educations.setStartDate(educationsEntity.getStartDate());
        educations.setEndDate(educationsEntity.getEndDate());

        EducationLocalizationsEntity educationLocalizationsEntity = UserService.getEducationLocalization(educationsEntity, 3);
        educations.setName(educationLocalizationsEntity.getName());
        educations.setMajor(educationLocalizationsEntity.getMajor());
        educations.setDegree(educationLocalizationsEntity.getDegree());

        return educations;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Edu/EditEdu/{eduId}", method = RequestMethod.POST )
    public String EditEduPost(Principal principal, @ModelAttribute  Educations educations, BindingResult result, @PathVariable("userId") String userId, @PathVariable("eduId") int eduId){
        UserService.updateEducations(UserMapper.mapEducations(educations, userId), eduId);
        UserService.updateEducationLocalizations(UserMapper.mapEducationLocalizations(educations, eduId));

        return "redirect: /Hr/user/"+userId+"/update/Edu";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Edu/AddCert", method = RequestMethod.GET)
    public ModelAndView addCert(Principal principal, Model model, @PathVariable("userId") int userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/new/edu/cer");

        Certificates certificates = new Certificates();
        model.addAttribute("cert", certificates);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Edu/AddCert", method = RequestMethod.POST )
    public String AddCertPost(Principal principal, @ModelAttribute  Certificates certificates, BindingResult result, @PathVariable("userId") String userId){
        int certId = UserService.insertCertificates(UserMapper.mapCertificates(certificates, userId, 1));
        UserService.insertCertificateLocalizations(UserMapper.mapCertificateLocalizations(certificates, certId),3);
        return "redirect: /Hr/user/"+userId+"/update/Edu";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Edu/EditCert/{certId}", method = RequestMethod.GET)
    public ModelAndView EditCert(Principal principal, Model model, @PathVariable("userId") int userId, @PathVariable("certId") int certId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/edit/edu/cer");

        Certificates certificate = getCertificate(userId, certId);
        model.addAttribute("cert", certificate);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }
    private Certificates getCertificate(int userId, int certId) {
        Certificates certificates = new Certificates();
        CertificatesEntity certificatesEntity = UserService.getCertificate(certId);
        CertificateLocalizationsEntity certificateLocalizationsEntity = UserService.getCertificatesLoc(certificatesEntity, 3);
        certificates.setName(certificateLocalizationsEntity.getName());
        certificates.setOrganization(certificateLocalizationsEntity.getOrganization());
        certificates.setDateTime(certificatesEntity.getDateTime());
        certificates.setMark(certificatesEntity.getMark());
        certificates.setPassed(certificatesEntity.getPass());
        certificates.setNumber(certificatesEntity.getNumber());
        return certificates;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Edu/EditCert/{certId}", method = RequestMethod.POST )
    public String EditCertPost(Principal principal, @ModelAttribute  Certificates certificate, BindingResult result, @PathVariable("userId") String userId, @PathVariable("certId") int certId){
        UserService.updateCertificates(UserMapper.mapCertificates(certificate, userId, 1), certId);
        UserService.updateCertificateLocalizations(UserMapper.mapCertificateLocalizations(certificate, certId));

        return "redirect: /Hr/user/"+userId+"/update/Edu";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Edu/AddLangScore", method = RequestMethod.GET)
    public ModelAndView addLangScore(Principal principal, Model model, @PathVariable("userId") int userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/new/edu/langScore");

        Certificates certificates = new Certificates();
        model.addAttribute("cert", certificates);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Edu/AddLangScore", method = RequestMethod.POST )
    public String AddLangScorePost(Principal principal, @ModelAttribute  Certificates certificates, BindingResult result, @PathVariable("userId") String userId){
        int certId = UserService.insertCertificates(UserMapper.mapCertificates(certificates, userId, 2));
        UserService.insertCertificateLocalizations(UserMapper.mapCertificateLocalizations(certificates, certId),3);
        return "redirect: /Hr/user/"+userId+"/update/Edu";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Edu/EditLangScore/{certId}", method = RequestMethod.GET)
    public ModelAndView EditLangScore(Principal principal, Model model, @PathVariable("userId") int userId, @PathVariable("certId") int certId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/edit/edu/langScore");

        Certificates certificate = getCertificate(userId, certId);
        model.addAttribute("cert", certificate);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Edu/EditLangScore/{certId}", method = RequestMethod.POST )
    public String EditLangScore(Principal principal, @ModelAttribute  Certificates certificate, @PathVariable("userId") String userId, @PathVariable("certId") int certId){
        UserService.updateCertificates(UserMapper.mapCertificates(certificate, userId, 2), certId);
        UserService.updateCertificateLocalizations(UserMapper.mapCertificateLocalizations(certificate, certId));

        return "redirect: /Hr/user/"+userId+"/update/Edu";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Salary/addSal", method = RequestMethod.GET)
    public ModelAndView addSalary(Principal principal, Model model, @PathVariable("userId") int userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/new/salary");
        List<CurrencyLocalizationsEntity> currencyLocalizationsEntities = UserService.getCurrencies(3);
        Map<Integer, String> currencies = new HashMap<Integer, String>();
        for (CurrencyLocalizationsEntity cur :
                currencyLocalizationsEntities) {
            currencies.put(cur.getCurrencyId(), cur.getName());
        }
        model.addAttribute("currencies", currencies);
        SalaryVewModel salaryVM = new SalaryVewModel();
        model.addAttribute("salaryVM", salaryVM);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{userId}/update/Salary/addSal", method = RequestMethod.POST )
    public String AddSalaryPost(Principal principal, @ModelAttribute  SalaryVewModel salaryVewModel, BindingResult result, @PathVariable("userId") String userId){
        UserService.insertSalary(UserMapper.mapSalaryEntity(salaryVewModel, userId));
        return "redirect: /Hr/user/"+userId+"/update/Salary";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Salary/updateSal/{salId}", method = RequestMethod.GET)
    public ModelAndView updateSalary(Principal principal, Model model, @PathVariable("userId") String userId, @PathVariable("salId") int salId){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/edit/salary");
        mav = UP.includeUserProfile(mav, principal);
        SalaryHistoriesEntity salaryVM = UserService.getSalary(salId);
        model.addAttribute("salaryVM", salaryVM);
        List<CurrencyLocalizationsEntity> currencyLocalizationsEntities = UserService.getCurrencies(3);
        Map<Integer, String> currencies = new HashMap<Integer, String>();
        for (CurrencyLocalizationsEntity cur :
                currencyLocalizationsEntities) {
            currencies.put(cur.getCurrencyId(), cur.getName());
        }
        model.addAttribute("currencies", currencies);
        return mav;
    }
    @RequestMapping ( value = "/Hr/user/{userId}/update/Salary/updateSal/{salId}", method = RequestMethod.POST )
    public String UpdateSalaryPost(Principal principal, @ModelAttribute  SalaryHistoriesEntity salaryVewModel,  @PathVariable("userId") String userId, @PathVariable("salId") String salId){
        System.out.println("SALLARY: " + salaryVewModel);
        UserService.updateSalaryPost(salaryVewModel, Integer.parseInt(salId));
        return "redirect: /Hr/user/"+userId+"/update/Salary";
    }

    @RequestMapping(value = "/Hr/user/{id}/Docs/Download/{docId}")
    public String downloadFileView(Principal principal, HttpServletResponse response, @PathVariable("id") int id, @PathVariable("docId") int docId) throws IOException {
        File file = null;
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



        return "redirect: /Hr/Docs/";
    }

    @RequestMapping(value = "/Hr/user/{id}/update/Docs/Download/{docId}")
    public String downloadFile(Principal principal, HttpServletResponse response, @PathVariable("id") int id, @PathVariable("docId") int docId) throws IOException {
        File file = null;
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



        return "redirect: /Hr/Docs/";
    }

    @RequestMapping(value = "/Hr/user/{id}/update/Docs/Del/{docId}")
    public String deleteFile(Principal principal, @PathVariable("id") int id, @PathVariable("docId") int docId) throws IOException {

        String path = UserService.getDocumentByDocId(docId).getLink();
        File file = new File(path);
        if(file.delete())
            System.out.println(file.getName() + " is deleted!");
        else
            System.out.println("Delete operation is failed.");
        UserService.deleteDocument(docId);

        return "redirect: /Hr/user/{id}/update/Docs";
    }

    @RequestMapping(value = "/Hr/user/{id}/update/Docs", method = RequestMethod.POST)
    public String uploadingFile(FileBucket fileBucket, @PathVariable("id") int id) throws IOException {

        MultipartFile multipartFile = fileBucket.getFile();

        //Now do something with file...
        File f =  new File("C:/files/documents/" + String.format("%05d", id) );
        boolean b = f.mkdir();
        if (b)
        {
            System.out.println("DIRECTORY CREATED SECCESFULLY");
        }
        FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File("C:/files/documents/" + String.format("%05d", id) + "/" + fileBucket.getFile().getOriginalFilename())) ;
        DocsViewModel docsViewModel = new DocsViewModel();
        docsViewModel.setUserId(id);
        docsViewModel.setDocumentType(fileBucket.getType());
        docsViewModel.setLink("C:/files/documents/" + String.format("%05d", id) + "/" + fileBucket.getFile().getOriginalFilename());
        docsViewModel.setName(fileBucket.getName());
        UserService.insertDocumentUser(UserMapper.mapDocuments(docsViewModel));
        String fileName = multipartFile.getOriginalFilename();
        return "redirect: /Hr/user/"+id+"/update/Docs";

    }

    @RequestMapping(value = "/Hr/user/{id}/update/{path}", method = RequestMethod.GET)
    @ResponseBody public ModelAndView UpdateInfo(Principal principal, Model model, @PathVariable("id") int id, @PathVariable("path") String path){
        ModelAndView mav = new ModelAndView();
        ProfileViewModel userProfile = getProfileById(id);
        ProfileViewModel userProfile2 = UserController.getProfileByUsername(principal.getName());
        model.addAttribute("fullName", userProfile.getFirstName()[2] + " "+ userProfile.getLastName()[2]);
        model.addAttribute("jobTitle", userProfile.getJobTitle());
        model.addAttribute("external", userProfile.getExternal());
        model.addAttribute("userProfile", userProfile2);
        model.addAttribute("userProfile", UP.includeUserProfile(mav, principal));
        if(path.compareTo("Geninfo")==0) {
            mav.setViewName("Home/editmenu/edit/geninfo");
            userProfile = getProfileById(id);
            model.addAttribute("person",  userProfile);
            // Getting list of departments and send to view
            Map<Integer, String> deps = new HashMap<Integer, String>();
            for (DepartmentLocalizationsEntity dep :
                    UserService.getDepartments(3)) {
                deps.put(dep.getDepartmentId(), dep.getName());
            }
            model.addAttribute("departments",  deps);

            //Getting positions list from RoleLocalizations
            Map<Integer, String> positions = new HashMap<Integer, String>();
            for (RoleLocalizationsEntity pos :
                    UserService.getRolesLoc()) {
                positions.put(pos.getRoleId(), pos.getName());
            }
            model.addAttribute("roles",  positions);

            //Getting jobTitles list from RoleLocalizations
            Map<Integer, String> jobTitles = new HashMap<Integer, String>();
            for (PostLocalizationsEntity pos :
                    UserService.getPostLocalizations(3)) {
                jobTitles.put(pos.getPostId(), pos.getName());
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
        else if(path.compareTo("Appointment")==0) {
            mav.setViewName("Home/editmenu/appointmentrec");
            AppointmentrecViewModel appointmentrecViewModels = new AppointmentrecViewModel();
            try {
                appointmentrecViewModels = getAppointmentByUsername(userProfile.getUsername());
            } catch (Exception e){
                e.printStackTrace();
            }
            model.addAttribute("userProfileUser", UserController.getProfileByUsername(userProfile.getUsername()));
            model.addAttribute("appointmentrecVM", appointmentrecViewModels);

            Map<Integer, String> contracts = new HashMap<Integer, String>();
            for (Appoint a :
                    Appoint.values()) {
                contracts.put(a.getValue(), a.name().replace("_", " "));
            }
            model.addAttribute("contracts", contracts);

            Map<Integer, String> departments = new HashMap<Integer, String>();
            for (DepartmentLocalizationsEntity dep :
                    UserService.getDepartments()) {
                departments.put(dep.getDepartmentId(), dep.getName());
            }
            model.addAttribute("departments", departments);

            Map<Integer, String> posts = new HashMap<Integer, String>();
            for (PostLocalizationsEntity post :
                    UserService.getPostLocalizations(3)) {
                posts.put(post.getPostId(), post.getName());
            }
            model.addAttribute("posts", posts);

            Map<Integer, String> externals = new HashMap<Integer, String>();
            for(ExternalLocalizationsEntity ext:
                    UserService.getExternalLoc()){
                externals.put(ext.getId(), ext.getExternalName());
            }
            model.addAttribute("externals", externals);

            return mav;
        }
        else if(path.compareTo("Jobexp")==0) {
            mav.setViewName("Home/editmenu/job");
            List<JobexpViewModel> jobexpViewModel = new LinkedList<JobexpViewModel>();
            try {
                jobexpViewModel = UserController.getJobExperience(userProfile.getUsername());
            } catch (Exception e){
                e.printStackTrace();
            }
            model.addAttribute("jobexpVM", jobexpViewModel);
            Map<Integer, String> contractTypes = new HashMap<Integer, String>();
            for (Appoint ap :
                    Appoint.values()) {
                contractTypes.put(ap.getValue(), ap.name());
            }
            model.addAttribute("contractTypes", contractTypes);
            return mav;
        }
        else if(path.compareTo("Salary")==0) {
            mav.setViewName("Home/editmenu/salary");
            List<SalaryVewModel> salaryVewModel = UserController.getSalaryByUser(UserService.getUserById(id).getUserName());
            model.addAttribute("salaryVM", salaryVewModel);
            return mav;
        }
        else if(path.compareTo("Edu")==0) {
            mav.setViewName("Home/editmenu/edu");

            EduViewModel eduViewModel = UserController.getEducationByUsername(userProfile.getUsername());

            model.addAttribute("eduVM", eduViewModel);
            return mav;
        }
        else if(path.compareTo("Train")==0) {
            mav.setViewName("Home/editmenu/train");
            List<TrainViewModel> trainingRecord = UserController.getTrainingRecord(userProfile.getUsername());
            model.addAttribute("trainVM", trainingRecord);
            return mav;
        }
        else if(path.compareTo("Docs")==0){
            System.out.println(path);
            mav.setViewName("Home/editmenu/Docs");
            model.addAttribute("person",  userProfile);

            Map<Integer, String> docType = new HashMap<Integer, String>();
            for (Document_Type doc :
                    Document_Type.values()) {
                docType.put(doc.getValue(), doc.name());
            }
            model.addAttribute("docType", docType);

            List<DocumentsEntity> documentsEntities = UserService.getDocuments(UserService.getUserByUsername(UserService.getUsernameById(id)));
            List<DocumentsEntity> documentsEntitiesNew = new LinkedList<DocumentsEntity>();
            DocsViewModel docsViewModel = new DocsViewModel();
            for (DocumentsEntity docs :
                    documentsEntities) {
                if (docs.getDocumentType() != 7)
                    documentsEntitiesNew.add(docs);
            }
            model.addAttribute("docs", docsViewModel);
            model.addAttribute("documents", documentsEntitiesNew);
            FileBucket fileModel = new FileBucket();
            model.addAttribute("fileBucket", fileModel);

            return mav;

        }
        else if(path.compareTo("ChangePass")==0) {
            mav.setViewName("user/changepassuser");

            ChangepassViewModel changepassViewModel = new ChangepassViewModel ();
            mav = UP.includeUserProfile(mav, principal);
            mav.addObject("changepassVM", changepassViewModel);
            mav.addObject("userChange", "1");
            return mav;
        }
        else if(path.compareTo("Head")==0){
            mav.setViewName("Home/editmenu/head");
            List<PersonInfo> users = getUsers();
            mav.addObject("hrUserslistVM", users);
            mav.addObject("person", new PersonInfo());
            return mav;
        }
        else{

        }
        mav.setViewName("Home/editmenu/edit/geninfo");
        HrJobexpViewModel hrjobexpViewModel = new HrJobexpViewModel();
        mav.addObject("hrjobexpVM", hrjobexpViewModel);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{id}/update/{path}", method = RequestMethod.POST )
    public String UpdateInfo(Principal principal, @Valid @ModelAttribute  ProfileViewModel person, FileBucket fileBucket, @PathVariable String path, BindingResult result, @PathVariable("id") String id) throws IOException {

        if(result.hasErrors()) {
            return "Hr/user/"+id+"/update"+path;
        }
        updateDBGenInfo(person);
        return "redirect: /Hr/user/"+id+"/update/"+path;
    }
    public static AppointmentrecViewModel getAppointmentByUsername(String userName) {
        AppointmentrecViewModel returning = new AppointmentrecViewModel();

        // Getting data from users db
        UsersEntity user = UserService.getUserByUsername(userName);

        List<UserInPostsEntity> usersInPost = UserService.getUserInPost(user);

        for (UserInPostsEntity uip :
                usersInPost) {
            returning.addAppointSummary(uip.getDateFrom(), uip.getContractType(), uip.getPostId(), uip.getId(), uip.getDepartmentId(), user.getRoleId(), uip.getExternalId());
        }

        return returning;
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Geninfo/updateFam/{famId}/", method = RequestMethod.GET)
    public ModelAndView UpdateFamInfo(Principal principal, @PathVariable("userId") int userId, @PathVariable("famId") int famId){
        FamilyMember familyProfile = getUserFamily(userId, famId);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/edit/faminfo");
        mav.addObject("family", familyProfile);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Geninfo/updateFam/{famId}/", method = RequestMethod.POST)
    public String UpdateFamInfoPost(FamilyMember familyProfile, @PathVariable("userId") String userId, @PathVariable("famId") String famId){
        UserService.updateUsersFamilyInfo(familyProfile);
        UserService.updateUsersFamilyInfoLocEn(familyProfile);
        UserService.updateUsersFamilyInfoLocRu(familyProfile);
        UserService.updateUsersFamilyInfoLocUz(familyProfile);
        return "redirect: /Hr/user/"+userId+"/update/Geninfo";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Geninfo/deleteFam/{famId}/", method = RequestMethod.GET)
    public String DeleteFamInfoPost(Model model, FamilyMember familyProfile, @PathVariable("userId") String userId, @PathVariable("famId") String famId){
        UserService.deleteUsersFamilyInfoLoc(famId);
        UserService.deleteUsersFamilyInfo(famId);
        return "redirect: /Hr/user/"+userId+"/update/Geninfo";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Geninfo/addFam", method = RequestMethod.GET)
    public ModelAndView addFamGet(Principal principal, Model model, @PathVariable("userId") int userId){
        ModelAndView mav = new ModelAndView();
        FamilyMember familyProfile = new FamilyMember();
        model.addAttribute("family", familyProfile);
        mav.addObject("SystemRole", UserService.getUserByUsername(principal.getName()).getRoleId());
        mav = UP.includeUserProfile(mav, principal);
        mav.setViewName("Home/editmenu/new/newfaminfo");
        return mav;
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Geninfo/addFam", method = RequestMethod.POST)
    public String addFamPost(Model model, FamilyMember familyProfile, @PathVariable("userId") String userId){

        int id = UserService.insertUsersFamilyInfo(UserMapper.mapAddFamily(familyProfile, userId));

        UserService.insertUsersFamilyInfoLocEn(UserMapper.mapAddFamilyLoc(familyProfile, id, 3));
        UserService.insertUsersFamilyInfoLocRu(UserMapper.mapAddFamilyLoc(familyProfile, id, 1));
        UserService.insertUsersFamilyInfoLocUz(UserMapper.mapAddFamilyLoc(familyProfile, id, 2));
        return "redirect: /Hr/user/"+userId+"/update/Geninfo";
    }
    private FamilyMember getUserFamily(int userId, int famId) {
        FamilyMember familyMember = new FamilyMember(3);
        UsersEntity usersEntity = UserService.getUserById(userId);
        List<FamilyInfosEntity> famMem = UserService.getFamilyInfos(usersEntity);
        for (FamilyInfosEntity fam :
                famMem) {
            if(fam.getId()==famId) {
                familyMember.setDateOfBirth(fam.getDateOfBirth());
                familyMember.setId(fam.getId());
                List<FamiliyInfoLocalizationsEntity> famLoc = UserService.getFamilyInfosLoc(famId);
                String[] lastName = new String[3];
                String[] firstName = new String[3];
                String[] jobTitle = new String[3];
                String[] relation = new String[3];

                for (FamiliyInfoLocalizationsEntity fLoc :
                        famLoc) {
                    lastName[fLoc.getLanguageId()-1] = fLoc.getLastName();
                    firstName[fLoc.getLanguageId()-1] = fLoc.getFirstName();
                    jobTitle[fLoc.getLanguageId()-1] = fLoc.getJobTitle();
                    relation[fLoc.getLanguageId()-1] = fLoc.getRelation();
                }
                familyMember.setLastName(lastName);
                familyMember.setFirstName(firstName);
                familyMember.setJobTitle(jobTitle);
                familyMember.setRelation(relation);
            }
        }
        return familyMember;
    }

    @RequestMapping ( value = "/Hr/user/{id}/disable/", method = RequestMethod.GET )
    public String DisableUser(Principal principal, @PathVariable("id") int id){

        UserService.disableUser(id);
        return "redirect: /Hr/Userslist";
    }

    @RequestMapping ( value = "/Hr/user/{id}/enable/", method = RequestMethod.GET )
    public String EnableUser(Principal principal, @PathVariable("id") int id){

        UserService.enableUser(id);
        return "redirect: /Hr/Userslist";
    }

    @RequestMapping ( value = "/Hr/Generate/{docId}/{userId}", method = RequestMethod.POST )
    public ResponseEntity<byte[]> GenerateDoc(HttpServletRequest request, HttpServletResponse response, Principal principal, @PathVariable("docId") int docId, @PathVariable("userId") int userId){
        ProfileViewModel user = getProfileById(userId);
        System.out.printf("Printing document: "+docId);
        ResponseEntity<byte[]> res=null;
        try {
            return generateDocument(user, docId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping ( value = "/Hr/Generate", method = RequestMethod.POST, params = "Save")
    public ResponseEntity<byte[]> GenerateSave(@ModelAttribute("docs") DocsViewModel docs, HttpServletRequest request, HttpServletResponse response, Principal principal){

        ProfileViewModel user = UserController.getProfileByUsername(UserService.getUsernameById(docs.getUserId()));
        ResponseEntity<byte[]> res=null;
        try {
            return generateDocument(user, docs.getDocId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
        return res;

    }

    @RequestMapping ( value = "/Hr/Generate", method = RequestMethod.POST, params = "Print")
    public String GeneratePrint(@ModelAttribute("docs") DocsViewModel docs, HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException, XDocReportException {
        ProfileViewModel user = UserController.getProfileByUsername(UserService.getUsernameById(docs.getUserId()));
        String path = null;
        try {
            path = printDocument(user, docs.getDocId());
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return "redirect: /temp/"+path;
    }

    @RequestMapping(value = "/Hr/user/{id}/update/UploadPic", method = RequestMethod.GET)
    public ModelAndView uploading(Principal principal, Model model, @PathVariable("id") int id) {
        File file = new File(uploadingdir);
        model.addAttribute("files", file.listFiles());
        ModelAndView mav = new ModelAndView();
        mav.addObject("id", id);
        mav.setViewName("Home/editmenu/FileUploadForm");
        ProfileViewModel userProfile2 = getProfileById(id);
        model.addAttribute("fullName", userProfile2.getFirstName()[2] + " "+ userProfile2.getLastName()[2]);
        model.addAttribute("jobTitle", userProfile2.getJobTitle());
        model.addAttribute("external", userProfile2.getExternal());
        mav.addObject("userProfile", UserController.getProfileByUsername(principal.getName()));
        return mav;
    }

    @RequestMapping(value = "/Hr/user/{id}/update/UploadPic", method = RequestMethod.POST)
    public String uploadingPost(@RequestParam MultipartFile fileUpload, @PathVariable("id") int id) throws IOException {
        String sId=null;
        sId = String.format("%05d", id);
        String sId2 =sId + ".jpg";
        File file = new File("C:/files/photos/" + sId2);
        fileUpload.transferTo(file);
        return "redirect:/";
    }

    @RequestMapping(value = "Hr/user/{userId}/update/ChangePass", method = RequestMethod.POST)
    public String ChangePass2(Principal principal, @RequestParam("password") String pass, @RequestParam("repeatPassword") String repPass, @PathVariable("userId") int userId){
        ModelAndView model = new ModelAndView();
        UsersEntity user = UserService.getUserById(userId);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(pass);
        System.out.println(pass);
        String passwordNow = UserService.getUserByUsername(principal.getName()).getPasswordHash();
        UserService.updatePassword(user, hashedPassword);
        System.out.println("Password updated!");
        return "redirect: /";
    }

    @RequestMapping(value = "Hr/changepass", method = RequestMethod.GET)
    public ModelAndView ChangePass(Principal principal){
        ModelAndView mav = new ModelAndView();
        ChangepassViewModel changepassViewModel = new ChangepassViewModel ();
        mav.setViewName("user/changepass");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("changepassVM", changepassViewModel);
        return mav;
    }

    public static int updateDBGenInfo(ProfileViewModel person) {
        int res = 0;
        try {
            UserService.updateUsersEntity(person);
            res = UserService.updateUsersLocEntityEn(person);
            res = UserService.updateUsersLocEntityRu(person);
            res = UserService.updateUsersLocEntityUz(person);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return res;
        }

    }

    public static List<PersonInfo> getUsers() {
        List<PersonInfo> returning = new LinkedList<PersonInfo>();
        PersonInfo userProfile;
        List<UsersEntity> usersEntityList = UserService.getAllUsers();


        for (UsersEntity userEntity:
                usersEntityList) {
            userProfile = new PersonInfo();
            // Getting data from users db
            UsersEntity user = UserService.getUserByUsername(userEntity.getUserName());
            if(user.getId()!=0) {
                userProfile.setId(String.format("%05d", user.getId()));
                userProfile.setRoleId(user.getRoleId());
                if (user.getChiefId() != null)
                    userProfile.setChiefId(user.getChiefId());
                userProfile.setUserName(user.getUserName());

                // Getting its localization data
                try {
                    if (UserService.getUserLocByUserId(user.getId(), 3) != null) {
                        UserLocalizationsEntity userLoc = UserService.getUserLocByUserId(user.getId(), 3);
                        userProfile.setFirstName(userLoc.getFirstName());
                        userProfile.setLastName(userLoc.getLastName());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                userProfile.setEnabled(user.isEnabled());
                returning.add(userProfile);
            }
        }
        return returning;

    }

    // Getting user from ID
    public static ProfileViewModel getProfileById(int id){
        ProfileViewModel returning = new ProfileViewModel();
        // Getting data from users db
        UsersEntity user = UserService.getUserById(id);
        returning.setUsername(user.getUserName());
        // Getting its localization data
        List<UserLocalizationsEntity> userLocalizationsEntities = UserService.getUserLocByUserId(user.getId());

        for (UserLocalizationsEntity ul:
                userLocalizationsEntities ) {
            returning.addData1(String.format("%05d", user.getId()), ul.getFirstName(), ul.getLastName(), ul.getFatherName(), ul.getAddress(), ul.getLanguageId());
        }
        //Getting department
        returning.setDepartmentId(user.getDepartmentId());
        /*try {
            if(UserService.getDepartments().get(user.getDepartmentId()-1).getName()!=null)
                returning.setDepartment(UserService.getDepartments().get(user.getDepartmentId()-1).getName());
        }catch (Exception e){
            e.printStackTrace();
        }*/
        //Getting Position from user_in_roles
        returning.setRoleId(user.getRoleId());
        /*try {
            if (UserService.getRoleLoc(user).getName()!=null)
                returning.setPosition(UserService.getRoleLoc(user).getName());
        }catch (Exception e){
            e.printStackTrace();
        }*/
        //Getting is political
        try {
            if (user.getPolitical()!=null)
                returning.setPolitical(user.getPolitical());
        }catch (Exception e){
            e.printStackTrace();
        }
        try {

            /*//Getting Joint Type
            returning.setJointType(Appoint.values()[getMax(UserService.getUserInPost(user)).getContractType() - 1].toString());*/
            if(UserService.getUserInPost(user).size()!=0) {
                returning.setPostId(UserService.getUserInPost(user).get(0).getPostId());
                returning.setJobTitle(UserService.getJobTitle(UserService.getUserInPost(user).get(0).getPostId(), 3).getName());
                returning.setExternal(UserService.getExternalLoc(UserService.getUserInPost(user).get(0).getExternalId()));
            }
            //Getting status
            List<StatusLocalizationsEntity> statuses = UserService.getStatuses();

            returning.setStatusId(user.getStatusId());

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
                familyLoc2 = UserService.getFamilyInfosLoc(fie.getId());

                FamilyMember familyMember = new FamilyMember(familyLoc2.size());
                for (FamiliyInfoLocalizationsEntity faInLoEn :
                        familyLoc2) {
                    familyMember.add(faInLoEn.getRelation(), faInLoEn.getLastName(), faInLoEn.getFirstName(), fie.getDateOfBirth(), faInLoEn.getJobTitle(), faInLoEn.getLanguageId(), faInLoEn.getFamilyInfoid());
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

    private ResponseEntity<byte[]> generateDocument(ProfileViewModel user, int docId) throws IOException, XDocReportException {
        DocumentsEntity documentsEntity = UserService.getDocumentByDocId(docId);
        String fileName = documentsEntity.getName().replaceAll(" ", "_");

        String templatePath = documentsEntity.getLink();
        Map<String, Object> nonImageVariableMap = new HashMap<String, Object>();
        Date date = new Date();

        // Saving data for generating document

        nonImageVariableMap.put("dateNow", new SimpleDateFormat("d MMMMMMMM yyyy", Locale.ENGLISH).format(date));
        if(user.getFirstName()[2]!=null || user.getLastName()[2]!=null)
            nonImageVariableMap.put("nameEn", user.getFirstName()[2] + " "+ user.getLastName()[2]);
        else
            nonImageVariableMap.put("nameEn", " ");

        if(user.getFirstName()[0]!=null || user.getLastName()[0]!=null)
            nonImageVariableMap.put("nameRu", user.getFirstName()[0] + " "+ user.getLastName()[0]);
        else
            nonImageVariableMap.put("nameRu", " ");

        if(user.getFirstName()[1]!=null || user.getLastName()[1]!=null)
            nonImageVariableMap.put("nameUz", user.getFirstName()[1] + " "+ user.getLastName()[1]);
        else
            nonImageVariableMap.put("nameUz", " ");

        if(user.getJobTitle()!=null)
            nonImageVariableMap.put("jobTitle", user.getJobTitle());
        else
            nonImageVariableMap.put("jobTitle", " ");
        System.out.printf("Entry date: " + user.getEntryDate());

        if(user.getEntryDate()!=null)
            nonImageVariableMap.put("hiringDate", new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH).format(user.getEntryDate()));
        else
            nonImageVariableMap.put("hiringDate", " ");
        Map<String, String> imageVariablesWithPathMap =new HashMap<String, String>();

        String familyMembersEn = "";
        for (FamilyMember famMember:
                user.getFamilyLoc()) {
            familyMembersEn += ", "+famMember.getRelation()[2] + "(" + famMember.getLastName()[2]+" " + famMember.getFirstName()[2]+")";
        }
        String familyMembersRu = "";
        for (FamilyMember famMember:
                user.getFamilyLoc()) {
            familyMembersRu += famMember.getRelation()[0] + "(" + famMember.getLastName()[0]+" " + famMember.getFirstName()[0]+"), ";
        }
        String familyMembersUz = "";
        for (FamilyMember famMember:
                user.getFamilyLoc()) {
            familyMembersUz += famMember.getRelation()[1] + "(" + famMember.getLastName()[1]+" " + famMember.getFirstName()[1]+"), ";
        }


        nonImageVariableMap.put("familyMembersEn", familyMembersEn);
        nonImageVariableMap.put("familyMembersRu", familyMembersRu);
        nonImageVariableMap.put("familyMembersUz", familyMembersUz);


        System.out.println("Writing file from template");
        DocxDocumentMergerAndConverter docxDocumentMergerAndConverter = new DocxDocumentMergerAndConverter();
        byte[] mergedOutput = docxDocumentMergerAndConverter.mergeAndGenerateOutput(templatePath, TemplateEngineKind.Freemarker, nonImageVariableMap, imageVariablesWithPathMap);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/docx"));

        String filename = fileName+user.getFirstName()[2] + "_"+ user.getLastName()[2]+"_"+new SimpleDateFormat("d-MMMMMMMM-yyyy", Locale.ENGLISH).format(date)+".docx";

        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        assertNotNull(mergedOutput);
        ResponseEntity<byte[]> response2 = new ResponseEntity<byte[]>(mergedOutput, headers, HttpStatus.OK);

        return response2;

    }

    private String printDocument(ProfileViewModel user, int docId) throws IOException, XDocReportException, TransformerException, ParserConfigurationException {
        DocumentsEntity documentsEntity = UserService.getDocumentByDocId(docId);
        String fileName = documentsEntity.getName().replaceAll(" ", "_");

        String templatePath = documentsEntity.getLink();
        Map<String, Object> nonImageVariableMap = new HashMap<String, Object>();
        Date date = new Date();

        // Saving data for generating document

        nonImageVariableMap.put("dateNow", new SimpleDateFormat("d MMMMMMMM yyyy", Locale.ENGLISH).format(date));
        if(user.getFirstName()[2]!=null || user.getLastName()[2]!=null)
            nonImageVariableMap.put("nameEn", user.getFirstName()[2] + " "+ user.getLastName()[2]);
        else
            nonImageVariableMap.put("nameEn", " ");

        if(user.getFirstName()[0]!=null || user.getLastName()[0]!=null)
            nonImageVariableMap.put("nameRu", user.getFirstName()[0] + " "+ user.getLastName()[0]);
        else
            nonImageVariableMap.put("nameRu", " ");

        if(user.getFirstName()[1]!=null || user.getLastName()[1]!=null)
            nonImageVariableMap.put("nameUz", user.getFirstName()[1] + " "+ user.getLastName()[1]);
        else
            nonImageVariableMap.put("nameUz", " ");

        if(user.getJobTitle()!=null)
            nonImageVariableMap.put("jobTitle", user.getJobTitle());
        else
            nonImageVariableMap.put("jobTitle", " ");
        System.out.printf("Entry date: " + user.getEntryDate());

        if(user.getEntryDate()!=null)
            nonImageVariableMap.put("hiringDate", new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH).format(user.getEntryDate()));
        else
            nonImageVariableMap.put("hiringDate", " ");
        Map<String, String> imageVariablesWithPathMap =new HashMap<String, String>();

        String familyMembersEn = "";
        for (FamilyMember famMember:
                user.getFamilyLoc()) {
            familyMembersEn += ", "+famMember.getRelation()[2] + "(" + famMember.getLastName()[2]+" " + famMember.getFirstName()[2]+")";
        }
        String familyMembersRu = "";
        for (FamilyMember famMember:
                user.getFamilyLoc()) {
            familyMembersRu += famMember.getRelation()[0] + "(" + famMember.getLastName()[0]+" " + famMember.getFirstName()[0]+"), ";
        }
        String familyMembersUz = "";
        for (FamilyMember famMember:
                user.getFamilyLoc()) {
            familyMembersUz += famMember.getRelation()[1] + "(" + famMember.getLastName()[1]+" " + famMember.getFirstName()[1]+"), ";
        }


        nonImageVariableMap.put("familyMembersEn", familyMembersEn);
        nonImageVariableMap.put("familyMembersRu", familyMembersRu);
        nonImageVariableMap.put("familyMembersUz", familyMembersUz);


        System.out.println("Writing file from template");
        DocxDocumentMergerAndConverter docxDocumentMergerAndConverter = new DocxDocumentMergerAndConverter();
        byte[] mergedOutput = docxDocumentMergerAndConverter.mergeAndGenerateOutput(templatePath, TemplateEngineKind.Freemarker, nonImageVariableMap, imageVariablesWithPathMap);

        String filename = fileName+"_"+user.getFirstName()[2] + "_"+ user.getLastName()[2]+"_"+new SimpleDateFormat("d-MMMMMMMM-yyyy", Locale.ENGLISH).format(date)+".docx";
        assertNotNull(mergedOutput);
        FileOutputStream os = new FileOutputStream("C:/files/temp/" + filename);
        os.write(mergedOutput);
        os.flush();
        os.close();






        InputStream is = new FileInputStream(new File(
                "C:/files/temp/"+filename));
        XWPFDocument document = new XWPFDocument(is);

        // 2) Prepare Html options
        XHTMLOptions options = XHTMLOptions.create();
        // Extract image
        File imageFolder = new File( "C:/files/temp/" );
        imageFolder.getParentFile().mkdirs();
        options.setExtractor( new FileImageExtractor( imageFolder ) );
        // URI resolver
        options.URIResolver( new FileURIResolver( imageFolder ) );

        // 3) Convert XWPFDocument to HTML
        OutputStream out = new FileOutputStream(new File(
                "C:/files/temp/"+filename+".html"));
        XHTMLConverter.getInstance().convert(document, out, options);

       return filename+".html";

    }



    public static UserInPostsEntity getMax(List<UserInPostsEntity> usersInPost) {
        UserInPostsEntity uip = new UserInPostsEntity();
        long num = 0;

        uip.setDateFrom( new java.sql.Date(num));
        for (UserInPostsEntity up :
                usersInPost) {
            if(up.getDateFrom().compareTo(uip.getDateFrom())>0)
            {
                uip=up;
            }
        }
        return uip;
    }


    private Map<Integer, String> getDirectHeadIdAndName() {
        Map<Integer, String> users = new LinkedHashMap<Integer, String>();
        for (UserLocalizationsEntity loc : UserService.getAllUserLocs()) {
            users.put(loc.getUserId(), loc.getFirstName() + " " + loc.getLastName());
        }
        return users;
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


}

