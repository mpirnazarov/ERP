package com.lgcns.erp.scheduleManagement.util.email;

import com.lgcns.erp.scheduleManagement.enums.ScheduleEventInvolvement;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by DS on 26.04.2017.
 */
public class EmailUtil {

    private static String subject = "";
    private static String msg = "";

    public static void sendEmail(int scheduleId, int[] author, int[] participantIds, int[] referenceIds, int action) {
        // E-mail is sent here
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");


        sendEmailToParticipants(scheduleId, participantIds, mm, action);
        sendEmailToReferences(scheduleId, referenceIds, mm, action);
        sendEmailToAuthor(scheduleId, author, mm, action);
    }

    private static void sendEmailToParticipants(int scheduleId, int[] participantIds, MailMail mm, int action) {
        /* Sending to participants*/
        subject="";
        msg="";

        subject = EmailMessageContent.generateSubject(scheduleId, action, ScheduleEventInvolvement.Participant.getValue());
        msg = EmailMessageContent.generateMessage(scheduleId, action, ScheduleEventInvolvement.Participant.getValue());
        if (participantIds.length != 0 && participantIds != null) {
            mm.sendMail(participantIds, subject, msg);
        }
    }

    private static void sendEmailToReferences(int scheduleId, int[] referenceIds, MailMail mm, int action) {
        /* Sending to references */
        subject="";
        msg="";

        subject = EmailMessageContent.generateSubject(scheduleId, action, ScheduleEventInvolvement.Referenced.getValue());
        msg = EmailMessageContent.generateMessage(scheduleId, action, ScheduleEventInvolvement.Referenced.getValue());
        if (referenceIds.length != 0 && referenceIds != null) {
            mm.sendMail(referenceIds, subject, msg);
        }
    }

    private static void sendEmailToAuthor(int scheduleId, int[] author, MailMail mm, int action) {
        /* Sending to creator */
        subject="";
        msg="";

        subject = MailMessage.generateSubject(scheduleId, action, ScheduleEventInvolvement.Author.getValue());
        msg = MailMessage.generateMessage(scheduleId, action, ScheduleEventInvolvement.Author.getValue());
        if (author.length != 0 && author != null) {
            mm.sendMail(author, subject, msg);
        }
    }
}
