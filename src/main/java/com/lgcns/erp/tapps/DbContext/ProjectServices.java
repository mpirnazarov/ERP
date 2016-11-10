package com.lgcns.erp.tapps.DbContext;


import com.lgcns.erp.tapps.entities.ProjectEntity;
import com.lgcns.erp.tapps.model.DbEntities.ProjectsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity;
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
public class ProjectServices {
    public static List<ProjectEntity> getAllProjects(){
        List<ProjectEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ProjectsEntity");
            list = (List<ProjectEntity>)query.list();
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

    public static List<UserInProjectsEntity> getUserInProjectsInfoByUserId(int userId){
        List<UserInProjectsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInProjectsEntity uip where uip.userId= :userId");
            query.setParameter("userId", userId);
            list = (List<UserInProjectsEntity>) query.list();
            transaction.commit();

        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }
}
