package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Enums.Type;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.Date;

/**
 * Created by Rafatdin on 15.09.2016.
 */

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String printWelcome(Principal principal) {


        /* For testing purposes */

        int requestId = 67;
        int actionStep = 1;
        int involvementType = 1;
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(requestId);
        String formType = "";
        String msg = "";
        String creator = "";
        String involvement = "";
        String creationDate = "";

        formType = Type.values()[requestsEntity.getTypeId() - 1].toString();
        creator = UserService.getUserFullNameInLanguageById(requestsEntity.getUserFromId(), 3);
        creationDate = requestsEntity.getDateCreated().toString();

        if (actionStep == 1) {
            if (involvementType != 4) {
                msg = "Dear SmartOffice user,\n" +
                        creationDate + " , " + formType + " form has been created by " + creator +
                        " in the Workflow system.\n";
                if (involvementType == 1) {
                    msg += "In order to finalize this workflow form, " +
                            "you have to take an action by accessing to your account " +
                            "and following to the To-Do list section in the Workflow menu bar.\n" +
                            "Thank you for your effort. \n";
                }
                msg += "Best regards\n" +
                        "Technical Department team.";
            } else {
                msg = "Dear " + creator + ",\n" +
                        "Recently, " + formType + " form has been created by you in the SmartOffice system.\n" +
                        "You will be informed when one of the Approvals will take an action.\n" +
                        "Thank you for your effort.\n" +
                        "Best regards\n" +
                        "Technical Department team.";
            }
        } else if (actionStep == 2) {
            if (involvementType == 1) {
                msg = "Dear SmartOffice user,\n" +
                        "Recently, " + formType + " form has been created by " + creator + " in the Workflow system.\n" +
                        "In order to finalize this workflow form, you have to take an action by accessing to your account and following to the To-Do list section in the Workflow menu bar.\n" +
                        "Thank you for your effort.\n" +
                        "Best regards\n" +
                        "Technical Department team.";
            } else {
                msg = "Dear SmartOffice user,\n" +
                        "Recently, " + formType + " form has been created by " + creator + " in the Workflow system.\n" +
                        "In order to finalize this workflow form, you have to take an action by accessing to your account and following to the To-Do list section in the Workflow menu bar.\n" +
                        "Thank you for your effort.\n" +
                        "Best regards\n" +
                        "Technical Department team.";
            }
        } else if (actionStep == 3) {
            msg = "Dear SmartOffice user,\n" +
                    "Recently created " + formType + "form was updated by " + creator + ".\n" +
                    "Best regards\n" +
                    "Technical Department team.";
        }
        System.out.println("------------------- MESSAGE -------------------");
        System.out.println(msg);
        System.out.println("-----------------------------------------------");

        /* For testing purposes END */


        int roleId = UserService.getRoleByUserName(principal.getName());
        System.out.println("ROLE: " + roleId);
        if(roleId==1)
            return "forward: /Manager/Profile";
        else if(roleId==3)
            return "forward: /Hr/Profile";
        else
            return "forward: /User/Profile";
    }

    @RequestMapping(value = "/Sarvartest")
    public ModelAndView printWelcome2(Principal principal) {


        /* For testing purposes */

        int requestId = 67;
        int actionStep = 1;
        int involvementType = 1;
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(requestId);
        String formType = "";
        String msg = "";
        String creator = "";
        String involvement = "";
        String creationDate = "";

        formType = Type.values()[requestsEntity.getTypeId() - 1].toString();
        creator = UserService.getUserFullNameInLanguageById(requestsEntity.getUserFromId(), 3);
        creationDate = requestsEntity.getDateCreated().toString();

        if (actionStep == 1) {
            if (involvementType != 4) {
                msg = "<b>Dear SmartOffice user </b>,\n \n" +
                        creationDate + " , " + formType + " form has been created by " + creator +
                        " in the Workflow system.";
                if (involvementType == 1) {
                    msg += "In order to finalize this workflow form, " +
                            "you have to take an action by accessing to your account " +
                            "and following to the To-Do list section in the Workflow menu bar.\n" +
                            "Thank you for your effort. \n";
                }
                msg += "Best regards\n" +
                        "Technical Department team.";
            } else {
                msg = "Dear " + creator + ",\n" +
                        "Recently, " + formType + " form has been created by you in the SmartOffice system.\n" +
                        "You will be informed when one of the Approvals will take an action.\n" +
                        "Thank you for your effort.\n" +
                        "Best regards\n" +
                        "Technical Department team.";
            }
        } else if (actionStep == 2) {
            if (involvementType == 1) {
                msg = "Dear SmartOffice user,\n" +
                        "Recently, " + formType + " form has been created by " + creator + " in the Workflow system.\n" +
                        "In order to finalize this workflow form, you have to take an action by accessing to your account and following to the To-Do list section in the Workflow menu bar.\n" +
                        "Thank you for your effort.\n" +
                        "Best regards\n" +
                        "Technical Department team.";
            } else {
                msg = "Dear SmartOffice user,\n" +
                        "Recently, " + formType + " form has been created by " + creator + " in the Workflow system.\n" +
                        "In order to finalize this workflow form, you have to take an action by accessing to your account and following to the To-Do list section in the Workflow menu bar.\n" +
                        "Thank you for your effort.\n" +
                        "Best regards\n" +
                        "Technical Department team.";
            }
        } else if (actionStep == 3) {
            msg = "Dear SmartOffice user,\n" +
                    "Recently created " + formType + "form was updated by " + creator + ".\n" +
                    "Best regards\n" +
                    "Technical Department team.";
        }
        System.out.println("------------------- MESSAGE -------------------");
        System.out.println(String.format(msg));
        System.out.println("-----------------------------------------------");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("workflow/newForm/test");
        mav.addObject("msg", String.format(msg));

        return mav;
        /* For testing purposes END */

       }

}
