package com.lgcns.erp.workflow.controller.newForm;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.model.UserInfo;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.MembersEntity;
import com.lgcns.erp.workflow.DBEntities.ToDoEntity;
import com.lgcns.erp.workflow.DBEntities.TripTypesEntity;
import com.lgcns.erp.workflow.Mapper.BusinessTripMapper;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.apache.commons.lang3.ArrayUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DScomputers3 on 20.01.2017.
 */

@Controller
@RequestMapping(value = "/Workflow")
public class BusinessTripController {

    int[] approvalsGlobal = null;
    int[] executivesGlobal = null;
    int[] referencesGlobal = null;

    @RequestMapping(value = "/NewForm/BusinessTripForm", method = RequestMethod.GET)
    public ModelAndView WorkflowGET(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("workflow/newForm/businessTripForm");
        mav = UP.includeUserProfile(mav, principal);

        // Creating Business Trip ViewModel
        BusinessTripVM businessTripVM = new BusinessTripVM();
        mav.addObject("businessTripVM", businessTripVM);

        // Create Json data about userlist for Approvals, reference and executive persons list
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        int i=0;
        int[] userId = new int[1000];
        String[] userName = new String[1000];
        Map<Integer, String> users = new HashMap<>();
        // Retrieving data of all users from DB
        UserLocalizationsEntity userLoc=null;
        users.put(0, "");
        jsonObject = new JSONObject();
        jsonObject.put("id", "0");
        jsonObject.put("name", "");
        jsonObject.put("jobTitle", "");
        jsonObject.put("department", "");

        jsonArray.add(jsonObject);

        for (UsersEntity user :
                UserService.getAllUsers()) {

            if(user.isEnabled()==true) {
                jsonObject = new JSONObject();
                // Retrieving user localizations info from DB for all users and check for null
                if(user.getId()!=0 || UserService.getUserLocByUserId(user.getId(), 3)!=null) {
                    try {
                        userLoc = UserService.getUserLocByUserId(user.getId(), 3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Inserting user Id as id, fullname as name, jobTitle as jobTitle and department as department
                ProfileViewModel prof =  UserController.getProfileByUsername(user.getUserName());

                jsonObject.put("id", prof.getId());
                jsonObject.put("name", userLoc.getFirstName() + " " + userLoc.getLastName());
                jsonObject.put("jobTitle", prof.getJobTitle());
                jsonObject.put("department", prof.getDepartment());

                users.put(Integer.parseInt(prof.getId()), userLoc.getFirstName() + " " + userLoc.getLastName());
                userId[i] = Integer.parseInt(prof.getId());
                userName[i++] = userLoc.getFirstName() + " " + userLoc.getLastName();

                // add object to array
                jsonArray.add(jsonObject);
            }
        }

        mav.addObject("userId", userId);
        mav.addObject("userName", userName);
        // add JSON array to ModelAndView
        mav.addObject("jsonData", jsonArray);
        mav.addObject("users", users);
        // Retriving data about type of Business trip
        Map<Integer, String> tripTypeList = new HashMap<>();
        tripTypeList.put(0, "");
        for (TripTypesEntity tripType :
                WorkflowService.getTripTypes()) {
            tripTypeList.put(Integer.valueOf((int) tripType.getId()), tripType.getName());
        }
        // Add trip type data to ModelAndView
        mav.addObject("triptypeList", tripTypeList);

        Map<Integer, String> currency = new HashMap<>();
        currency.put(0, "");
        currency.put(1, "UZS");
        currency.put(2, "USD");
        mav.addObject("currency", currency);

        return mav;
    }

    @RequestMapping(value = "/NewForm/BusinessTripForm", method = RequestMethod.POST, params = "Submit")
    public String BusinessTripPostSubmit(@ModelAttribute BusinessTripVM businessTripVM, BindingResult result, Principal principal) throws IOException {

        int userId = UserService.getIdByUsername(principal.getName());

        /* Insert to table Requests */

        int requestId = WorkflowService.insertRequests(BusinessTripMapper.requestMapper(businessTripVM, userId, 1, 1, false));
        businessTripVM.setId(requestId);

        /* Insert to table Members */
        for (MembersEntity member :
                businessTripVM.getMembersEntityList()) {
            WorkflowService.insertMembers(BusinessTripMapper.membersMapper(businessTripVM, member, userId));
        }

        /* Insert to table to_do */
        for (ToDoEntity todo :
                businessTripVM.getToDoEntityList()) {
            if (!todo.getDate().toString().equals("1111-11-11"))
                WorkflowService.insertToDo(BusinessTripMapper.toDoMapper(businessTripVM.getId(), todo));
        }

        int count=1;
        /* Insert to table steps */
        for (int num :
                approvalsGlobal) {
            if(count==1)
                WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(businessTripVM.getId(), num, 1, count++, 1, true));
            else
                WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(businessTripVM.getId(), num, 1, count++, 1, false));
        }

        for (int num :
                executivesGlobal) {
            System.out.println("Executives: " + num);
            WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(businessTripVM.getId(), num, 2, 0, 1, false));
        }

        for (int num :
                referencesGlobal) {
            System.out.println("References: " + num);
            WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(businessTripVM.getId(), num, 3, 0, 1, false));
        }

        System.out.println("FORM:   BUSINESS TRIP: " + businessTripVM.toString());

        /* File upload */
        MultipartFile[] multipartFiles=null;
        if(!businessTripVM.getFile()[0].isEmpty()) {
            multipartFiles = businessTripVM.getFile();

            // Uploading files attached to C:/files/documents/workflow. Create folder if doesn't exist.
            File f = new File("C:/files/documents/workflow/" + businessTripVM.getId() + "/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/workflow/" + businessTripVM.getId() + "/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }

        /*  Insert attachments info to table Attachments */
        if(!businessTripVM.getFile()[0].isEmpty()) {
            for (MultipartFile attachment :
                    businessTripVM.getFile()) {
                WorkflowService.insertAttachments(BusinessTripMapper.attachmentsMapper(businessTripVM.getId(), attachment));
            }
        }

        // E-mail is sent here
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        String subject = "";
        String msg = "";
        int[] to;

        /* Sending to approvals*/
        subject = MailMessage.generateSubject(requestId, 1, 1);
        msg = MailMessage.generateMessage(requestId, 1, 1);
        to = approvalsGlobal;
        if (to.length!=0){
            mm.sendMail(to, subject, msg);
            to = null;
        }
        /* Sending to references and executors */
        subject = MailMessage.generateSubject(requestId, 1, 2);
        msg = MailMessage.generateMessage(requestId, 1, 2);
        to = (int[]) ArrayUtils.addAll(referencesGlobal, executivesGlobal);
        if (to.length!=0){
            mm.sendMail(to, subject, msg);
            to = null;
        }
        /* Sending to creator */
        subject = MailMessage.generateSubject(requestId, 1, 4);
        msg = MailMessage.generateMessage(requestId, 1, 4);
        to = new int[1];
        to[0] = UserService.getIdByUsername(principal.getName());
        if (to.length!=0){
            mm.sendMail(to, subject, msg);
            to = null;
        }
        return "redirect: /Workflow/MyForms/Request";
    }


    @RequestMapping(value = "/NewForm/BusinessTripForm", method = RequestMethod.POST, params = "Save")
    public String BusinessTripPostSave(@ModelAttribute BusinessTripVM businessTripVM, BindingResult result, Principal principal) throws IOException {

        System.out.println(result);
        int userId = UserService.getIdByUsername(principal.getName());

        /* Insert to table Requests */

        int requestId = WorkflowService.insertRequests(BusinessTripMapper.requestMapper(businessTripVM, userId, 1,  4, false));
        businessTripVM.setId(requestId);

        /* Insert to table Members */
             for (MembersEntity member :
                    businessTripVM.getMembersEntityList()) {
                 if (member.getUserId()!=0)
                    WorkflowService.insertMembers(BusinessTripMapper.membersMapper(businessTripVM, member, userId));
             }

        /* Insert to table to_do */
             for (ToDoEntity todo :
                    businessTripVM.getToDoEntityList()) {
                 if (todo.getRequestId() != 0)
                    WorkflowService.insertToDo(BusinessTripMapper.toDoMapper(businessTripVM.getId(), todo));
            }


        /*  Insert attachments info to table Attachments */

            for (MultipartFile attachment :
                    businessTripVM.getFile()) {
                if (attachment.getSize()!=0)
                    WorkflowService.insertAttachments(BusinessTripMapper.attachmentsMapper(businessTripVM.getId(), attachment));
            }



        int count=1;
        /* Insert to table steps */

        if (approvalsGlobal != null){
            for (int num :approvalsGlobal) {
                System.out.println("Approvals: " + num);
                WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(businessTripVM.getId(), num, 1, count++, 4, false));
            }
        }

        if (executivesGlobal != null){
            for (int num :executivesGlobal) {
                System.out.println("Executives: " + num);
                WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(businessTripVM.getId(), num, 2, 0, 4, false));
            }
        }

        if (referencesGlobal != null){
            for (int num :referencesGlobal) {
                System.out.println("References: " + num);
                WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(businessTripVM.getId(), num, 3, 0, 4, false));
            }
        }

        System.out.println("FORM:   BUSINESS TRIP: " + businessTripVM.toString());
        MultipartFile[] multipartFiles=null;
        if(!businessTripVM.getFile()[0].isEmpty()) {
            multipartFiles = businessTripVM.getFile();

            // Uploading files attached to C:/files/documents/workflow. Create folder if doesn't exist.
            File f = new File("C:/files/documents/workflow/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/workflow/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }
        return "redirect: /Workflow/MyForms/Request";
    }



    @RequestMapping(value = "/NewForm/BusinessTripFormAjax", method = RequestMethod.POST)
    public @ResponseBody
    int[] BusinessTripPostAjax(@RequestParam("approvals") int[] approvals, @RequestParam("executives") int[] executives, @RequestParam("references") int[] references){

        approvalsGlobal = approvals;
        executivesGlobal = executives;
        referencesGlobal = references;

        return approvals;
    }

    @RequestMapping(value = "/Users")
    public @ResponseBody
    List<UserInfo> ReturnUsers() {

        List<UserInfo> users = new ArrayList<>();
        users.add(new UserInfo(0, ""));

        UserLocalizationsEntity userLoc=null;
        for (UsersEntity user :
                UserService.getAllUsers()) {
            if(user.isEnabled()==true) {
                // Retrieving user localizations info from DB for all users and check for null
                if(user.getId()!=0 || UserService.getUserLocByUserId(user.getId(), 3)!=null) {
                    try {
                        userLoc = UserService.getUserLocByUserId(user.getId(), 3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Inserting user Id as id, fullname as name, jobTitle as jobTitle and department as department
                ProfileViewModel prof =  UserController.getProfileByUsername(user.getUserName());

                users.add(new UserInfo(Integer.parseInt(prof.getId()), userLoc.getFirstName() + " " + userLoc.getLastName()));
            }
        }

        return users;
    }

}
