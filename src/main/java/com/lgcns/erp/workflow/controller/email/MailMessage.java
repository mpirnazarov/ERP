package com.lgcns.erp.workflow.controller.email;

import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.Enums.LeaveType;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.Mapper.MembersMapper;
import com.lgcns.erp.workflow.Model.Member;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
            msg = "Deletion of new workflow";
        }
        else if(actionStep == 3) {
            msg = "Update of workflow";
        }
        else if(actionStep == 4 || actionStep ==5) {
            msg = "Approve action on workflow";
        }
        else if(actionStep == 7) {
            msg = "Reject workflow";
        }
        else if(actionStep == 8) {
            msg = "Resubmit workflow";
        }

        return msg;
    }

    public static String generateMessage(int requestId, int actionStep, int involvementType, int userId) {

        /* Message creation */

        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(requestId);
        int typeOfBusinessTrip;
        int formTypeId = 0;

        String msg = "";
        String formType = "";
        String creator = "";
        String creationDate = "";
        String title = "";
        String description = "";
        String destination = "";
        String duration = "";
        String approvalsStr = "", executivesStr = "", referencesStr = "";
        String businesstripStr = "", unformattedStr = "", leaveapproveStr = "", footerStr = "", approvalheaderStr = "", authorCreateStr = "", SignatureStr = "", approvalCreateBodyStr = "";
        String token = null;

        String involvement = "";
        String businessTripTypeStr = "", leaveApproveTypeStr = "";
        String involvementTypeStr = "";
        List<Member> approvals = new ArrayList<>();
        List<Member> executives = new ArrayList<>();
        List<Member> references = new ArrayList<>();
        List<Member> members = new ArrayList<>();
        Collections.sort(approvals);
        Collections.sort(executives);
        Collections.sort(references);
        List<StepsEntity> stepsEntities = new LinkedList<>();
        Member member = new Member();

        /**
         * Generate token for sending through email
         */

        /* Firstly generate redirect URL */
        String redirectUrl = generateURL(requestId, actionStep, involvementType, userId);
        token = EmailService.generateToken(userId, redirectUrl, 168);

        formType = Type.values()[requestsEntity.getTypeId() - 1].toString().replace("_", " ");
        formTypeId = requestsEntity.getTypeId();
        creator = UserService.getUserFullNameInLanguageById(requestsEntity.getUserFromId(), 3);
        creationDate = requestsEntity.getDateCreated().toString();
        title = requestsEntity.getSubject();
        description = requestsEntity.getDescription();

        destination = requestsEntity.getDestination();
        typeOfBusinessTrip = requestsEntity.getTripTypeId();
        if(involvementType != 2)
            involvementTypeStr = WorkflowService.getInvolvementType(involvementType).getName();
        else{
            involvementTypeStr = WorkflowService.getInvolvementType(involvementType).getName();
            involvementTypeStr += " or " + WorkflowService.getInvolvementType(involvementType + 1).getName();
        }

        duration = "Start: " + requestsEntity.getDateFrom().toString() + " | End: " + requestsEntity.getDateTo().toString();
        businessTripTypeStr = WorkflowService.getTripType(typeOfBusinessTrip);
        if (requestsEntity.getLeaveTypeId() != null)
            leaveApproveTypeStr = LeaveType.values()[requestsEntity.getLeaveTypeId() - 1].name().replace("_", " ");
        stepsEntities = WorkflowService.getStepsByRequestId(requestId);
        int appId = 1, execId = 1, refId = 1;
        /* Get list of approvals, executives, references */
        for (StepsEntity stepsEntity :
                stepsEntities) {
            member = MembersMapper.getMember(stepsEntity.getUserId());
            if (stepsEntity.getInvolvementTypeId() == 1) {
                approvals.add(member);
                approvalsStr += "<br>&nbsp;&nbsp;&nbsp;&nbsp;" + appId++ + ". " + member.toString();
            } else if (stepsEntity.getInvolvementTypeId() == 2) {
                executives.add(member);
                executivesStr += "<br>&nbsp;&nbsp;&nbsp;&nbsp;" + execId++ + ". " + member.toString();
            } else if (stepsEntity.getInvolvementTypeId() == 3) {
                references.add(member);
                referencesStr += "<br>&nbsp;&nbsp;&nbsp;&nbsp;" + refId++ + ". " + member.toString();
            }
        }

        /*FormStrings*/

        businesstripStr = "<br><br>Business Trip details are as follows:<br><br>" +
                "[Subject] - " + title + "<br>" +
                "[Type of business trip] - " + businessTripTypeStr + "<br>" +
                "[Destination] - " + destination + "<br>" +
                "[Purpose] - " + description;
        String absenceTypeStr = "";
        if(requestsEntity.getTypeId() == 2 && requestsEntity.getLeaveTypeId()!=null)
            absenceTypeStr = LeaveType.values()[requestsEntity.getLeaveTypeId().intValue()-1].name().replace("_", " ");
        leaveapproveStr = "<br><br>Leave approve details are as follows: <br><br>" +
                "[Absence type] - " + absenceTypeStr + "<br>";
                /* [Leaving hours] 1 - 8 hours; 2 - 4AM; 3- 4PM */
                if(requestsEntity.getTypeId() ==2 &&  requestsEntity.getDateFrom().compareTo(requestsEntity.getDateTo())==0  ) {
                    if(requestsEntity.getLeaveTypeId().intValue()==2) {
                        leaveapproveStr += "[Leaving hours] - ";
                        if (requestsEntity.getLeavingHours() == 1) {
                            leaveapproveStr += "8 HR";
                        } else if (requestsEntity.getLeavingHours() == 2) {
                            leaveapproveStr += "4 AM";
                        } else if (requestsEntity.getLeavingHours() == 3) {
                            leaveapproveStr += "4 PM";
                        }
                        leaveapproveStr += "<br>";
                    }
                }

        leaveapproveStr += "[Description] - " + description;

        unformattedStr = "<br><br>Unformatted details are as follows: <br><br>" +
                "[Subject] - " + title + "<br>" +
                "[Description] - " + description;

        approvalheaderStr = "<br>Dear Smart Office user, <br><br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;We would like to notify you that, " +
                creator + " has created the " + formType + " form in workflow system on " +
                creationDate + ", and you were selected as " + involvementTypeStr + ".";

        approvalCreateBodyStr = " When it's your turn to make a decision, " +
                "you will be notified through Smart Office system in the Workflow/My Forms/To-do tab.<br> ";

        authorCreateStr = "<br>Dear " + creator + "<br><br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;We would like to notify you that, your " +
                formType + " form is successfully submitted. " +
                "You can check the current state of your form in the Workflow/MyForms/Request tab.";


        footerStr = "<br><br>[Approvals] " + approvalsStr + "<br>" +
                "<br>[Executives] " + executivesStr + "<br>" +
                "<br>[References] " + referencesStr + "<br>" +
                "<br>[Duration] " + duration;

        String auth = "";
        if(involvementType == 1 || involvementType == 4) {
            auth = "<div style='border:1px solid #000'><a href=''>Authorize</a></div>";
            auth = "<a href=\"http://192.168.1.78/auth?token=" + token + "\" target=\"_blank\" style=\"display: inline-block;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #ffffff; background-color: #1BBCCE; border-radius: 0px; -webkit-border-radius: 0px; -moz-border-radius: 0px; max-width: 134px; width: 126px; width: 25%; border-top: 4px solid #1BBCCE; border-right: 4px solid #1BBCCE; border-bottom: 4px solid #1BBCCE; border-left: 4px solid #1BBCCE; padding-top: 5px; padding-right: 0px; padding-bottom: 5px; padding-left: 0px; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;mso-border-alt: none\">\n" +
                    "      <span style=\"font-size:16px;line-height:32px;\"><strong><span style=\"font-size: 14px; line-height: 28px;\" data-mce-style=\"font-size: 14px; line-height: 28px;\">View</span></strong></span>\n" +
                    "    </a>";
        }
        SignatureStr = "<br><br>" + auth +
                "<br>Thank you for your attention,<br>" +
                "Best regards<br>" +
                "Technical Department team";


        /*FormStrings End*/


        /*Workflow Created Message*/
        if (actionStep == 1) {

            /*Message For Approval, Executive, Reference*/
            if (involvementType != 4) {
                msg += approvalheaderStr;

                /*Meesage For Approval*/
                if (involvementType == 1) {
                    msg += approvalCreateBodyStr;
                }
            } else {
                msg += authorCreateStr; /*Message For Author*/
            }
        }

        /*Workflow Deletion Message*/
        else if (actionStep == 2) {
            /*Message ONLY for Approval*/
            if (involvementType == 1 || involvementType == 3) {
                msg += " Dear Smart Office user,<br><br>" +
                        "&nbsp;&nbsp;&nbsp;&nbsp;We would like to notify you that " + creator + " has deleted " + formType + " form, that was created at" + creationDate;

            }

        }

        /*Workflow Update Message*/
        else if (actionStep == 3) {

            if (involvementType != 4) {
                msg += "Dear Smart Office user,<br>" +
                        "&nbsp;&nbsp;&nbsp;&nbsp;We would like to notify you that " + creator + " has updated " + formType + " form, that was created at " + creationDate;
            }

        }

        /*Massege for Author and next Approval*/
        else if (actionStep == 4){
            if (involvementType == 1){
                msg += "Dear Smart Office user,<br>" +
                        "&nbsp;&nbsp;&nbsp;&nbsp;We would like to notify you that, you have to take an action on a " + formType + " form" +
                        " that was created by " + creator + ". Please go to Workflow/My Forms/To-do tab in Smart Office system," +
                        " and take corresponding actions.  ";
            } else if (involvementType == 4){
                msg += "Dear " + creator + ",<br>" +
                        "One of the approvals has approved your" + formType + " form.";
            }
        }

        /*Workflow Approved*/
        else if (actionStep == 5){
            if (involvementType == 2 || involvementType == 3){
                msg += "Dear Smart Office user, <br>" +
                        formType + " form that was created by " + creator + " was confirmed by all approvals.";
            }else if (involvementType == 4){
                msg += "Dear " + creator + ",<br>" +
                        "Your " + formType + " form, was confirmed by all approvals.";
            }
        }

        /*Workflow Reject*/
        else if (actionStep == 7){

            if (involvementType == 1){
                msg += "Dear Smart Office user, <br>" +
                        "We would like to notify you that, " + formType + " form, " +
                        "that was created by " + creator + " is rejected.";
            }else if(involvementType == 4){
                msg += "Dear " + creator + ", <br>" +
                        "Your " + formType + " form, was rejected. For additional information," +
                        " please go to Workflow/My Forms/Request tab in Smart Office system.";
            }
        }

        /*Workflow Resubmit*/
        else if (actionStep == 8){

            if (involvementType != 4){
                msg += "Dear Smart Office user, <br>" +
                        "We would like to notify you that " + creator + " updated his/her workflow. For additional information," +
                        " please go to Workflow/My Forms/To-do tab in Smart Office system.";
            }

        }


        if (formTypeId == 1) {
            msg += businesstripStr;
        } else if (formTypeId == 2) {
            msg += leaveapproveStr;
        } else if (formTypeId == 3) {
            msg += unformattedStr;
        } else if (formTypeId == 4) {
                    /*generateTermination*/
        }

        msg += footerStr;
        msg += SignatureStr;

        return msg;
    }

    private static String generateURL(int requestId, int actionStep, int involvementType, int userId) {
        String URL = "/";

        /*if(actionStep == 1){*/
            if(involvementType == 1){
                URL = "/Workflow/MyForms/details/1/" + requestId;
            }
            else if(involvementType == 4){
                URL = "/Workflow/MyForms/details/2/" + requestId;
            }

        /*}*/
        return URL;
    }

    public static String generateRestorePasswordMessage(int userId) {
        String msg = "";
        String fullName = UserService.getUserFullNameInLanguageById(userId, 3);
        String URL = "http://192.168.1.78/restorePassword?token=";
        String token = EmailService.generateToken(userId, "/", 168);
        msg += "Dear " + fullName + msg + ", <br>" +
                "You requested password recovery. In order to recover password, please press the button below. <br>" ;
        msg += "<br><br><h1><a href='" + URL + "?token=" + token + "'>Authorize</a></h1>" +
                "<br>Thank you for your attention,<br>" +
                "Best regards<br>" +
                "Technical Department team";

        return msg;
    }

}
