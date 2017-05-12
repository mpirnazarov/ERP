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
    public static List<ScheduleEntity> getScheduleList(Timestamp start, Timestamp end, int userId){
        List<ScheduleEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ScheduleEntity s where s.dateFrom>=:from and s.dateTo<=:end and s.autherId=:userId or s.scheduleId in (select p.scheduleId from ParticipantInScheduleEntity p where p.userId=:userId and s.draft=false) or s.scheduleId in (select r.scheduleId from ReferenceInCheduleEntity r where r.userId=:userId and s.draft=false)");
            query.setParameter("from", start);
            query.setParameter("end", end);
            query.setParameter("userId", userId);
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

    public static List<ScheduleEntity> getScheduleWhereUserIsAuthor(Timestamp start, Timestamp end, int authorId) {
        List<ScheduleEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ScheduleEntity s where s.dateFrom>=:from and s.dateTo<=:end and s.autherId=:authorId");
            query.setParameter("from", start);
            query.setParameter("end", end);
            query.setParameter("authorId", authorId);
            list = (List<ScheduleEntity>) query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<ScheduleEntity> getScheduleWhereUserIsParticipant(Timestamp start, Timestamp end, int userId) {
        List<ScheduleEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ScheduleEntity s where s.dateFrom>=:from and s.dateTo<=:end and s.scheduleId in (select p.scheduleId from ParticipantInScheduleEntity p where p.userId=:userId)");
            query.setParameter("from", start);
            query.setParameter("end", end);
            query.setParameter("userId", userId);
            list = (List<ScheduleEntity>) query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<ScheduleEntity> getScheduleWhereUserIsReference(Timestamp start, Timestamp end, int userId) {
        List<ScheduleEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ScheduleEntity s where s.dateFrom>=:from and s.dateTo<=:end and s.scheduleId in (select r.scheduleId from ReferenceInCheduleEntity r where r.userId=:userId)");
            query.setParameter("from", start);
            query.setParameter("end", end);
            query.setParameter("userId", userId);
            list = (List<ScheduleEntity>) query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
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

            Query query = session.createQuery("update ScheduleEntity set title=:title, description=:description, place=:place, stype=:stype" +
                    ", other=:other, dateFrom=:dateFrom, dateTo=:dateTo, compulsory=:isCompulsory, toNotify=:toNotify, draft=:isDraft" +
                    " where scheduleId =:scheduleId");
            query.setParameter("scheduleId", scheduleEntity.getScheduleId());
            query.setParameter("title", scheduleEntity.getTitle());
            query.setParameter("description", scheduleEntity.getDescription());
            query.setParameter("place", scheduleEntity.getPlace());
            query.setParameter("stype", scheduleEntity.getStype());
            query.setParameter("other", scheduleEntity.getOther());
            query.setParameter("dateFrom", scheduleEntity.getDateFrom());
            query.setParameter("dateTo", scheduleEntity.getDateTo());
            query.setParameter("isCompulsory", scheduleEntity.getCompulsory());
            query.setParameter("toNotify", scheduleEntity.getToNotify());
            query.setParameter("isDraft", scheduleEntity.getDraft());
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
