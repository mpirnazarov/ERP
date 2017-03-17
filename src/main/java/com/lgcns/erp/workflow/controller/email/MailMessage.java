package com.lgcns.erp.workflow.controller.email;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.MembersEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.Mapper.MembersMapper;
import com.lgcns.erp.workflow.Model.Member;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Muslimbek Pirnazarov on 3/1/2017.
 */
public class MailMessage {

    public static String generateSubject(int requestId, int actionStep, int involvementType) {
        String msg = "";

        if(actionStep == 1) {
            msg = "Creation of new workflow";
        }
        else if(actionStep == 2) {
            msg = "Creation of new workflow";
        }

        return msg;
    }

    public static String generateMessage(int requestId, int actionStep, int involvementType) {
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(requestId);

        String formType = "";
        String creator = "";
        String creationDate = "";
        String title = "";
        String destination = "";

        String msg = "";
        String involvement = "";

        List<Member> approvals = new ArrayList<>();
        List<Member> executives = new ArrayList<>();
        List<Member> references = new ArrayList<>();
        List<Member> members = new ArrayList<>();

        List<StepsEntity> stepsEntities = new LinkedList<>();
        Member member = new Member();

        formType = Type.values()[requestsEntity.getTypeId()-1].toString().replace("_"," ");
        creator = UserService.getUserFullNameInLanguageById(requestsEntity.getUserFromId(), 3);
        creationDate = requestsEntity.getDateCreated().toString();
        title = requestsEntity.getSubject();
        destination = requestsEntity.getDestination();

        WorkflowService.getStepsByRequestId(requestId);

        /* Get list of approvals, executives, references */
        for(StepsEntity stepsEntity:
                stepsEntities){
            member = MembersMapper.getMember(stepsEntity.getUserId());
            if(stepsEntity.getInvolvementTypeId()==1){
                approvals.add(member);
            }
            else if(stepsEntity.getInvolvementTypeId()==2){
                executives.add(member);
            }
            else if(stepsEntity.getInvolvementTypeId()==3){
                references.add(member);
            }
        }

        System.out.println("Approvals MAIL: " + approvals);
        System.out.println("Executives MAIL: " + executives);
        System.out.println("References MAIL: " + references);

        if(actionStep == 1) {
            if(involvementType != 4) {
                msg = "Dear SmartOffice user,\n" +
                        creationDate + " , " + formType + " form has been created by " + creator + "title: " + title +
                        " in the Workflow system.\n" + "Approval: " + approvals + "Executives: " + executives + "References: " + references ;
                if (involvementType == 1) {
                    msg += "In order to finalize this workflow form, " +
                            "you have to take an action by accessing to your account " +
                            "and following to the To-Do list section in the Workflow menu bar.\n" +
                            "Thank you for your effort. \n";
                }
            }
            else{
                msg = "Dear " + creator + ",\n" +
                        creationDate + ", " + formType + " form has been created by you in the SmartOffice system.\n" +
                        "You will be informed when one of the Approvals will take an action.\n" +
                        "Thank you for your effort.\n";
            }
        }
        else if (actionStep==2){
            if (involvementType == 1){
                msg = "Dear SmartOffice user,\n" +
                        "Recently, "+formType+" form has been created by "+creator+" in the Workflow system.\n" +
                        "In order to finalize this workflow form, you have to take an action by accessing to your account and following to the To-Do list section in the Workflow menu bar.\n" +
                        "Thank you for your effort.\n";
            }else {
                msg = "Dear SmartOffice user,\n" +
                        "Recently, "+formType+" form has been created by "+creator+" in the Workflow system.\n" +
                        "In order to finalize this workflow form, you have to take an action by accessing to your account and following to the To-Do list section in the Workflow menu bar.\n" +
                        "Thank you for your effort.\n";
            }
        }

        else if(actionStep == 3){
            msg = "Dear SmartOffice user,\n" +
                    "Recently created " + formType + "form was updated by " + creator + ".\n";
        }
        msg += "Best regards\n" +
                "Technical Department team.";
        return msg;
    }
}
