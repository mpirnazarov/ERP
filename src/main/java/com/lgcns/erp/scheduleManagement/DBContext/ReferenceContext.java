package com.lgcns.erp.scheduleManagement.DBContext;

import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInCheduleEntity;
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
public class ReferenceContext {


    public static void insertReference(ReferenceInCheduleEntity referenceInScheduleEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            referenceInScheduleEntity.setScheduleByScheduleId(session.load(ScheduleEntity.class, referenceInScheduleEntity.getScheduleId()));
            //Save the object in database
            session.save(referenceInScheduleEntity);
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
     * Retrieves references based on event id
     * @param id
     * @return
     */
    public static List<ReferenceInCheduleEntity> getReferencesByScheduleId(int id){
        List<ReferenceInCheduleEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ReferenceInCheduleEntity where scheduleId=:id");
            query.setParameter("id", id);
            list = (List<ReferenceInCheduleEntity>)query.list();
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
}
