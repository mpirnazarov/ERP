package com.lgcns.erp.workflow.controller.newForm;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.Mapper.UnformattedMapper;
import com.lgcns.erp.workflow.ViewModel.UnformattedViewModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
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

        return mav;
    }
    @RequestMapping(value = "NewForm/Unformatted", method = RequestMethod.POST, params = "Submit")
    public String Hrprofile(@ModelAttribute UnformattedViewModel unformattedVM, Principal principal) throws IOException {

        int userId = UserService.getIdByUsername(principal.getName());

        /* Insert to table Requests */

        int requestId = WorkflowService.insertRequests(UnformattedMapper.requestMapper(unformattedVM, userId, 3, 1, false));
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



        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

        String msg = "";
        for (int appr :
                approvalsGlobal) {
            msg += "  [Approval: " + appr + "]";
        }


        // E-mail is sent here

        /*MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMailApproval(approvalsGlobal, principal);
        mm.sendMailReference(referencesGlobal, principal);
        mm.sendMailExecutive(executivesGlobal, principal);
        mm.sendMail("muslimbek.pirnazarov@gmail.com",
                "muslimbek.pirnazarov@gmail.com",
                "Testing123",
                msg);*/



        return "redirect: /Workflow/NewForm/Unformatted";
    }

    @RequestMapping(value = "/NewForm/UnformattedFormAjax", method = RequestMethod.POST)
    public @ResponseBody
    int[] UnformattedPostAjax(@RequestParam("approvals") int[] approvals, @RequestParam("executives") int[] executives, @RequestParam("references") int[] references){
        System.out.println("Unformatted is working.");

        approvalsGlobal = approvals;
        executivesGlobal = executives;
        referencesGlobal = references;

        return approvals;
    }
}
