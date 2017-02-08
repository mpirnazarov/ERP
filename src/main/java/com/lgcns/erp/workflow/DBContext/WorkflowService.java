package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.workflow.DBEntities.*;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.workflow.DBEntities.*;
import com.lgcns.erp.workflow.ViewModel.ToDoViewModel;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Muslimbek on 1/23/2017.
 */
public class WorkflowService {

    public static List<RequestsEntity> getRequestList(){
        List<RequestsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from RequestsEntity");
            list = (List<RequestsEntity>)query.list();
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

    public static List<RequestsEntity> filter(String whereClause){
        String q = "select r from RequestsEntity r ";

        List<RequestsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(q+whereClause+" order by r.dateCreated, r.statusId");
            list = (List<RequestsEntity>)query.list();
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

    public static List<StepsEntity> getStepsList(){
        List<StepsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from StepsEntity");
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

    public static RequestsEntity getRequestsEntityById(int id){
        RequestsEntity entity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from RequestsEntity where id="+id);
            entity = (RequestsEntity)query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return entity;
    }

    public static List<AttachmentsEntity> getAttachmentList(){
        List<AttachmentsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AttachmentsEntity ");
            list = (List<AttachmentsEntity>)query.list();
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


    public static String getAttachmentPathNameById(Long id){
        AttachmentsEntity entity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AttachmentsEntity WHERE id="+id);
            entity = (AttachmentsEntity)query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return entity.getUrl()+entity.getFilename();
    }

    public static List<TripTypesEntity> getTripTypes() {
        List<TripTypesEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from TripTypesEntity ");
            list = (List<TripTypesEntity>)query.list();
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

    public static List<StepCommentsEntity> getStepComments(){
        List<StepCommentsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from StepCommentsEntity ");
            list = (List<StepCommentsEntity>)query.list();
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

    public static List<StepCommentsEntity> getStepCommentsByStepId(long id){
        List<StepCommentsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from StepCommentsEntity WHERE stepsId="+id);
            list = (List<StepCommentsEntity>)query.list();
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

    public static void stepAction(long reqId){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update StepCommentsEntity s set s.comments = 'sadadsadad' where s.id = 1");
            /*query.setParameter("statusId", 2);
            query.setParameter("id", 1);*/
            int s = query.executeUpdate();
            System.out.println("S= " + s);
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

    public static int insertRequests(RequestsEntity requestsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            requestsEntity.setTripTypesByTripTypeId(session.load(TripTypesEntity.class, requestsEntity.getTripTypeId()));
            //Save the object in database
            session.save(requestsEntity);
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
        return (int)requestsEntity.getId();
    }

    public static void insertMembers(MembersEntity membersEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            membersEntity.setRequestsByRequestId(session.load(RequestsEntity.class, membersEntity.getRequestId()));
            //Save the object in database
            session.save(membersEntity);
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

    public static void insertToDo(ToDoEntity toDoEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            toDoEntity.setRequestsByRequestId(session.load(RequestsEntity.class, toDoEntity.getRequestId()));
            //Save the object in database
            session.save(toDoEntity);
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

    public static void insertAttachments(AttachmentsEntity attachmentsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            attachmentsEntity.setRequestsByRequestId(session.load(RequestsEntity.class, attachmentsEntity.getRequestId()));
            //Save the object in database
            session.save(attachmentsEntity);
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

    public static void insertTest() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update StepsEntity set stepSequence = :statusId where id = :id");
            query.setParameter("statusId", 2);
            query.setParameter("id", 1);
            int s = query.executeUpdate();
            System.out.println("S= " + s);
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
