package com.lgcns.erp.tapps.DbContext;

import com.lgcns.erp.hr.enums.WorkloadType;
import com.lgcns.erp.tapps.model.DbEntities.ProjectsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity;
import com.lgcns.erp.tapps.model.DbEntities.WorkloadEntity;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rafatdin on 31.10.2016.
 */
public class WorkloadServices {
    public static List<ProjectsEntity> getUsersAllProjects(int userId, Date from, Date to) {
        List<UserInProjectsEntity> list = null;
        List<ProjectsEntity> projectsList = new ArrayList<ProjectsEntity>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInProjectsEntity uip where uip.userId= :userId and uip.dateFrom<= :fromDate and uip.dateTo>=:toDate");
            query.setParameter("userId", userId);
            query.setParameter("fromDate", from);
            query.setParameter("toDate", to);
            list = (List<UserInProjectsEntity>) query.list();
            transaction.commit();

            for (UserInProjectsEntity uip : list) {
                projectsList.add(uip.getProjectsByProjectId());
            }
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return projectsList;
    }

    public static List<WorkloadEntity> getWorkloadsByUserid(int userId) {
        List<WorkloadEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from WorkloadEntity workloads where userId = :userId");
            query.setParameter("userId", userId);
            list = (List<WorkloadEntity>) query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }


    public static List<WorkloadEntity> getWorkloadsByUserid(int userId, Date from, Date to) {
        List<WorkloadEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from WorkloadEntity workloads where userId = :userId and date>= :fromDate and date<= :toDate");
            query.setParameter("userId", userId);
            query.setParameter("fromDate", from);
            query.setParameter("toDate", to);
            list = (List<WorkloadEntity>) query.list();
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
