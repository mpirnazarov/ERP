package com.lgcns.erp.tapps.DbContext;


import com.lgcns.erp.hr.enums.ProjectRole;
import com.lgcns.erp.tapps.model.DbEntities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.ValueHandlerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rafatdin on 31.10.2016.
 */
public class ProjectServices {
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

    public static int insertProject(ProjectsEntity project){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(project);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return project.getId();
    }

    public static void insertProjectMember(int projectId, int userId, ProjectRole role){
        UserInProjectsEntity uipInfo = new UserInProjectsEntity();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            uipInfo.setProjectsByProjectId(session.load(ProjectsEntity.class, projectId));
            uipInfo.setUsersByUserId(session.load(UsersEntity.class, userId));
            uipInfo.setRoleId(role.getValue());
            session.save(uipInfo);
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

    public static String getNextCode() {
        String returning = "";
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ProjectsEntity pe order by pe.code asc");
            List<ProjectsEntity> list = (List<ProjectsEntity>) query.list();
            String curCode = list.get(list.size()-1).getCode();
            returning = String.format("%0" + curCode.length() + "d", Integer.parseInt(curCode)+1);


            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return returning;
    }

}
