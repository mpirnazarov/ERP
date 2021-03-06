package com.lgcns.erp.workflow.controller.newForm;

import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.workflow.DBContext.WorkflowEmailService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.Mapper.MembersMapper;
import com.lgcns.erp.workflow.Mapper.UnformattedMapper;
import com.lgcns.erp.workflow.Model.Member;
import com.lgcns.erp.workflow.ViewModel.UnformattedViewModel;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.apache.commons.lang3.ArrayUtils;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DScomputers3 on 20.01.2017.
 */

@Controller
@RequestMapping(value = "/Workflow")
public class UnformattedController {
    int[] approvalsGlobal = null;
    int[] executivesGlobal = null;
    int[] referencesGlobal = null;

    @RequestMapping(value = "NewForm/Unformatted", method = RequestMethod.GET)
    public ModelAndView Hrprofile(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("workflow/newForm/unformatted");
        mav = UP.includeUserProfile(mav, principal);
        // Creating Unformatted ViewModel
        UnformattedViewModel unformattedViewModel = new UnformattedViewModel();
        mav.addObject("unformattedVM", unformattedViewModel);
        // Create Json data about userlist for Approvals, reference and executive persons list
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        int i=0;
        int[] userId = new int[1000];
        String[] userName = new String[1000];
        Map<Integer, String> users = new HashMap<>();
        // Retrieving data of all users from DB
        UserLocalizationsEntity userLoc=null;
        for (UsersEntity user :
                UserService.getAllUsers()) {
            jsonObject = new JSONObject();
            if(user.isEnabled()==true) {
                jsonObject = new JSONObject();
                Member member = MembersMapper.getMember(user.getId());

                // Retrieving user localizations info from DB for all users and check for null

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
        System.out.println("Unformatted is working4!!!");
        return mav;
    }
    @RequestMapping(value = "NewForm/Unformatted", method = RequestMethod.POST, params = "Submit")
    public String NewUnformattedSubmit(@ModelAttribute UnformattedViewModel unformattedVM, Principal principal) throws Exception {

        int userId = UserService.getIdByUsername(principal.getName());

        /* Insert to table Requests */

        int requestId = WorkflowService.insertRequests(UnformattedMapper.requestMapper(unformattedVM, userId, 3, 1, false));
        unformattedVM.setId(requestId);

        int count=1;
        /* Insert to table steps */
        for (int num :
                approvalsGlobal) {
            System.out.println("Approvals: " + num);
            if(count==1)
                WorkflowService.insertSteps(UnformattedMapper.stepsMapper(unformattedVM.getId(), num, 1, count++, 1, true));
            else
                WorkflowService.insertSteps(UnformattedMapper.stepsMapper(unformattedVM.getId(), num, 1, count++, 1, false));
        }

        for (int num :
                executivesGlobal) {
            System.out.println("Executives: " + num);
            WorkflowService.insertSteps(UnformattedMapper.stepsMapper(unformattedVM.getId(), num, 2, 0, 1, false));
        }

        for (int num :
                referencesGlobal) {
            System.out.println("References: " + num);
            WorkflowService.insertSteps(UnformattedMapper.stepsMapper(unformattedVM.getId(), num, 3, 0, 1, false));
        }

        System.out.println("FORM:   LEAVE APPROVAL: " + unformattedVM.toString());

        /* File upload */
        MultipartFile[] multipartFiles=null;
        if(!unformattedVM.getFile()[0].isEmpty()) {
            multipartFiles = unformattedVM.getFile();

            // Uploading files attached to C:/files/documents/workflow. Create folder if doesn't exist.
            File f = new File("C:/files/documents/workflow/"+unformattedVM.getId()+"/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/workflow/" + unformattedVM.getId() + "/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }

        /*  Insert attachments info to table Attachments */
        if(!unformattedVM.getFile()[0].isEmpty()) {
            for (MultipartFile attachment :
                    unformattedVM.getFile()) {
                WorkflowService.insertAttachments(UnformattedMapper.attachmentsMapper(unformattedVM.getId(), attachment));
            }
        }


        WorkflowEmailService.sendNewWorkflowEmail(requestId, principal.getName(), approvalsGlobal, executivesGlobal, referencesGlobal);


        return "redirect: /Workflow/MyForms/Request";
    }

   /* @InitBinder
    public void initBinder( WebDataBinder binder) throws ServletException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
*/
    @RequestMapping(value = "NewForm/Unformatted", method = RequestMethod.POST, params = "Save")
    public String NewUnformattedSave(@ModelAttribute UnformattedViewModel unformattedVM, BindingResult result, Principal principal) throws Exception {

        int userId = UserService.getIdByUsername(principal.getName());

        /* Insert to table Requests */

        int requestId = WorkflowService.insertRequests(UnformattedMapper.requestMapper(unformattedVM, userId, 3, 4, false));
        unformattedVM.setId(requestId);

        /*  Insert attachments info to table Attachments */
        if(!unformattedVM.getFile()[0].isEmpty()) {
            for (MultipartFile attachment :
                    unformattedVM.getFile()) {
                WorkflowService.insertAttachments(UnformattedMapper.attachmentsMapper(unformattedVM.getId(), attachment));
            }
        }

        int count=1;
        /* Insert to table steps */
        if (approvalsGlobal!=null){
            for (int num :
                    approvalsGlobal) {
                System.out.println("Approvals: " + num);
                WorkflowService.insertSteps(UnformattedMapper.stepsMapper(unformattedVM.getId(), num, 1, count++, 4, false));
            }
        }

        if (executivesGlobal!=null){
            for (int num :
                    executivesGlobal) {
                System.out.println("Executives: " + num);
                WorkflowService.insertSteps(UnformattedMapper.stepsMapper(unformattedVM.getId(), num, 2, 0, 4, false));
            }
        }

        if (referencesGlobal!=null){
            for (int num :
                    referencesGlobal) {
                System.out.println("References: " + num);
                WorkflowService.insertSteps(UnformattedMapper.stepsMapper(unformattedVM.getId(), num, 3, 0, 4, false));
            }
        }

        System.out.println("FORM:   LEAVE APPROVAL: " + unformattedVM.toString());

        /* File upload */
        MultipartFile[] multipartFiles=null;
        if(!unformattedVM.getFile()[0].isEmpty()) {
            multipartFiles = unformattedVM.getFile();

            // Uploading files attached to C:/files/documents/workflow. Create folder if doesn't exist.
            File f = new File("C:/files/documents/workflow/"+unformattedVM.getId()+"/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/workflow/" + unformattedVM.getId() + "/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }

        return "redirect: /Workflow/MyForms/Request";
    }

    @RequestMapping(value = "/NewForm/UnformattedFormAjax", method = RequestMethod.POST)
    public @ResponseBody
    int[] UnformattedPostAjax(@RequestParam("approvals") int[] approvals, @RequestParam("executives") int[] executives, @RequestParam("references") int[] references){

        approvalsGlobal = approvals;
        executivesGlobal = executives;
        referencesGlobal = references;

        return approvals;
    }
}
