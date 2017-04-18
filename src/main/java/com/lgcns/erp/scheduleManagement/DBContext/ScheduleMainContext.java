package com.lgcns.erp.scheduleManagement.DBContext;

import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInCheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by DS on 06.04.2017.
 */
public class ScheduleMainContext {
    /**
     * Retrieves all schedule entities
     * @return
     */
    public static List<ScheduleEntity> getScheduleList(Timestamp start, Timestamp end){
        List<ScheduleEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ScheduleEntity where dateFrom>=:from and dateTo<=:end");
            query.setParameter("from", start);
            query.setParameter("end", end);
            list = (List<ScheduleEntity>)query.list();
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
     * Inserts an event into Schedule table
     * @param scheduleEntity
     * @return event Id
     */
    public static int insertSchedule(ScheduleEntity scheduleEntity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Save the object in database
            int id = (int)session.save(scheduleEntity);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return scheduleEntity.getScheduleId();
    }

    /**
     * Modifies the schedule
     * @param scheduleEntity
     */
    public static void updateSchedule(ScheduleEntity scheduleEntity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //todo not sure
            session.update(scheduleEntity);
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
     * Deletes an event from schedule table
     * @param scheduleId
     */
    public static void deleteSchedule(int scheduleId){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from ScheduleEntity where scheduleId =:scheduleId");
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

}
