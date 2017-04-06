package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.hr.enums.WorkloadType;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.tapps.DbContext.WorkloadServices;
import com.lgcns.erp.tapps.model.DbEntities.WorkloadEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepCommentsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.Enums.LeaveType;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.Mapper.CommentMapper;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DS on 08.02.2017.
 */
public class WorkflowToDoApproveService {
    public static int getTheNextSequence(int requestId, int statusId, String comment){
        List<StepsEntity> list = getTheStepsByReqId(requestId);
        int currentSequence = 0;
        int currentStepId = 0;
        for (StepsEntity entity : list) {
            if (entity.getActive()){
                currentSequence = entity.getStepSequence();
                currentStepId = entity.getId();
            }
        }
        setStepComment(currentStepId, comment, statusId);
        currentSequence++;
        int nextStepId = 0;
        boolean isNext = false;
        for (StepsEntity stepsEntity : list) {
            if (stepsEntity.getStepSequence().intValue()==currentSequence){
                nextStepId = stepsEntity.getId();
                isNext = true;
            }
        }
        if (isNext)
            return nextStepId;
        else{
            // if it is termination
            RequestsEntity entity = WorkflowService.getRequestsEntityById(requestId);
            if (entity.getTypeId()== Type.Termination.getValue()) {
                submitRequest(entity.getReqLinkId(), Status.Terminated.getValue());
                if (entity.getLeaveTypeId()!=null&&(entity.getLeaveTypeId()==LeaveType.Annual_leave.getValue()
                        ||entity.getLeaveTypeId()==LeaveType.Sick_leave.getValue())){
                    RequestsEntity terminating_entity = WorkflowService.getRequestsEntityById(entity.getReqLinkId());
                    removeHours(terminating_entity);
                }
            }
            approve(requestId, statusId);
            submitRequest(requestId, statusId);
            if (entity.getLeaveTypeId()!=null&&(entity.getLeaveTypeId()==LeaveType.Annual_leave.getValue()
                    ||entity.getLeaveTypeId()==LeaveType.Sick_leave.getValue()))
                addHours(entity);
            sendEmailAfterLastApproves(requestId);

            return -1;
        }
    }

    private static void sendEmailAfterLastApproves(int requestId){

        // E-mail is sent here
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        String subject = "";
        String msg = "";
        int[] to;

        /* Sending to references and executors */
        subject = MailMessage.generateSubject(requestId, 5, 2);
        msg = MailMessage.generateMessage(requestId, 5, 2);
        to = (int[]) ArrayUtils.addAll(WorkflowEmailService.getInvolvementList(requestId, 2), WorkflowEmailService.getInvolvementList(requestId, 3));
        if (to.length!=0) {
            mm.sendMail(to, subject, msg);
            /*mm.sendHtmlMail(to, subject, msg);*/
            to = null;
        }
        /* Sending to creator */
        subject = MailMessage.generateSubject(requestId, 5, 4);
        msg = MailMessage.generateMessage(requestId, 5, 4);
        to[0] = WorkflowService.getRequestsEntityById(requestId).getUserFromId();
        if (to.length!=0) {
            mm.sendMail(to, subject, msg);
         }
    }

    private static void addHours(RequestsEntity entity){
        if (entity.getDateFrom()!=null && entity.getDateTo()!=null) {
            if (entity.getLeaveTypeId() == LeaveType.Annual_leave.getValue())
                addHours(entity.getUserFromId(), WorkloadType.Annual_leave.getValue(), entity.getDateFrom(), entity.getDateTo(), true);
            else
                addHours(entity.getUserFromId(), WorkloadType.Sick_leave.getValue(), entity.getDateFrom(), entity.getDateTo(), true);
        }
     }

     private static void removeHours(RequestsEntity entity){
         if (entity.getDateFrom()!=null && entity.getDateTo()!=null) {
             if (entity.getLeaveTypeId() == LeaveType.Annual_leave.getValue())
                 removeHours(entity.getUserFromId(), WorkloadType.Annual_leave.getValue(), entity.getDateFrom(), entity.getDateTo());
             else
                 removeHours(entity.getUserFromId(), WorkloadType.Sick_leave.getValue(), entity.getDateFrom(), entity.getDateTo());
         }
     }


    protected static List<StepsEntity> getTheStepsByReqId(int requestId){
        List<StepsEntity> list = null;

        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from StepsEntity WHERE requestId="+requestId);
            list = (List<StepsEntity>)query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }

    protected static void setStepComment(int stepId, String comment, int status){
        StepCommentsEntity commentsEntity = CommentMapper.getCommentsFromModel(stepId, status, comment);

        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set forerequestsEntityign key items
            commentsEntity.setStepsByStepsId(session.load(StepsEntity.class, commentsEntity.getStepsId()));

            //Save the object in database
            session.save(commentsEntity);
            //Commit the transaction
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    protected static void approve(int reqId, int statusId){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update StepsEntity set statusId = :status, active = false where requestId= :reqId and active = true");
            query.setParameter("reqId", reqId);
            query.setParameter("status", statusId);
            query.executeUpdate();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }


    public static void setNewStep(int newStepId, int reqId){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update StepsEntity set active = true WHERE id="+newStepId);

            int s = query.executeUpdate();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
            sendEmailToTheNextApprover(reqId, newStepId);
        }
    }

    private static void sendEmailToTheNextApprover(int requestId, int nextApproverId){
        // E-mail is sent here
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        String subject = "";
        String msg = "";
        int[] to = new int[1];

         /* Sending to approvals*/
        subject = MailMessage.generateSubject(requestId, 4, 1);
        msg = MailMessage.generateMessage(requestId, 4, 1);
        to[0] = WorkflowService.getUserIdFromSteps(nextApproverId);
        if (to.length!=0) {
            mm.sendMail(to, subject, msg);
            to = null;
        }
        to = new int[1];
         /* Sending to creator */
        subject = MailMessage.generateSubject(requestId, 4, 4);
        msg = MailMessage.generateMessage(requestId, 4, 4);
        to[0] = WorkflowService.getRequestsEntityById(requestId).getUserFromId();
        if (to.length!=0) {
            mm.sendMail(to, subject, msg);
        }
    }

    public static void submitRequest(int requestId, int statusId){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update RequestsEntity set statusId = :status WHERE id="+requestId);
            query.setParameter("status", statusId);

            int s = query.executeUpdate();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    /**
     Adds hours for specified user from given from and to dates, with given workload type
     @param userId Id of the user
     @param workloadType Id of the workload, taken from WorkloadType enum
     @param from From which date the workloads should be added
     @param to To which date the workloads should be added
     @return Returns boolean value, specifying whether the method was executed successfully
     */
    public static boolean addHours (int userId, int workloadType, Date from, Date to, boolean full){
        if(full)
            return updateWorkloads(userId, workloadType, from, to, 8);
        else
            return updateWorkloads(userId, workloadType, from, to, 4);
    }

    /**
     Removes hours for specified user from given from and to dates, with given workload type
     @param userId Id of the user
     @param workloadType Id of the workload, taken from WorkloadType enum
     @param from From which date the workloads should be deleted
     @param to To which date the workloads should be deleted
     @return Returns boolean value, specifying whether the method was executed successfully
     */
    public static boolean removeHours (int userId, int workloadType, Date from, Date to){
        return updateWorkloads(userId, workloadType, from, to, 0);
    }


    public static boolean updateWorkloads(int userId, int workloadType, Date from, Date to, int duration){
        boolean doneStatus = false;
        try {
            List<Date> dates = getDaysBetweenDates(from, to);
            Calendar c = Calendar.getInstance();
            for (Date date : dates) {
                c.set(date.getYear(), date.getMonth(), date.getDate());
                if(c.get(Calendar.DAY_OF_WEEK) != 5) {
                    WorkloadEntity workload = new WorkloadEntity();
                    workload.setUserId(userId);
                    workload.setDate(date);
                    workload.setProjectId(0);
                    workload.setDuration(duration);
                    workload.setWorkloadType(workloadType);

                    doneStatus = WorkloadServices.saveOrUpdateWorklad(workload);
                }
            }
        }catch (Exception e){
            doneStatus = false;
        }
        return doneStatus;
    }

    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) throws ParseException {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);
        enddate = addDay(enddate, 1);

        while (calendar.getTime().before(enddate))
        {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static Date addDay(Date originalDate, int daysToAdd) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(originalDate);
        c.add(Calendar.DATE, daysToAdd);  // number of days to add

        return c.getTime();
    }

}
