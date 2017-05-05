package com.lgcns.erp.scheduleManagement.DBContext;

import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
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
public class ParticipantContext {

    /**
     * Retrieves the participants by schedule(event) id
     *
     * @param id
     * @return List of Participants included in the evernt
     */
    public static List<ParticipantInScheduleEntity> getParticipantsByScheduleId(int id) {
        List<ParticipantInScheduleEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ParticipantInScheduleEntity where scheduleId=:id");
            query.setParameter("id", id);
            list = (List<ParticipantInScheduleEntity>) query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    public static void insertParticipants(ParticipantInScheduleEntity participantInScheduleEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            participantInScheduleEntity.setScheduleByScheduleId(session.load(ScheduleEntity.class, participantInScheduleEntity.getScheduleId()));

            //Save the object in database
            session.save(participantInScheduleEntity);

            //Commit the transaction
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * updates decision of an participant in a certain event and adds reason
     *
     * @param participantId
     * @param scheduleId
     * @param status
     * @param reason
     */
    public static void updateParticipantDecision(int participantId, int scheduleId, int status, String reason) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update ParticipantInScheduleEntity set status=:status, reason=:reason " +
                    "where scheduleId=:scheduleId and userId=:userId");
            query.setParameter("userId", participantId);
            query.setParameter("scheduleId", scheduleId);
            query.setParameter("status", status);
            query.setParameter("reason", reason);

            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Deletes participant
     *
     * @param scheduleId
     */
    public static void deleteParticipant(int scheduleId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from ParticipantInScheduleEntity where scheduleId = :scheduleId");
            query.setParameter("scheduleId", scheduleId);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
