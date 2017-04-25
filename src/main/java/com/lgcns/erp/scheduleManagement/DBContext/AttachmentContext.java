package com.lgcns.erp.scheduleManagement.DBContext;

import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by DS on 12.04.2017.
 */
public class AttachmentContext {

    public static void insertAttachment(ScheduleAttachmentsEntity scheduleAttachmentsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            scheduleAttachmentsEntity.setScheduleByScheduleId(session.load(ScheduleEntity.class, scheduleAttachmentsEntity.getScheduleId()));

            //Save the object in database
            session.save(scheduleAttachmentsEntity);

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

    /**
     * Retrieves attachments of an event
     * @param id
     * @return List of AttachmentEntity
     */
    public static List<ScheduleAttachmentsEntity> getAttachmentsByScheduleId(int id){
        List<ScheduleAttachmentsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ScheduleAttachmentsEntity where scheduleId=:id");
            query.setParameter("id", id);
            list = (List<ScheduleAttachmentsEntity>)query.list();
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

    /**
     * Todo
     * @param scheduleId
     */
    public static void deleteAttachment(int scheduleId){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from ScheduleAttachmentsEntity where scheduleId=:scheduleId");
            query.setParameter("scheduleId", scheduleId);
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

    public static void deleteAttachmentById(int attachmentId){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from ScheduleAttachmentsEntity where attachmentId=:attachmentId");
            query.setParameter("attachmentId", attachmentId);
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

    public static ScheduleAttachmentsEntity getAttachmentById(int id){
        ScheduleAttachmentsEntity attachment = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ScheduleAttachmentsEntity where attachmentId=:id");
            query.setParameter("id", id);
            attachment = (ScheduleAttachmentsEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return attachment;
    }

}
