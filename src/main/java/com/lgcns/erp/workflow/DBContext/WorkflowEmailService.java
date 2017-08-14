package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Muslimbek Pirnazarov on 3/2/2017.
 */
public class WorkflowEmailService {
    public static int[] getInvolvementList(int reqId, int involvementType) {
        int[] involvementList;
        int i=0;
        List<Integer> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select userId from StepsEntity WHERE requestId=" + reqId + " AND involvementTypeId=" + involvementType);
            list = (List<Integer>)query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        involvementList = new int[list.size()];
        for (Integer num :
                list) {
            involvementList[i++] = num.intValue();
        }

        return involvementList;
    }

    public static void sendNewWorkflowEmail(int requestId, String userName, int[] approvalsGlobal, int[] executivesGlobal, int[] referencesGlobal){
        try {
            // E-mail is sent here
            String subject = "";
            String msg = "";
            String token = null;

            /* Sending to approvals*/
            subject = MailMessage.generateSubject(requestId, 1, 1);

            if (approvalsGlobal.length != 0) {
                for (int userId: approvalsGlobal) {
                    msg = MailMessage.generateMessage(requestId, 1, 1, userId);
                    EmailService.sendHtmlMail(userId, subject, msg);
                }
            }

            /* Sending to executors */
            subject = MailMessage.generateSubject(requestId, 1, 2);

            if (executivesGlobal.length != 0) {
                for (int userId : executivesGlobal) {
                    msg = MailMessage.generateMessage(requestId, 1, 2, userId);
                    EmailService.sendHtmlMail(userId, subject, msg);
                }
            }

            /* Sending to references */
            subject = MailMessage.generateSubject(requestId, 1, 3);

            if (referencesGlobal.length != 0) {
                for (int userId : referencesGlobal) {
                    msg = MailMessage.generateMessage(requestId, 1, 3, userId);
                    EmailService.sendHtmlMail(userId, subject, msg);
                }
            }

            /* Send to Kamola, must be changed in case of HR change (Kamola's ID is 20, hard coded) */
            msg = MailMessage.generateMessage(requestId, 1, 3, 20);
            EmailService.sendHtmlMail(20, subject, msg);

            /* Sending to creator */
            int toCreator;

            toCreator = UserService.getIdByUsername(userName);
            subject = MailMessage.generateSubject(requestId, 1, 4);
            msg = MailMessage.generateMessage(requestId, 1, 4, toCreator);
            EmailService.sendHtmlMail(toCreator, subject, msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
