package com.lgcns.erp.scheduleManagement.serviceImpl;

import com.lgcns.erp.scheduleManagement.DBContext.ScheduleMainContext;
import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.scheduleManagement.service.ScheduleMainService;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by DS on 05.04.2017.
 */
public class ScheduleTestImpl implements ScheduleMainService {

    @Override
    public List<ScheduleEntity> getScheduleList() {
        ScheduleMainContext.getScheduleList();
        return null;
    }

    @Override
    public int insertSchedule(ScheduleEntity scheduleEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Save the object in database
            int id = (int)session.save(scheduleEntity);
            System.out.println(id);
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
        return scheduleEntity.getScheduleId();
    }

    @Override
    public void insertParticipants(ParticipantInScheduleEntity participantInScheduleEntity) {
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

    @Override
    public void insertReference(ReferenceInScheduleEntity referenceInScheduleEntity) {
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

    @Override
    public void insertAttachment(ScheduleAttachmentsEntity scheduleAttachmentsEntity) {
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
}
