package com.lgcns.erp.tapps.controller;


import com.lgcns.erp.tapps.DbContext.DocxDocumentMergerAndConverter;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.Enums.Appoint;
import com.lgcns.erp.tapps.mapper.UserMapper;
import com.lgcns.erp.tapps.model.DbEntities.*;
import com.lgcns.erp.tapps.validator.UserFormValidator;
import com.lgcns.erp.tapps.viewModel.HR.DocsViewModel;
import com.lgcns.erp.tapps.viewModel.HR.HrJobexpViewModel;
import com.lgcns.erp.tapps.viewModel.PersonalInformationViewModel;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.RegistrationViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.*;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    @RequestMapping(value = "/Hr/Register", method = RequestMethod.POST)
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

    @RequestMapping (value = "/Hr/Profile", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Hrprofile(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/IndexHR");
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping (value = "/Hr/Profile/Appointment", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrAppointmentrec(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/AppointmentRec");
        AppointmentrecViewModel appointmentrecViewModel = UserController.getAppointmentByUsername(principal);
        mav.addObject("appointmentrecVM", appointmentrecViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping (value = "/Hr/Profile/Salary", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrSalaryrec(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/SalaryDetails");
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<SalaryVewModel> salaryVewModel = UserController.getSalaryByUser(user);

        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
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
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping (value = "/Hr/Profile/Jobexp", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrJobexp(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/JobExp");
        List<JobexpViewModel> jobexpViewModel = UserController.getJobExperience(principal);
        mav.addObject("jobexpVM", jobexpViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping (value = "/Hr/Profile/Train", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrTrain(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/TrainingRec");
        List<TrainViewModel> trainViewModel = UserController.getTrainingRecord(principal);
        mav.addObject("trainVM", trainViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping (value = "/Hr/Userslist", method = RequestMethod.GET)
    @ResponseBody public ModelAndView HrUserslist(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/Userslist");
        List<ProfileViewModel> users = getUsers();

        mav.addObject("hrUserslistVM", users);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }
    @RequestMapping(value = "/Hr/Docs", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Docs(Principal principal,Model model, @ModelAttribute("user")  Hashtable<Integer, String> users) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home/hrmenu/Docs");
        DocsViewModel docsViewModel = new DocsViewModel();
        users = new Hashtable<Integer, String>();
        for (UserLocalizationsEntity user2 :
                UserService.getAllUserLocs()) {
            if (user2.getLanguageId()==3)
            {
                users.put(user2.getUserId(), user2.getFirstName() + " " + user2.getLastName());
            }
        }
        mav.addObject("users", users);
        model.addAttribute("users", users);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    private void generateCertificate(ProfileViewModel user) throws IOException, XDocReportException {
        String templatePath = "C:/files/template/Certification_of_Employment.docx";
        Map<String, Object> nonImageVariableMap = new HashMap<String, Object>();
        Date date = new Date();
        nonImageVariableMap.put("date_now", new SimpleDateFormat("d MMMMMMMM yyyy", Locale.ENGLISH).format(date));

        nonImageVariableMap.put("name", user.getFirstName()[2] + " "+ user.getLastName()[2]);
        nonImageVariableMap.put("jobTitle", user.getJobTitle());
        System.out.printf("Entry date: " + user.getEntryDate());
        nonImageVariableMap.put("hiringDate", new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH).format(user.getEntryDate()));
        Map<String, String> imageVariablesWithPathMap =new HashMap<String, String>();
        imageVariablesWithPathMap.put("header_image_logo", "C:/0001.jpg");
        System.out.println("Writing file from template");
        DocxDocumentMergerAndConverter docxDocumentMergerAndConverter = new DocxDocumentMergerAndConverter();
        byte[] mergedOutput = docxDocumentMergerAndConverter.mergeAndGenerateOutput(templatePath, TemplateEngineKind.Freemarker, nonImageVariableMap, imageVariablesWithPathMap);
        assertNotNull(mergedOutput);
        FileOutputStream os = new FileOutputStream("C:/files/Certification_"+user.getFirstName()[2] + "_"+ user.getLastName()[2]+"_"+new SimpleDateFormat("d-MMMMMMMM-yyyy", Locale.ENGLISH).format(date)+".docx");
        os.write(mergedOutput);
        os.flush();
        os.close();
    }

    @RequestMapping(value = "/Hr/user/{id}/update/{path}", method = RequestMethod.GET)
    @ResponseBody public ModelAndView UpdateInfo(Principal principal, Model model, @ModelAttribute("user") ProfileViewModel person, @PathVariable("id") int id, @PathVariable("path") String path){
        ModelAndView mav = new ModelAndView();
        System.out.print("Path: ");
        if(path.compareTo("Geninfo")==0) {
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


        else if(path.compareTo("Docs")==0){
            System.out.println(path);
            mav.setViewName("Home/editmenu/Docs");
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
        else{

        }
        mav.setViewName("Home/editmenu/geninfo");
        HrJobexpViewModel hrjobexpViewModel = new HrJobexpViewModel();
        mav.addObject("hrjobexpVM", hrjobexpViewModel);
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
        return mav;
    }

    @RequestMapping ( value = "/Hr/user/{id}/update/{path}", method = RequestMethod.POST )
    public String UpdateInfo(Principal principal, @Valid @ModelAttribute  ProfileViewModel person, @PathVariable String path, BindingResult result, @PathVariable("id") String id){
        System.out.println(person.getPassportNumber());
        if(result.hasErrors()) {
            return "Hr/user/"+id+"/update"+path;
        }


        updateDBGenInfo(person);
        return "redirect: /Hr/user/"+id+"/update/"+path;
    }


    @RequestMapping(value = "/Hr/user/{userId}/update/Geninfo/updateFam/{famId}/", method = RequestMethod.GET)
    public ModelAndView UpdateFamInfo(Model model, @PathVariable("userId") int userId, @PathVariable("famId") int famId){

        FamilyMember familyProfile = getUserFamily(userId, famId);
        //System.out.println(familyProfile);

        return new ModelAndView("Home/editmenu/faminfo" , "family", familyProfile);
    }
    @RequestMapping(value = "/Hr/user/{userId}/update/Geninfo/updateFam/{famId}/", method = RequestMethod.POST)
    public String UpdateFamInfoPost(Model model, FamilyMember familyProfile, @PathVariable("userId") String userId, @PathVariable("famId") String famId){

        System.out.println("Family member: " + familyProfile);
        UserService.updateUsersFamilyInfo(familyProfile);
        UserService.updateUsersFamilyInfoLocEn(familyProfile);
        UserService.updateUsersFamilyInfoLocRu(familyProfile);
        UserService.updateUsersFamilyInfoLocUz(familyProfile);
        System.out.println("I am working here");
        return "redirect: /Hr/user/"+userId+"/update/Geninfo";
    }

    @RequestMapping(value = "/Hr/user/{userId}/update/Geninfo/addFam", method = RequestMethod.GET)
    public ModelAndView addFamGet(Model model, @PathVariable("userId") int userId){
        ModelAndView mav = new ModelAndView();
        FamilyMember familyProfile = new FamilyMember();
        model.addAttribute("family", familyProfile);
        mav.setViewName("Home/editmenu/newfaminfo");
        return mav;
    }
    @RequestMapping(value = "/Hr/user/{userId}/update/Geninfo/addFam", method = RequestMethod.POST)
    public String addFamPost(Model model, FamilyMember familyProfile, @PathVariable("userId") String userId){

        System.out.println("Family member: " + familyProfile);
        int id = UserService.insertUsersFamilyInfo(UserMapper.mapAddFamily(familyProfile, userId));

        System.out.println("ID: "+id);
        UserService.insertUsersFamilyInfoLocEn(UserMapper.mapAddFamilyLoc(familyProfile, id, 3));
        UserService.insertUsersFamilyInfoLocRu(UserMapper.mapAddFamilyLoc(familyProfile, id, 1));
        UserService.insertUsersFamilyInfoLocUz(UserMapper.mapAddFamilyLoc(familyProfile, id, 2));
        System.out.println("I am working here");
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

    @RequestMapping ( value = "/Hr/Generate/{docId}/{userId}/", method = RequestMethod.GET )
    public String GenerateDoc(Principal principal, @PathVariable("docId") int docId, @PathVariable("userId") int userId){
        ProfileViewModel user = getProfileById(userId);
        System.out.printf("Generating document: "+docId);
        try {
            if (docId==1)
                generateCertificate(user);
            else if(docId==2) {
                System.out.printf("Generating Decree terminate");
                generateDecreeTerminate(user);
            }
            else if(docId==3){
                generateDecreeFamilyTicket(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
        return "redirect:/Hr/Docs";
    }

    @RequestMapping("/Hr/user/{id}/UploadPic")
    public ModelAndView uploading(Model model, @PathVariable("id") int id) {
        File file = new File(uploadingdir);
        model.addAttribute("files", file.listFiles());
        ModelAndView mav = new ModelAndView();
        mav.addObject("id", id);
        mav.setViewName("Home/hrmenu/FileUploadForm");
        return mav;
    }

    @RequestMapping(value = "/Hr/user/{id}/UploadPic", method = RequestMethod.POST)
    public String uploadingPost(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles, @PathVariable("id") int id) throws IOException {
        String sId=null;
        sId = String.format("%04d", id);
        for(MultipartFile uploadedFile : uploadingFiles) {
            String sId2 =sId + ".jpg";
            File file = new File("../webapps/ROOT/resources/images/" + sId2);
            uploadedFile.transferTo(file);
            System.out.printf(file.getAbsolutePath());
        }
        return "redirect:/Hr/user/"+sId+"/update/geninfo/";
    }

    @RequestMapping(value = "Hr/changepass", method = RequestMethod.GET)
    public ModelAndView ChangePass(Principal principal){
        ModelAndView mav = new ModelAndView();
        ChangepassViewModel changepassViewModel = new ChangepassViewModel ();
        mav.setViewName("user/changepass");
        ProfileViewModel userProfile = UserController.getProfileByUsername(principal.getName());
        mav.addObject("userProfile", userProfile);
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
            userProfile.setEnabled(user.isEnabled());
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
            returning.setPolitical(user.getPolitical());

        }catch (Exception e){
            e.printStackTrace();
        }
        try {

            /*//Getting Joint Type
            returning.setJointType(Appoint.values()[getMax(UserService.getUserInPost(user)).getContractType() - 1].toString());
*/
            //Getting status
            List<StatusLocalizationsEntity> statuses = UserService.getStatuses();
            for (StatusLocalizationsEntity st :
                    statuses) {
                if(st.getStatusId()==user.getStatusId())
                    System.out.printf(st.getName());
            }
            /*returning.setStatus();*/

            //Getting job title
            /*int postId = getMax(UserService.getUserInPost(user)).getPostId();
            returning.setJobTitle(UserService.getJobTitle(postId, 3).getName());*/

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

    private void generateDecreeFamilyTicket(ProfileViewModel user) throws IOException, XDocReportException {
        String templatePath = "C:/files/template/Decree_family_ticket.docx";
        Map<String, Object> nonImageVariableMap = new HashMap<String, Object>();
        Date date = new Date();
        nonImageVariableMap.put("date_now", new SimpleDateFormat("d MMMMMMMM yyyy", Locale.ENGLISH).format(date));

        nonImageVariableMap.put("name", user.getFirstName()[2] + " "+ user.getLastName()[2]);
        nonImageVariableMap.put("jobTitle", user.getJobTitle());
        String familyMembers_en = "";
        for (FamilyMember famMember:
                user.getFamilyLoc()) {
            familyMembers_en += ", "+famMember.getRelation()[2] + "(" + famMember.getLastName()[2]+" " + famMember.getFirstName()[2]+")";
        }
        String familyMembers_ru = "";
        for (FamilyMember famMember:
                user.getFamilyLoc()) {
            familyMembers_ru += famMember.getRelation()[0] + "(" + famMember.getLastName()[2]+" " + famMember.getFirstName()[2]+"), ";
        }
        nonImageVariableMap.put("jobTitle", user.getJobTitle());
        nonImageVariableMap.put("familyMembers_en", familyMembers_en);
        nonImageVariableMap.put("familyMembers_ru", familyMembers_ru);
        Map<String, String> imageVariablesWithPathMap =new HashMap<String, String>();
        imageVariablesWithPathMap.put("header_image_logo", "C:/0001.jpg");
        System.out.println("Writing file from template");
        DocxDocumentMergerAndConverter docxDocumentMergerAndConverter = new DocxDocumentMergerAndConverter();
        byte[] mergedOutput = docxDocumentMergerAndConverter.mergeAndGenerateOutput(templatePath, TemplateEngineKind.Freemarker, nonImageVariableMap, imageVariablesWithPathMap);
        assertNotNull(mergedOutput);
        FileOutputStream os = new FileOutputStream("C:/files/Decree_family_ticket_"+user.getFirstName()[2] + "_"+ user.getLastName()[2]+"_"+new SimpleDateFormat("d-MMMMMMMM-yyyy", Locale.ENGLISH).format(date)+".docx");
        os.write(mergedOutput);
        os.flush();
        os.close();
    }

    private void generateDecreeTerminate(ProfileViewModel user) throws IOException, XDocReportException {
        String templatePath = "C:/files/template/Decree_terminate.docx";
        Map<String, Object> nonImageVariableMap = new HashMap<String, Object>();
        Date date = new Date();
        nonImageVariableMap.put("date_now", new SimpleDateFormat("d MMMMMMMM yyyy", Locale.ENGLISH).format(date));

        nonImageVariableMap.put("nameEn", user.getFirstName()[2] + " "+ user.getLastName()[2]);
        nonImageVariableMap.put("nameRu", user.getFirstName()[0] + " "+ user.getLastName()[0]);
        nonImageVariableMap.put("jobTitle", user.getJobTitle());
        Map<String, String> imageVariablesWithPathMap =new HashMap<String, String>();
        imageVariablesWithPathMap.put("header_image_logo", "C:/0001.jpg");
        System.out.println("Writing file from template");
        DocxDocumentMergerAndConverter docxDocumentMergerAndConverter = new DocxDocumentMergerAndConverter();
        byte[] mergedOutput = docxDocumentMergerAndConverter.mergeAndGenerateOutput(templatePath, TemplateEngineKind.Freemarker, nonImageVariableMap, imageVariablesWithPathMap);
        assertNotNull(mergedOutput);
        FileOutputStream os = new FileOutputStream("C:/files/Decree_terminate_"+user.getFirstName()[2] + "_"+ user.getLastName()[2]+"_"+new SimpleDateFormat("d-MMMMMMMM-yyyy", Locale.ENGLISH).format(date)+".docx");
        os.write(mergedOutput);
        os.flush();
        os.close();
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


}

