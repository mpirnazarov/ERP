package com.lgcns.erp.attendanceManagement.DBContext;

import com.lgcns.erp.attendanceManagement.DBEntities.AttendanceManagementEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

/**
 * Created by DS on 12.04.2017.
 */
public class AttendanceMainContext {


    /**
     * Manually inserts attendance
     * @param attendanceManagementEntity
     */
    public static void insertAttendance(AttendanceManagementEntity attendanceManagementEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items

            //Save the object in database
            session.save(attendanceManagementEntity);

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

    public static List<AttendanceManagementEntity> getAttendanceManagement() throws IOException{
        List<AttendanceManagementEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AttendanceManagementEntity ");
            list = (List<AttendanceManagementEntity>) query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }



}
