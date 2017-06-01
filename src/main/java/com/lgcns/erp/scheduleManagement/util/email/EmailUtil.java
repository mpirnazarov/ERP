package com.lgcns.erp.scheduleManagement.util.email;

import com.lgcns.erp.scheduleManagement.DBContext.DetailsContext;
import com.lgcns.erp.scheduleManagement.enums.ScheduleEventInvolvement;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by DS on 26.04.2017.
 */
public class EmailUtil {

    private static String subject = "";
    private static String msg = "";

    public static void sendEmailToParticipants(int scheduleId, int[] participantIds, int action, String message) {
        /* Sending to participants*/
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        subject="";
        msg="";
        EmailMessageContent content = new EmailMessageContent();

        if (participantIds.length != 0 && participantIds != null) {
            for (int participantId : participantIds) {
                subject = content.generateSubject(action);
                msg = content.generateMessage(scheduleId, action, ScheduleEventInvolvement.Participant.getValue(), participantId);
                mm.sendHtmlMail(participantId, subject, msg);
            }
        }
    }

    public static void sendEmailToReferences(int scheduleId, int[] referenceIds, int action, String message) {
        /* Sending to references */
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        subject="";
        msg="";
        EmailMessageContent content = new EmailMessageContent();

        if (referenceIds.length != 0 && referenceIds != null) {
            for (int referenceId : referenceIds) {
                subject = content.generateSubject(action);
                msg = content.generateMessage(scheduleId, action, ScheduleEventInvolvement.Referenced.getValue(), referenceId);
                mm.sendHtmlMail(referenceId, subject, msg);
            }
        }
    }

    /*public static void sendEmailToAuthor(int scheduleId, int[] author, int action, String message) {
        *//* Sending to creator *//*
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        subject="";
        msg="";
        EmailMessageContent content = new EmailMessageContent();


        for (int i : author) {
            subject = content.generateSubject(action);
            msg = content.generateMessage(scheduleId, action, ScheduleEventInvolvement.Author.getValue(), i);
          //  mm.sendHtmlMail(i, subject, msg);
        }
    }
*/
    public static void sendEmailToAuthorByDecider(int scheduleId, int[] decider, int action, String message) {
        //* Sending to creator *//*
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        subject="";
        msg="";
        EmailMessageContent content = new EmailMessageContent();
        int authorId = DetailsContext.getScheduleById(scheduleId).getAutherId();

        subject = content.generateSubject(action);
        msg = content.generateMessage(scheduleId, action, ScheduleEventInvolvement.Author.getValue(), authorId);
       // mm.sendHtmlMail(authorId, subject, msg);

    }
}
