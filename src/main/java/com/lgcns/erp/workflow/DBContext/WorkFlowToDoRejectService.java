package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by DS on 11.02.2017.
 */
public class WorkFlowToDoRejectService {
    public static void reject(int reqId, int statusId, String comment){
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

    private static void sendRejectEmail(int requestId){
        // E-mail is sent here
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        String subject = "";
        String msg = "";
        int[] to;

        /* Sending to approvals*/
        subject = MailMessage.generateSubject(requestId, 7, 1);
        msg = MailMessage.generateMessage(requestId, 7, 1);
        to = WorkflowEmailService.getInvolvementList(requestId, 1);
        mm.sendMail(to, subject, msg);

        /* Sending to creator */
        subject = MailMessage.generateSubject(requestId, 7, 4);
        msg = MailMessage.generateMessage(requestId, 7, 4);
        to[0] = WorkflowService.getRequestsEntityById(requestId).getUserFromId();
        mm.sendMail(to, subject, msg);
    }
}
