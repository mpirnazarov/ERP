package com.lgcns.erp.workflow.controller.newForm;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.model.UserInfo;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.Mapper.MembersMapper;
import com.lgcns.erp.workflow.Model.Member;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Muslimbek Pirnazarov on 1/27/2017.
 */
@Controller
@RequestMapping(value = "/Workflow")
public class Test {

    @RequestMapping(value = "/Test", method = RequestMethod.GET)
    public ModelAndView Test(Principal principal){
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
    public ModelAndView MailTest(Principal principal){
        ModelAndView mav = new ModelAndView();
        // add JSON array to ModelAndView
        mav.setViewName("workflow/newForm/test");

        String subject = "";
        String msg = "";
        int[] to;


        int requestId = 391;
        int actionStep = 1;
        int involvementType = 1;

        /* Message creation */

        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(requestId);

        String formType = "";
        String creator = "";
        String creationDate = "";
        String title = "";
        String destination = "";


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

        stepsEntities = WorkflowService.getStepsByRequestId(requestId);

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
        for (Member mem :
                approvals) {
            System.out.println("Approval member: " + mem);
        }
        System.out.println("Executives MAIL: " + executives);
        for (Member mem :
                executives) {
            System.out.println("Executives member: " + mem);
        }
        System.out.println("References MAIL: " + references);
        for (Member mem :
                references) {
            System.out.println("References member: " + mem);
        }

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

        /* Message creation */




        mav.addObject("subject", subject);
        mav.addObject("msg", msg);

        return mav;
    }

    @RequestMapping(value = "/Test", method = RequestMethod.POST)
    public void Test(@RequestParam(value = "myArray") int a) {
        System.out.println("ARRAY: " + a);

    }


}
