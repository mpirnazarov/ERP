package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.hr.enums.WorkloadType;
import com.lgcns.erp.scheduleManagement.DBContext.ParticipantContext;
import com.lgcns.erp.scheduleManagement.DBContext.ReferenceContext;
import com.lgcns.erp.scheduleManagement.DBContext.ScheduleMainContext;
import com.lgcns.erp.scheduleManagement.mapper.ReferenceMapper;
import com.lgcns.erp.scheduleManagement.mapper.ScheduleMainMapper;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.tapps.DbContext.WorkloadServices;
import com.lgcns.erp.tapps.model.DbEntities.WorkloadEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepCommentsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.Enums.LeaveType;
import com.lgcns.erp.workflow.Enums.LeavingHours;
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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DS on 08.02.2017.
 */
public class WorkflowToDoApproveService {
    public static int getTheNextSequence(int requestId, int statusId, String comment) throws IOException {
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
            addToSchedule(requestId);
            sendEmailAfterLastApproves(requestId);
            return -1;
        }
    }

    private static void addToSchedule(int requestId) throws IOException{

        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(requestId);
        if(requestsEntity.getTypeId().intValue() == Type.Leave.getValue()){
            int scheduleId = ScheduleMainContext.insertSchedule(ScheduleMainMapper.mapScheduleFromWorkflowToEntity(requestId));
            /* Set references */
            for (StepsEntity step :
                    WorkflowService.getStepsByRequestIdFilterInvolvement(requestId, 3)) {
                ReferenceContext.insertReference(ReferenceMapper.mapReferenceListToScheduleFromWorkflow(step, scheduleId));
            }

        }
    }

    private static void sendEmailAfterLastApproves(int requestId) throws IOException {

        // E-mail is sent here
        /*ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");*/
        String subject = "";
        String msg = "";
        int[] to;

        /* Sending to references and executors */
        subject = MailMessage.generateSubject(requestId, 5, 2);

        to = WorkflowEmailService.getInvolvementList(requestId, 2);
        if (to.length!=0) {
            for (int userId :
                    to) {
                msg = MailMessage.generateMessage(requestId, 5, 2, userId);
                EmailService.sendHtmlMail(userId, subject, msg);
            }
        }
        to = null;

        to = WorkflowEmailService.getInvolvementList(requestId, 3);
        if (to.length!=0) {
            for (int userId :
                    to) {
                msg = MailMessage.generateMessage(requestId, 5, 3, userId);
                EmailService.sendHtmlMail(userId, subject, msg);
            }
        }
        to = null;


        /* Sending to creator */
        subject = MailMessage.generateSubject(requestId, 5, 4);

        int toCreator = WorkflowService.getRequestsEntityById(requestId).getUserFromId();
        msg = MailMessage.generateMessage(requestId, 5, 4, toCreator);
        EmailService.sendHtmlMail(toCreator, subject, msg);

    }

    private static void addHours(RequestsEntity entity){
        if (entity.getDateFrom()!=null && entity.getDateTo()!=null) {
            if (entity.getLeaveTypeId() == LeaveType.Annual_leave.getValue()) {
                if (entity.getLeavingHours()== LeavingHours.Eight_Hour.getValue())
                    addHours(entity.getUserFromId(), WorkloadType.Annual_leave.getValue(), entity.getDateFrom(), entity.getDateTo(), true);
                else
                    addHours(entity.getUserFromId(), WorkloadType.Annual_leave.getValue(), entity.getDateFrom(), entity.getDateTo(), false);
            }
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


    public static void setNewStep(int newStepId, int reqId) throws IOException {
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

    private static void sendEmailToTheNextApprover(int requestId, int nextApproverId) throws IOException {
        // E-mail is sent here
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        String subject = "";
        String msg = "";

         /* Sending to next Approval*/
        int to = WorkflowService.getUserIdFromSteps(nextApproverId);
        subject = MailMessage.generateSubject(requestId, 4, 1);
        msg = MailMessage.generateMessage(requestId, 4, 1, to);

        EmailService.sendHtmlMail(to, subject, msg);


         /* Sending to creator */
        to = WorkflowService.getRequestsEntityById(requestId).getUserFromId();
        subject = MailMessage.generateSubject(requestId, 4, 4);
        msg = MailMessage.generateMessage(requestId, 4, 4, to);

        EmailService.sendHtmlMail(to, subject, msg);

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
