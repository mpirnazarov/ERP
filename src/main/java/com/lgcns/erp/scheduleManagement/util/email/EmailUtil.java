package com.lgcns.erp.scheduleManagement.util.email;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.workflow.DBContext.WorkflowEmailService;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by DS on 26.04.2017.
 */
public class EmailUtil {

    public static void sendEmail(int scheduleId, int[] author, int[] participantIds, int[] referenceIds, int action) {
        // E-mail is sent here
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        String subject = "";
        String msg = "";

        sendEmailToParticipants(scheduleId, participantIds, mm, subject, msg);
        sendEmailToReferences(scheduleId, referenceIds, mm, subject, msg);
        sendEmailToAuthor(scheduleId, author, mm, subject, msg);
    }

    private static void sendEmailToParticipants(int scheduleId, int[] participantIds, MailMail mm, String subject, String msg) {
        /* Sending to participants*/
        subject = EmailMessageContent.generateSubject(scheduleId, 2, 1);
        msg = EmailMessageContent.generateMessage(scheduleId, 2, 1);
        if (participantIds.length != 0 && participantIds != null) {
            mm.sendMail(participantIds, subject, msg);
        }
    }

    private static void sendEmailToReferences(int scheduleId, int[] referenceIds, MailMail mm, String subject, String msg) {
        /* Sending to references */
        subject = EmailMessageContent.generateSubject(scheduleId, 2, 3);
        msg = EmailMessageContent.generateMessage(scheduleId, 2, 3);
        if (referenceIds.length != 0 && referenceIds != null) {
            mm.sendMail(referenceIds, subject, msg);
        }
    }

    private static void sendEmailToAuthor(int scheduleId, int[] author, MailMail mm, String subject, String msg) {
        /* Sending to creator */
        subject = MailMessage.generateSubject(scheduleId, 1, 4);
        msg = MailMessage.generateMessage(scheduleId, 1, 4);
        if (author.length != 0 && author != null) {
            mm.sendMail(author, subject, msg);
        }
    }
}
