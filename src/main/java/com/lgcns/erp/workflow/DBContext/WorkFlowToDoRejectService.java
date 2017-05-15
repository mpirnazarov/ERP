package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * Created by DS on 11.02.2017.
 */
public class WorkFlowToDoRejectService {
    public static void reject(int reqId, int statusId, String comment) throws IOException {
        List<StepsEntity> list = WorkflowToDoApproveService.getTheStepsByReqId(reqId);
        int stepId = 0;

        for (StepsEntity stepsEntity : list) {
            if (stepsEntity.getActive()){
                stepId = stepsEntity.getId();
            }
        }
        WorkflowToDoApproveService.setStepComment(stepId, comment, statusId);
        WorkflowToDoApproveService.approve(reqId, statusId);
        WorkflowToDoApproveService.submitRequest(reqId, statusId);
        sendRejectEmail(reqId);
    }

    private static void sendRejectEmail(int requestId) throws IOException {
        // E-mail is sent here
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        String subject = "";
        String msg = "";
        int[] to;

        /* Sending to approvals*/
        to = WorkflowEmailService.getInvolvementList(requestId, 1);
        subject = MailMessage.generateSubject(requestId, 7, 1);


        if (to.length!=0) {
            for (int userId :
                    to) {
                msg = MailMessage.generateMessage(requestId, 7, 1, userId);
                EmailService.sendHtmlMail(userId, subject, msg);
            }
        }
        to = null;
        int idCreator = WorkflowService.getRequestsEntityById(requestId).getUserFromId();
        /* Sending to creator */
        subject = MailMessage.generateSubject(requestId, 7, 4);
        msg = MailMessage.generateMessage(requestId, 7, 4, idCreator);
        EmailService.sendHtmlMail(idCreator, subject, msg);
    }
}
