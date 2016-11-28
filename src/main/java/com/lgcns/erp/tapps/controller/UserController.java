package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.Enums.Appoint;
import com.lgcns.erp.tapps.Enums.Language;
import com.lgcns.erp.tapps.Enums.Language_Ranking;
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

    @RequestMapping (value = "/User/Profile", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Profile(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/IndexUser");
        ProfileViewModel userProfile=null;
        try {
            userProfile = getProfileByUsername(principal.getName());
        } catch (Exception e){
            e.printStackTrace();
        }

        mav.addObject("userProfile", userProfile);

        return mav;
    }

    @RequestMapping(value = "/User/Profile/Appointment", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Appointmentrec(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/AppointmentRec");
        AppointmentrecViewModel appointmentrecViewModel = getAppointmentByUsername(principal);
        ProfileViewModel userProfile = getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        mav.addObject("appointmentrecVM", appointmentrecViewModel);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Salary", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView SalaryRec(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/SalaryDetails");
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<SalaryVewModel> salaryVewModel = getSalaryByUser(user);

        ProfileViewModel userProfile = getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        mav.addObject("salaryVM", salaryVewModel);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Edu", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Edu(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/EducationCer");
        EduViewModel eduViewModel = getEducationByUsername(principal);
        ProfileViewModel userProfile = getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        mav.addObject("eduVM", eduViewModel);
        return mav;
    }


    @RequestMapping(value = "/User/Profile/Jobexp", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Jobexp(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/JobExp");
        List<JobexpViewModel> jobexpViewModel = getJobExperience(principal);
        ProfileViewModel userProfile = getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        mav.addObject("jobexpVM", jobexpViewModel);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Train", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Train(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/TrainingRec");
        List<TrainViewModel> trainViewModel = getTrainingRecord(principal);
        ProfileViewModel userProfile = getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        mav.addObject("trainVM", trainViewModel);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Docs", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Docs(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/usermenu/Docs");
        List<DocsViewModel> docsViewModel = getDocuments(principal);
        ProfileViewModel userProfile = getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        mav.addObject("docsVM", docsViewModel);
        return mav;
    }

    @RequestMapping(value = "/User/Profile/Docs/download", method = RequestMethod.GET)
    public String DocDownload(HttpServletResponse response, Principal principal, @RequestParam("id") int id) throws IOException {
        System.out.println("ID: " + id);


        File file = null;
        ClassLoader classLoader = this.getClass().getClassLoader();
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        String filePath = UserService.getDocumentById(id, user).getLink();
        file = new File(filePath);
        System.out.println("Path: " + file.getAbsolutePath());

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
        ProfileViewModel userProfile = getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
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
            returning.setDepartment(UserService.getDepartments().get(3).getName());

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
                familyLoc2 = UserService.getFamilyInfosLoc(fie.getId());

                FamilyMember familyMember = new FamilyMember(familyLoc2.size());
                for (FamiliyInfoLocalizationsEntity faInLoEn :
                        familyLoc2) {
                    familyMember.add(faInLoEn.getRelation(), faInLoEn.getLastName() + " " + faInLoEn.getFirstName(), fie.getDateOfBirth(), faInLoEn.getJobTitle(), faInLoEn.getLanguageId(), faInLoEn.getFamilyInfoid());
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

    public static AppointmentrecViewModel getAppointmentByUsername(Principal principal) {
        AppointmentrecViewModel returning = new AppointmentrecViewModel();

        // Getting data from users db
        UsersEntity user = UserService.getUserByUsername(principal.getName());

        List<UserInPostsEntity> usersInPost = UserService.getUserInPost(user);

        UserInPostsEntity userInPost = usersInPost.get(0);

        for (UserInPostsEntity uip :
                usersInPost) {
            returning.addAppointSummary(uip.getDateFrom(), Appoint.values()[uip.getContractType() - 1].toString(), UserService.getDepartments().get(user.getDepartmentId()).getName(), UserService.getJobTitle(uip.getPostId(), 3).getName());
        }
        return returning;
    }


    public static List<SalaryVewModel> getSalaryByUser(UsersEntity user) {
        List<SalaryHistoriesEntity> salariesHistory = UserService.getSalaryHistories(user);
        List<SalaryVewModel> salaries = new LinkedList<SalaryVewModel>();
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.ENGLISH);

        for (SalaryHistoriesEntity salary :
                salariesHistory) {
            salaries.add(new SalaryVewModel(String.format("%,d", salary.getSalaryBefore()), String.format("%,d", salary.getSalaryAfter()), salary.getDate(), salary.getPit(), salary.getInps(), salary.getPf()));
        }
        return salaries;

    }

    public static EduViewModel getEducationByUsername(Principal principal) {
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
            eduReturn.addCertificate(certificatesLocEntitie.getName(), certificatesLocEntitie.getOrganization(), cert.getNumber(), cert.getDateTime(),cert.getMark());
        }
        return eduReturn;
    }

    public static List<JobexpViewModel> getJobExperience(Principal principal) {
        List<JobexpViewModel> jobExpViewModels = new LinkedList<JobexpViewModel>();
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<WorksEntity> worksEntity = UserService.getWorksEntity(user);

        for (WorksEntity we :
                worksEntity) {
            WorkLocalizationsEntity wle = UserService.getWorkLocal(we);
            jobExpViewModels.add(new JobexpViewModel(wle.getOrganization(), wle.getPost(), we.getStartDate(), we.getEndDate()));
        }
        return jobExpViewModels;
    }

    public static List<TrainViewModel> getTrainingRecord(Principal principal) {
        List<TrainViewModel> trainViewModels = new LinkedList<TrainViewModel>();
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<TrainingsEntity> trainings = UserService.getTrainingsEntity(user);
        for (TrainingsEntity trainEn :
                trainings) {
            TrainingLocalizationsEntity trainLoc = UserService.getTrainingLoc(trainEn);
            trainViewModels.add(new TrainViewModel(trainLoc.getName(), trainEn.getCertificateId(), trainLoc.getOrganization(), trainEn.getDateFrom(), trainEn.getDateTo(), trainEn.getNumberOfHours(), trainEn.getMark()));
        }
        return trainViewModels;
    }

    private List<DocsViewModel> getDocuments(Principal principal) {
        List<DocsViewModel> docsViewModels = new LinkedList<DocsViewModel>();
        UsersEntity user = UserService.getUserByUsername(principal.getName());
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
