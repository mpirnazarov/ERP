package com.lgcns.erp.workflow.controller.newForm;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.model.UserInfo;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.Enums.LeaveType;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.Mapper.AuthTokenMapper;
import com.lgcns.erp.workflow.Mapper.MembersMapper;
import com.lgcns.erp.workflow.Model.Member;
import com.lgcns.erp.workflow.controller.email.MailMail;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Muslimbek Pirnazarov on 1/27/2017.
 */
@Controller
@RequestMapping(value = "/Workflow")
public class Test {

    @RequestMapping(value = "/Test", method = RequestMethod.GET)
    public ModelAndView Test(Principal principal) {
        ModelAndView mav = new ModelAndView();
        // add JSON array to ModelAndView
        mav.setViewName("workflow/newForm/test");
        mav = UP.includeUserProfile(mav, principal);

        List<UserInfo> userInfos = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setID(0);
        userInfo.setFirstName("Muslimbek");
        userInfo.setLastName("Pirnazarov");
        userInfos.add(userInfo);
        userInfo = new UserInfo();
        userInfo.setID(1);
        userInfo.setFirstName("Sergio");
        userInfo.setLastName("Nocco");
        userInfos.add(userInfo);
        userInfo = new UserInfo();
        userInfo.setID(2);
        userInfo.setFirstName("Maurisio");
        userInfo.setLastName("Marisio");
        userInfos.add(userInfo);

        mav.addObject("products", userInfos);
        return mav;
    }

    @RequestMapping(value = "/Mailtest", method = RequestMethod.GET)
    public ModelAndView MailTest(Principal principal) {
        ModelAndView mav = new ModelAndView();
        // add JSON array to ModelAndView
        mav.setViewName("workflow/newForm/test");

        String subject = "";
        String msg = "";
        int[] to;


        int requestId = 131;
        int actionStep = 1;
        int involvementType = 1;
        int formTypeId = 0;

        /* Message creation */

        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(requestId);
        int typeOfBusinessTrip;

        String formType = "";
        String creator = "";
        String creationDate = "";
        String title = "";
        String description = "";
        String destination = "";
        String duration = "";
        String approvalsStr = "", executivesStr = "", referencesStr = "";
        String businesstripStr = "", unformattedStr = "", leaveapproveStr = "", footerStr = "", approvalheaderStr = "", authorCreateStr = "", SignatureStr = "", approvalCreateBodyStr = "";

        String involvement = "";
        String businessTripTypeStr = "", leaveApproveTypeStr = "";
        String involvementTypeStr = "";
        List<Member> approvals = new ArrayList<>();
        List<Member> executives = new ArrayList<>();
        List<Member> references = new ArrayList<>();
        List<Member> members = new ArrayList<>();

        List<StepsEntity> stepsEntities = new LinkedList<>();
        Member member = new Member();


        formType = Type.values()[requestsEntity.getTypeId() - 1].toString().replace("_", " ");
        formTypeId = requestsEntity.getTypeId();
        creator = UserService.getUserFullNameInLanguageById(requestsEntity.getUserFromId(), 3);
        creationDate = requestsEntity.getDateCreated().toString();
        title = requestsEntity.getSubject();
        description = requestsEntity.getDescription();

        destination = requestsEntity.getDestination();
        typeOfBusinessTrip = requestsEntity.getTripTypeId();
        involvementTypeStr = WorkflowService.getInvolvementType(involvementType).getName();

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
                approvalsStr += "\n\t" + appId++ + ". " + member.toString();
            } else if (stepsEntity.getInvolvementTypeId() == 2) {
                executives.add(member);
                executivesStr += "\n\t" + execId++ + ". " + member.toString();
            } else if (stepsEntity.getInvolvementTypeId() == 3) {
                references.add(member);
                referencesStr += "\n\t" + refId++ + ". " + member.toString();
            }
        }

        /*FormStrings*/

        businesstripStr = "\nBusiness Trip details are as follows:\n\n" +
                "[Subject] - " + title + "\n" +
                "[Type of business trip] - " + businessTripTypeStr + "\n" +
                "[Destination] - " + destination + "\n" +
                "[Purpose] - " + description;

        leaveapproveStr = "\nLeave approve details are as follows: \n\n" +
                "[Absence type] - " + title + "\n" +
                "[Description] - " + description;

        unformattedStr = "\nUnformatted details are as follows: \n\n" +
                "[Subject] - " + title + "\n" +
                "[Description] - " + description;

        approvalheaderStr = "\nDear Smart Office user, \n\n" +
                "\tWe would like to notify you that, " +
                creator + " has created the " + formType + " form in workflow system on " +
                creationDate + ", and you were selected as " + involvementTypeStr + ".";

        approvalCreateBodyStr = " When it's your turn to make a decision, " +
                "you will be notified through Smart Office system in the Workflow/My Forms/To-do tab.\n ";

        authorCreateStr = "\nDear " + creator + "\n\n" +
                "\tWe would like to notify you that, your " +
                formType + " form is successfully submitted. " +
                "You can check the current state of your form in the Workflow/MyForms/Request tab.";


        footerStr = "\n\n[Approvals] " + approvalsStr + "\n" +
                "\n[Executives] " + executivesStr + "\n" +
                "\n[References] " + referencesStr + "\n" +
                "\n[Duration] " + duration;

        SignatureStr = "\n\nThank you for your attention," +
                "Best regards\n" +
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
                msg += " Dear Smart Office user,\n\n" +
                        "\tWe would like to notify you that " + creator + " has deleted " + formType + " form, that was created at " + creationDate;

            }

        }

        /*Workflow Update Message*/
        else if (actionStep == 3) {

            if (involvementType != 4) {
                msg += "Dear Smart Office user,\n" +
                        "\tWe would like to notify you that " + creator + " has updated " + formType + " form, that was created at " + creationDate;
            }

        }

        /*Massege for Author and next Approval*/
        else if (actionStep == 4){
            if (involvementType == 1){
                msg += "Dear Smart Office user,\n" +
                        "\tWe would like to notify you that, you have to take an action on a " + formType +
                        " that was created by " + creator + ". Please go to Workflow/My Forms/To-do tab in Smart Office system," +
                        " and take corresponding actions.  ";
            } else if (involvementType == 4){
                msg += "Dear" + creator + ",\n" +
                        "One of the approvals has approved your" + formType + " form.";
            }
        }

        /*Workflow Approved*/
        else if (actionStep == 5){

            if (involvementType == 2 || involvementType == 3){
                msg += "Dear Smart Office user, \n" +
                        formType + " form that was created by " + creator + " was confirmed by all approvals.";
            }else if (involvementType == 4){
                msg += "Dear " + creator + ",\n" +
                        "Your " + formType + " form, was confirmed by all approvals.";
            }
        }

        /*Workflow Reject*/
        else if (actionStep == 7){

            if (involvementType == 1){
                msg += "Dear Smart Office user, \n" +
                        "We would like to notify you that, " + formType + " form, " +
                        "that was created by " + creator + " is rejected.";
            }else if(involvementType == 4){
                msg += "Dear " + creator + ", \n" +
                        "Your " + formType + " form, was rejected. For additional information," +
                        " please go to Workflow/My Forms/Request tab in Smart Office system.";
            }
        }

        /*Workflow Resubmit*/
        else if (actionStep == 8){

            if (involvementType != 4){
                msg += "Dear Smart Office user, \n" +
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



        /* Message creation */
        mav.addObject("subject", subject);
        mav.addObject("msg", msg);

        return mav;
    }

    @RequestMapping(value = "/sc", method = RequestMethod.GET)
    public ModelAndView sc(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav = UP.includeUserProfile(mav, principal);
        mav.setViewName("scheduleManagement/main/scheduleIndex");


        return mav;
    }

    /* Testing HTML Mail sending*/
    @RequestMapping(value = "/htmlMailTest", method = RequestMethod.GET)
    public String htmlMailTest(Principal principal){
        ModelAndView mav = new ModelAndView();
        String secret = null;
        String token=  null;
        try {
            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            secret = bytes.toString();

            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("Json", "1234321")
                    .sign(algorithm);
            System.out.println("TOKEN: " + token);
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        DateTime expireDate = new DateTime().plusHours(24);
        /* Adding new token and expire date to DB */
        UserService.insertAuthToken(AuthTokenMapper.mapAuthToken(UserService.getUserIdByUsername(principal.getName()), token, expireDate, secret));

        MailMail mailMail = new MailMail();
        int[] to = new int[1];
        to[0] = 1;
        mailMail.sendHtmlMail(2 , "Subject HTML", token);

        /*String resourceName = "app.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("PROPERTY: " + props.getProperty("app.path"));*/
        // use props here ...

        return "redirect: /";
    }
}


    /*@RequestMapping(value = "/Test", method = RequestMethod.POST)
    public void Test(@RequestParam(value = "myArray") int a) {
        System.out.println("ARRAY: " + a);

    }*/


