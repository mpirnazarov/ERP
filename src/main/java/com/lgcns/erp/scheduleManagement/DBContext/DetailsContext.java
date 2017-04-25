package com.lgcns.erp.scheduleManagement.DBContext;

import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Map;

/**
 * Created by DS on 06.04.2017.
 */
public class DetailsContext {
    /**
     * Gets Schedule entity by entity id
     * @param id
     * @return ScheduleEntity
     */

    public static ScheduleEntity getScheduleById(int id){
        ScheduleEntity entity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ScheduleEntity where id="+id);
            entity = (ScheduleEntity)query.getSingleResult();
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


}
