package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepCommentsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.Mapper.CommentMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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
            approve(requestId, statusId);
            submitRequest(requestId, statusId);
            return -1;
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


    public static void setNewStep(int newStepId){
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

}
