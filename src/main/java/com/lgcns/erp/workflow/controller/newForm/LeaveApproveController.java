package com.lgcns.erp.workflow.controller.newForm;

import com.lgcns.erp.hr.enums.WorkloadType;
import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.DbContext.WorkloadServices;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.model.DbEntities.WorkloadEntity;
import com.lgcns.erp.workflow.DBContext.WorkflowEmailService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.Enums.LeaveType;
import com.lgcns.erp.workflow.Mapper.LeaveApproveMapper;
import com.lgcns.erp.workflow.Mapper.MembersMapper;
import com.lgcns.erp.workflow.Model.Member;
import com.lgcns.erp.workflow.ViewModel.LeaveApproveVM;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DScomputers3 on 20.01.2017.
 */
@Controller
@RequestMapping(value = "/Workflow")
public class LeaveApproveController {

    int[] approvalsGlobal = null;
    int[] executivesGlobal = null;
    int[] referencesGlobal = null;

    @RequestMapping(value = "/NewForm/LeaveApproveForm", method = RequestMethod.GET)
    public ModelAndView LeaveApproveGET(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("workflow/newForm/leaveApproveForm");
        mav = UP.includeUserProfile(mav, principal);

        // Creating Business Trip ViewModel
        LeaveApproveVM leaveApproveVM = new LeaveApproveVM();
        leaveApproveVM.setLeavingHours(1);
        mav.addObject("leaveApproveVM", leaveApproveVM);

        // Creating current users entity
        UsersEntity usersEntity = UserService.getUserByUsername(principal.getName());

        mav.addObject("curUser", usersEntity);

        boolean sixMonthPassed = false;
        int vacationDaysAvailable=0;
        int usedVacationNumber = getUsedVacationsNumber(usersEntity);

        Date hiringDate = usersEntity.getHiringDate();
        DateTime now = new DateTime();
        DateTime then = new DateTime(hiringDate.getTime());

        int hiringDateInterval = Math.abs(Months.monthsBetween(now, then).getMonths());

        if(hiringDateInterval >= 6){
            System.out.println("6 mo apart!");
            sixMonthPassed = true;
        }
        else{
            System.out.println("6 mo NOT apart!");
            sixMonthPassed = false;
        }

        if(hiringDateInterval < 6){
            vacationDaysAvailable = 0;
        }
        else if(hiringDateInterval >=6 && hiringDateInterval <= 12){
            vacationDaysAvailable = hiringDateInterval*2 - usedVacationNumber;
        }
        else{
            vacationDaysAvailable = 24 - usedVacationNumber;
        }

        if(vacationDaysAvailable < 0){
            vacationDaysAvailable = 0;
        }

        mav.addObject("vacationAvailable", vacationDaysAvailable);
        mav.addObject("sixMonthPassed", sixMonthPassed);

        // Create Json data about userlist for Approvals, reference and executive persons list
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        int i=0;
        int[] userId = new int[1000];
        String[] userName = new String[1000];
        Map<Integer, String> users = new HashMap<>();
        // Retrieving data of all users from DB
        /*UserLocalizationsEntity userLoc=null;*/
        for (UsersEntity user :
                UserService.getAllUsers()) {
            jsonObject = new JSONObject();
            if(user.isEnabled()==true) {
                jsonObject = new JSONObject();
                Member member = MembersMapper.getMember(user.getId());

                jsonObject.put("id", member.getId());
                jsonObject.put("name", member.getFirstName() + " " + member.getLastName());
                jsonObject.put("jobTitle", member.getJobTitle());
                jsonObject.put("department", member.getDepartment());

                users.put(member.getId(), member.getFirstName() + " " + member.getLastName());
                userId[i] = member.getId();
                userName[i++] = member.getFirstName() + " " + member.getLastName();
                if(user.getUserName().compareTo(principal.getName())!=0) {
                    // add object to array
                    jsonArray.add(jsonObject);
                }
            }
        }

        mav.addObject("userId", userId);
        mav.addObject("userName", userName);

        // add JSON array to ModelAndView
        mav.addObject("jsonData", jsonArray);
        mav.addObject("users", users);

        // Retriving data about type of Business trip
        Map<Integer, String> absenceType = new HashMap<>();
        absenceType.put(0, "");
        for (LeaveType leaveType :
                LeaveType.values()) {
            absenceType.put(leaveType.getValue(), leaveType.name().replace("_", " "));
        }

        // Add trip type data to ModelAndView
        mav.addObject("absenceType", absenceType);



        return mav;
    }

    public static int getUsedVacationsNumber(UsersEntity user) {
        LocalDateTime currentTime = LocalDateTime.now();

        Instant i = Instant.ofEpochMilli(user.getHiringDate().getTime());
        LocalDateTime hiringDateTime = LocalDateTime.ofInstant(i, ZoneOffset.UTC);

        hiringDateTime = hiringDateTime.withYear(currentTime.getYear());
        List<WorkloadEntity> workloads;

        if(currentTime.isAfter(hiringDateTime) || currentTime.isEqual(hiringDateTime)){
            LocalDateTime temp = hiringDateTime.plusYears(1);
            Instant instant = hiringDateTime.toInstant(ZoneOffset.UTC), instant1 = temp.toInstant(ZoneOffset.UTC);
            java.util.Date from = Date.from(instant), to = Date.from(instant1);
            workloads = WorkloadServices.getWorkloadByPeriod(user.getId(), 0, WorkloadType.Annual_leave.getValue(), from, to);
        }else{
            LocalDateTime temp = hiringDateTime.minusYears(1);
            Instant instant = hiringDateTime.toInstant(ZoneOffset.UTC), instant1 = temp.toInstant(ZoneOffset.UTC);
            java.util.Date from = Date.from(instant1), to = Date.from(instant);
            workloads = WorkloadServices.getWorkloadByPeriod(user.getId(), 0, WorkloadType.Annual_leave.getValue(), from, to);
        }

        return workloads.size();
    }

    @RequestMapping(value = "/NewForm/LeaveApproveForm", method = RequestMethod.POST, params = "Submit")
    public String LeaveApprovePostSubmit(@ModelAttribute LeaveApproveVM leaveApproveVM, Principal principal) throws Exception {

        int userId = UserService.getIdByUsername(principal.getName());

        /* Insert to table Requests */
        int requestId = WorkflowService.insertRequests(LeaveApproveMapper.requestMapper(leaveApproveVM, userId, 2, 1, false));
        leaveApproveVM.setId(requestId);

        /*  Insert attachments info to table Attachments */
        for (MultipartFile attachment :
                leaveApproveVM.getFile()) {
            WorkflowService.insertAttachments(LeaveApproveMapper.attachmentsMapper(leaveApproveVM.getId(), attachment));
        }

        int count=1;
        /* Insert to table steps */
        for (int num :
                approvalsGlobal) {
            System.out.println("Approvals: " + num);
            if(count==1)
                WorkflowService.insertSteps(LeaveApproveMapper.stepsMapper(leaveApproveVM.getId(), num, 1, count++, 1, true));
            else
                WorkflowService.insertSteps(LeaveApproveMapper.stepsMapper(leaveApproveVM.getId(), num, 1, count++, 1, false));
        }

        for (int num :
                executivesGlobal) {
            System.out.println("Executives: " + num);
            WorkflowService.insertSteps(LeaveApproveMapper.stepsMapper(leaveApproveVM.getId(), num, 2, 0, 1, false));
        }

        for (int num :
                referencesGlobal) {
            System.out.println("References: " + num);
            WorkflowService.insertSteps(LeaveApproveMapper.stepsMapper(leaveApproveVM.getId(), num, 3, 0, 1, false));
        }

        /* File upload */
        MultipartFile[] multipartFiles=null;
        if(!leaveApproveVM.getFile()[0].isEmpty()) {
            multipartFiles = leaveApproveVM.getFile();

            // Uploading files attached to C:/files/documents/workflow. Create folder if doesn't exist.
            File f = new File("C:/files/documents/workflow/"+leaveApproveVM.getId()+"/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/workflow/" + leaveApproveVM.getId() + "/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }

        // E-mail is sent here
        WorkflowEmailService.sendNewWorkflowEmail(requestId, principal.getName(), approvalsGlobal, executivesGlobal, referencesGlobal);

        return "redirect: /Workflow/MyForms/Request";
    }

    @RequestMapping(value = "/NewForm/LeaveApproveForm", method = RequestMethod.POST, params = "Save")
    public String LeaveApprovePostSave(@ModelAttribute LeaveApproveVM leaveApproveVM, BindingResult result, Principal principal) throws Exception {

        int userId = UserService.getIdByUsername(principal.getName());

        /* Insert to table Requests */

        int requestId = WorkflowService.insertRequests(LeaveApproveMapper.requestMapper(leaveApproveVM, userId, 2, 4, false));
        leaveApproveVM.setId(requestId);

        /*  Insert attachments info to table Attachments */
        if(!leaveApproveVM.getFile()[0].isEmpty()) {
            for (MultipartFile attachment :
                    leaveApproveVM.getFile()) {
                WorkflowService.insertAttachments(LeaveApproveMapper.attachmentsMapper(leaveApproveVM.getId(), attachment));
            }
        }
        int count=1;
        /* Insert to table steps */
        if (approvalsGlobal!=null){
            for (int num :
                    approvalsGlobal) {
                System.out.println("Approvals: " + num);
                WorkflowService.insertSteps(LeaveApproveMapper.stepsMapper(leaveApproveVM.getId(), num, 1, count++, 4, false));
            }
        }

        if (executivesGlobal!=null){
            for (int num :
                    executivesGlobal) {
                System.out.println("Executives: " + num);
                WorkflowService.insertSteps(LeaveApproveMapper.stepsMapper(leaveApproveVM.getId(), num, 2, 0, 4, false));
            }
        }

        if (referencesGlobal!=null){
            for (int num :
                    referencesGlobal) {
                System.out.println("References: " + num);
                WorkflowService.insertSteps(LeaveApproveMapper.stepsMapper(leaveApproveVM.getId(), num, 3, 0, 4, false));
            }
        }

        System.out.println("FORM:   LEAVE APPROVAL: " + leaveApproveVM.toString());
        MultipartFile[] multipartFiles=null;
        if(!leaveApproveVM.getFile()[0].isEmpty()) {
            multipartFiles = leaveApproveVM.getFile();

            // Uploading files attached to C:/files/documents/workflow. Create folder if doesn't exist.
            File f = new File("C:/files/documents/workflow/"+leaveApproveVM.getId()+"/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/workflow/" + leaveApproveVM.getId() + "/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }
        return "redirect: /Workflow/MyForms/Request";
    }



    @RequestMapping(value = "/NewForm/LeaveApproveFormAjax", method = RequestMethod.POST)
    public @ResponseBody
    int[] LeaveApprovePostAjax(@RequestParam("approvals") int[] approvals, @RequestParam("executives") int[] executives, @RequestParam("references") int[] references){


        approvalsGlobal = null;
        executivesGlobal = null;
        referencesGlobal = null;

        approvalsGlobal = approvals;
        executivesGlobal = executives;
        referencesGlobal = references;

        return approvals;
    }
}
