package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.Enums.Appoint;
import com.lgcns.erp.tapps.Enums.Document_Type;
import com.lgcns.erp.tapps.Enums.Language;
import com.lgcns.erp.tapps.Enums.Language_Ranking;
import com.lgcns.erp.tapps.mapper.UserMapper;
import com.lgcns.erp.tapps.model.DbEntities.*;
import com.lgcns.erp.tapps.model.UserInfo;
import com.lgcns.erp.tapps.viewModel.LoginViewModel;
import com.lgcns.erp.tapps.viewModel.PersonalInformationViewModel;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.Principal;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.*;


/**
 * Created by Rafatdin on 15.09.2016.
 */

@Controller
public class UserController {


    @RequestMapping(value = "/User/Login", method = RequestMethod.GET)
    public ModelAndView Login() {

        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");
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

    @RequestMapping (value = "/User/Profile", method = RequestMethod.GET)
    public ModelAndView Profile(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/Index");
        ProfileViewModel userProfile=null;
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        return mav;
    }

    @RequestMapping(value = "/User/Profile/editPersonal", method = RequestMethod.GET)
    public ModelAndView UpdatePersonalInfo(Principal principal, Model model){
        UsersEntity usersEntity = UserService.getUserByUsername(principal.getName());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/edit/personalinfo");
        mav.addObject("person", usersEntity);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/editPersonal", method = RequestMethod.POST)
    public String UpdatePersonalInfoPost(Principal principal, Model model, UsersEntity usersEntity){
        usersEntity.setId(UserService.getIdByUsername(principal.getName()));
        UserService.updateUsersEntityUser(usersEntity);
        System.out.println("Personal data: " + usersEntity.geteMail() + " " + usersEntity.getPersonalEmail() + " " + usersEntity.getHomePhone() +" "+ usersEntity.getMobilePhone());
        return "redirect: /User/Profile";
    }

    @RequestMapping(value = "/User/Profile/updateFam/{famId}/", method = RequestMethod.GET)
    public ModelAndView UpdateFamInfo(Principal principal, Model model, @PathVariable("famId") int famId){
        int userId = UserService.getUserIdByUsername(principal.getName());
        FamilyMember familyProfile = getUserFamily(userId, famId);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/editmenu/edit/faminfo");
        mav.addObject("family", familyProfile);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
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

    @RequestMapping(value = "/User/Profile/updateFam/{famId}/", method = RequestMethod.POST)
    public String UpdateFamInfoPost(Principal principal, Model model, FamilyMember familyProfile, @PathVariable("famId") String famId){
        int userId = UserService.getUserIdByUsername(principal.getName());
        UserService.updateUsersFamilyInfo(familyProfile);
        UserService.updateUsersFamilyInfoLocEn(familyProfile);
        UserService.updateUsersFamilyInfoLocRu(familyProfile);
        UserService.updateUsersFamilyInfoLocUz(familyProfile);
        return "redirect: /User/Profile";
    }
    @RequestMapping(value = "/User/Profile/deleteFam/{famId}/", method = RequestMethod.GET)
    public String DeleteFamInfoPost(Principal principal, Model model, FamilyMember familyProfile, @PathVariable("famId") String famId){
        int userId = UserService.getUserIdByUsername(principal.getName());
        UserService.deleteUsersFamilyInfoLoc(famId);
        UserService.deleteUsersFamilyInfo(famId);

        System.out.println("I am working here");
        return "redirect: /User/Profile";
    }

    @RequestMapping(value = "/User/Profile/addFam", method = RequestMethod.GET)
    public ModelAndView addFamGet(Principal principal, Model model){
        ModelAndView mav = new ModelAndView();
        FamilyMember familyProfile = new FamilyMember();
        model.addAttribute("family", familyProfile);
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("SystemRole", UserService.getUserByUsername(principal.getName()).getRoleId());
        mav.setViewName("Home/editmenu/new/newfaminfo");
        return mav;
    }
    @RequestMapping(value = "/User/Profile/addFam", method = RequestMethod.POST)
    public String addFamPost(Principal principal, Model model, FamilyMember familyProfile){
        String userId ="";
        userId += UserService.getUserIdByUsername(principal.getName());
        int id = UserService.insertUsersFamilyInfo(UserMapper.mapAddFamily(familyProfile, userId));

        UserService.insertUsersFamilyInfoLocEn(UserMapper.mapAddFamilyLoc(familyProfile, id, 3));
        UserService.insertUsersFamilyInfoLocRu(UserMapper.mapAddFamilyLoc(familyProfile, id, 1));
        UserService.insertUsersFamilyInfoLocUz(UserMapper.mapAddFamilyLoc(familyProfile, id, 2));
        return "redirect: /User/Profile";
    }

    @RequestMapping(value = "/User/Profile/Appointment", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Appointmentrec(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/AppointmentRec");
        AppointmentrecViewModel appointmentrecViewModel=null;
        try {
            appointmentrecViewModel = getAppointmentByUsername(principal.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("appointmentrecVM", appointmentrecViewModel);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Salary", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView SalaryRec(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/SalaryDetails");
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<SalaryVewModel> salaryVewModel = getSalaryByUser(user.getUserName());

        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("salaryVM", salaryVewModel);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Edu", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Edu(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/EducationCer");
        EduViewModel eduViewModel = getEducationByUsername(principal.getName());
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("eduVM", eduViewModel);
        return mav;
    }


    @RequestMapping(value = "/User/Profile/Jobexp", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Jobexp(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/JobExp");
        List<JobexpViewModel> jobexpViewModel = getJobExperience(principal.getName());
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("jobexpVM", jobexpViewModel);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Train", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Train(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/TrainingRec");
        List<TrainViewModel> trainViewModel = getTrainingRecord(principal.getName());
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("trainVM", trainViewModel);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Docs", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Docs(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/Docs");
        List<DocsViewModel> docsViewModel = getDocuments(principal.getName());
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("docsVM", docsViewModel);
        Map<Integer, String> docType = new HashMap<Integer, String>();
        for (Document_Type doc :
                Document_Type.values()) {
            docType.put(doc.getValue(), doc.name());
        }
        mav.addObject("docType", docType);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Project", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Project(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/Project");
       /* List<DocsViewModel> docsViewModel = getDocuments(principal);*/
        mav = UP.includeUserProfile(mav, principal);
        /*mav.addObject("docsVM", docsViewModel);*/
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Evaluation", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Evaluation(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/EvaluationHistory");
        List<PersonalEvalutionsEntity> evaluations = UserService.getEvaluationsByUserId(UserService.getUserIdByUsername(principal.getName()));
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

    @RequestMapping(value = "/User/Profile/Docs/download", method = RequestMethod.GET)
    public String DocDownload(HttpServletResponse response, Principal principal, @RequestParam("id") int id) throws IOException {
        System.out.println("ID: " + id);
        File file = null;
        ClassLoader classLoader = this.getClass().getClassLoader();
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        String filePath = UserService.getDocumentById(id, user.getId()).getLink();
        file = new File(filePath);

        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return "redirect: /User/Profile/Docs/";
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



        return "redirect: /User/Profile/Docs/";
    }

    @RequestMapping(value = "User/changepass", method = RequestMethod.GET)
    public ModelAndView ChangePass(Principal principal){
        ModelAndView mav = new ModelAndView();
        ChangepassViewModel changepassViewModel = new ChangepassViewModel ();
        mav.setViewName("user/changepass");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("changepassVM", changepassViewModel);
        return mav;
    }

    @RequestMapping(value = "user_changepass", method = RequestMethod.POST)
    public String ChangePass2(Principal principal, @RequestParam("oldPassword") String oldPass, @RequestParam("password") String pass, @RequestParam("repeatPassword") String repPass){
        ModelAndView model = new ModelAndView();
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(pass);
        System.out.println(pass);
        String passwordNow = UserService.getUserByUsername(principal.getName()).getPasswordHash();
        if(passwordEncoder.matches(oldPass, passwordNow)){
            UserService.updatePassword(user, hashedPassword);
            System.out.println("Password updated!");
        }else
            System.out.println("Passwords do not match!!!");
        return "redirect: /";
    }

    public static ProfileViewModel getProfileByUsername(String userName){
        ProfileViewModel returning = new ProfileViewModel();
        try {
            // Getting data from users db
            UsersEntity user = UserService.getUserByUsername(userName);
            // Getting its localization data
            List<UserLocalizationsEntity> userLocalizationsEntities = UserService.getUserLocByUserId(user.getId());

            for (UserLocalizationsEntity ul :
                    userLocalizationsEntities) {
                returning.addData1(String.format("%05d", user.getId()), ul.getFirstName(), ul.getLastName(), ul.getFatherName(), ul.getAddress(), ul.getLanguageId());
            }
            //Getting department name
            returning.setDepartment(UserService.getDepartments().get(user.getDepartmentId()-1).getName());
            //Getting roleId
            returning.setRoleId(user.getRoleId());
            //Getting Position from user_in_roles
            returning.setPosition(UserService.getRoleLoc(user).getName());

            // Getting isEnabled variable
            returning.setEnabled(user.isEnabled());

            //Getting is political
            if (user.getPolitical())
                returning.setPolitical(true);
            else
                returning.setPolitical(false);

            //Getting Joint Type
            if ((getMax(UserService.getUserInPost(user)).getContractType() - 1)>0)
                returning.setJointType(Appoint.values()[getMax(UserService.getUserInPost(user)).getContractType() - 1].toString());

            //Getting status
            if(UserService.getStatuses().get(3).getName()!=null)
            returning.setStatus(UserService.getStatuses().get(3).getName());

            //Getting job title
            int postId=0;
            if(getMax(UserService.getUserInPost(user)).getPostId()>0) {
                postId = getMax(UserService.getUserInPost(user)).getPostId();
                if(UserService.getJobTitle(postId, 3).getName()!=null)
                    returning.setJobTitle(UserService.getJobTitle(postId, 3).getName());
            }

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
                familyLoc2 = UserService.getFamilyInfosLoc(fie.getId());

                FamilyMember familyMember = new FamilyMember(familyLoc2.size());
                for (FamiliyInfoLocalizationsEntity faInLoEn :
                        familyLoc2) {
                    familyMember.add(faInLoEn.getRelation(), faInLoEn.getLastName() , faInLoEn.getFirstName(), fie.getDateOfBirth(), faInLoEn.getJobTitle(), faInLoEn.getLanguageId(), faInLoEn.getFamilyInfoid());
                    // System.out.println( familyMember.getRelation()[faInLoEn.getLanguageId()-1]+ " " + familyMember.getFullName()[faInLoEn.getLanguageId()-1] + " " + familyMember.getDateOfBirth() + " " + familyMember.getJobTitle()[faInLoEn.getLanguageId()-1]);
                }
                familyMembers.add(familyMember);
            }
            returning.setFamilyLoc(familyMembers);

            // (MUST BE FINISHED!) Getting and setting vacation days
            returning.setVacationDaysLeft(0);
            returning.setVacationDaysAll(12);
        }catch (Exception e){
            e.printStackTrace();
        }
        return returning;
    }

    public static AppointmentrecViewModel getAppointmentByUsername(String userName) {
        AppointmentrecViewModel returning = new AppointmentrecViewModel();

        // Getting data from users db
        UsersEntity user = UserService.getUserByUsername(userName);

        List<UserInPostsEntity> usersInPost = UserService.getUserInPost(user);

        UserInPostsEntity userInPost = usersInPost.get(0);

        for (UserInPostsEntity uip :
                usersInPost) {
            String departName=null;
            List<DepartmentLocalizationsEntity> departmentLocalizationsEntities = UserService.getDepartments();
            for (DepartmentLocalizationsEntity d :
                    departmentLocalizationsEntities) {
                if (d.getDepartmentId() == user.getDepartmentId() && d.getLanguageId()==3) {
                    departName = d.getName();
                }
            }
            returning.addAppointSummary(uip.getDateFrom(), Appoint.values()[uip.getContractType() - 1].toString(), departName, UserService.getJobTitle(uip.getPostId(), 3).getName(), uip.getId(), user.getDepartmentId());
        }
        return returning;
    }


    public static List<SalaryVewModel> getSalaryByUser(String userName) {
        int id = UserService.getUserIdByUsername(userName);
        List<SalaryHistoriesEntity> salariesHistory = UserService.getSalaryHistories(id);
        List<SalaryVewModel> salaries = new LinkedList<SalaryVewModel>();
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.ENGLISH);

        for (SalaryHistoriesEntity salary :
                salariesHistory) {
            salaries.add(new SalaryVewModel(String.format("%,d", salary.getSalaryBefore()), String.format("%,d", salary.getSalaryAfter()), salary.getDate(), salary.getPit(), salary.getInps(), salary.getPf(), salary.getId(), salary.getCurrencyId()));
        }
        return salaries;

    }

    public static EduViewModel getEducationByUsername(String userName) {
        EduViewModel eduReturn = new EduViewModel();
        EducationLocalizationsEntity eduLoc = null;
        UsersEntity user = UserService.getUserByUsername(userName);

        // Getting and setting Educations module
        List<EducationsEntity> educations = UserService.getEducationsByUsername(user);
        for (EducationsEntity edu:
                educations) {
            eduLoc = UserService.getEducationLocalization(edu, 3);
            eduReturn.addEducation(eduLoc.getName(), eduLoc.getMajor(), eduLoc.getDegree(), edu.getStartDate(), edu.getEndDate(), edu.getId());
        }

        // Getting and setting Language Summary module
        List<UserInLanguagesEntity> languageSummaries = UserService.getUserInLanguages(user);
        for (UserInLanguagesEntity lan :
                languageSummaries) {
            eduReturn.addLanguageSummary(Language.values()[lan.getLanguageId()-1].toString(), Language_Ranking.values()[lan.getListening()-1].toString(), Language_Ranking.values()[lan.getReading()-1].toString(), Language_Ranking.values()[lan.getWriting()-1].toString(), Language_Ranking.values()[lan.getSpeaking()-1].toString(), lan.getId());
        }

        // Getting and setting Certificates module
        List<CertificatesEntity> certificatesEntities=null;
        if(UserService.getCertificates(user)!=null)
            certificatesEntities = UserService.getCertificates(user);
        for (CertificatesEntity cert :
                certificatesEntities) {
            CertificateLocalizationsEntity certificatesLocEntitie = UserService.getCertificatesLoc(cert, 3);
            eduReturn.addCertificate(certificatesLocEntitie.getName(), certificatesLocEntitie.getOrganization(), cert.getNumber(), cert.getDateTime(),cert.getMark(), cert.getId(), cert.getType(), cert.getDegree());
        }
        return eduReturn;
    }

    public static List<JobexpViewModel> getJobExperience(String userName) {
        List<JobexpViewModel> jobExpViewModels = new LinkedList<JobexpViewModel>();
        UsersEntity user = null;
        try{
            user = UserService.getUserByUsername(userName);
        }catch (Exception e){
            e.printStackTrace();
        }

        List<WorksEntity> worksEntity = UserService.getWorksEntity(user);

        for (WorksEntity we :
                worksEntity) {
            WorkLocalizationsEntity wle = UserService.getWorkLocal(we);
            jobExpViewModels.add(new JobexpViewModel(wle.getOrganization(), wle.getPost(), we.getStartDate(), we.getEndDate(), we.getContractType(), we.getId()));
        }
        return jobExpViewModels;
    }

    public static List<TrainViewModel> getTrainingRecord(String userName) {
        List<TrainViewModel> trainViewModels = new LinkedList<TrainViewModel>();
        UsersEntity user = UserService.getUserByUsername(userName);
        List<TrainingsEntity> trainings = UserService.getTrainingsEntity(user);
        for (TrainingsEntity trainEn :
                trainings) {
            TrainingLocalizationsEntity trainLoc = UserService.getTrainingLoc(trainEn);
            trainViewModels.add(new TrainViewModel(trainLoc.getName(), trainEn.getCertificateId(), trainLoc.getOrganization(), trainEn.getDateFrom(), trainEn.getDateTo(), trainEn.getNumberOfHours(), trainEn.getMark(), trainEn.getId()));
        }
        return trainViewModels;
    }

    public static List<DocsViewModel> getDocuments(String userName) {
        List<DocsViewModel> docsViewModels = new LinkedList<DocsViewModel>();
        UsersEntity user = UserService.getUserByUsername(userName);
        List<DocumentsEntity> documents = UserService.getDocuments(user);
        for (DocumentsEntity docs :
                documents) {
            docsViewModels.add(new DocsViewModel(docs.getId(), docs.getName(), String.valueOf(docs.getDocumentType())));

        }

        return docsViewModels;
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
