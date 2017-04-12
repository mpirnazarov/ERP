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
    public static List<ParticipantInScheduleEntity> getParticipantsByScheduleId(int id){
        List<ParticipantInScheduleEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ParticipantInScheduleEntity where scheduleId=:id");
            query.setParameter("id", id);
            list = (List<ParticipantInScheduleEntity>)query.list();
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

    public static void insertParticipants(ParticipantInScheduleEntity participantInScheduleEntity){
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
