package com.lgcns.erp.workflow.controller.email;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Enums.Type;

/**
 * Created by Muslimbek Pirnazarov on 3/1/2017.
 */
public class MailMessage {

    public static String generateSubject(int requestId, int actionStep, int involvementType) {
        String msg = "";

        if(actionStep == 1) {
            msg = "Creation of new workflow";
        }

        return msg;
    }

    public static String generateMessage(int requestId, int actionStep, int involvementType) {
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(requestId);
        String formType = "";
        String msg = "";
        String creator = "";
        String involvement = "";

        formType = Type.values()[requestsEntity.getTypeId()].toString();
        creator = UserService.getUserFullNameInLanguageById(requestsEntity.getUserFromId(), 3);

        if(actionStep == 1) {
            if(involvementType != 4) {
                msg = "Dear SmartOffice user,\n" +
                        "Recently, " + formType + " form has been created by " + creator +
                        "in the Workflow system.\n";
                if (involvementType == 1) {
                    msg += "In order to finalize this workflow form, " +
                            "you have to take an action by accessing to your account " +
                            "and following to the To-Do list section in the Workflow menu bar.\n" +
                            "Thank you for your effort.";
                }
                msg += "Best regards\n" +
                        "Technical Department team.";
            }
            else{
                msg = "Dear " + creator + ",\n" +
                        "Recently, " + formType + "form has been created by you in the SmartOffice system.\n" +
                        "You will be informed when one of the Approvals will take an action.\n" +
                        "Thank you for your effort.\n" +
                        "Best regards\n" +
                        "Technical Department team.";
            }
        }
        else if (actionStep==2){
            if (involvementType == 1){
                msg = "Dear SmartOffice user,\n" +
                        "Recently, "+formType+" form has been created by "+creator+" in the Workflow system.\n" +
                        "In order to finalize this workflow form, you have to take an action by accessing to your account and following to the To-Do list section in the Workflow menu bar.\n" +
                        "Thank you for your effort.\n" +
                        "Best regards\n" +
                        "Technical Department team.";
            }
        }

        else if(actionStep == 3){
            msg = "Dear SmartOffice user,\n" +
                    "Recently created " + formType + "form was updated by" + creator + ".\n" +
                    "Best regards\n" +
                    "Technical Department team.";
        }

        return msg;
    }
}
