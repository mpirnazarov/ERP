package com.lgcns.erp.tapps.DbContext;

import com.lgcns.erp.hr.enums.WorkloadType;
import com.lgcns.erp.tapps.model.DbEntities.ProjectsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
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

    public static boolean saveOrUpdateWorklad(WorkloadEntity workload) {
        boolean done = false;
        WorkloadEntity tempWorkload;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            workload.setUsersByUserId(session.load(UsersEntity.class, workload.getUserId()));
            workload.setProjectsByProjectId(session.load(ProjectsEntity.class, workload.getProjectId()));

            Query query = session.createQuery("from WorkloadEntity workloads where userId = :userId and date= :date and projectId = :projectId and workloadType = :workloadType");
            query.setParameter("userId", workload.getUserId());
            query.setParameter("date", workload.getDate());
            query.setParameter("projectId", workload.getProjectId());
            query.setParameter("workloadType", workload.getWorkloadType());
            if (!query.getResultList().isEmpty()) {
                tempWorkload = (WorkloadEntity) query.getSingleResult();
                if(workload.getDuration()==0)
                    session.delete(tempWorkload);
                else {
                    tempWorkload.setDuration(workload.getDuration());
                    session.update(tempWorkload);
                }
                done = true;
            }
            else if(workload.getDuration()!=0){
                session.save(workload);
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
            done = false;
        } finally {
            session.close();
        }
        return done;
    }

    public static boolean isAccessibleOnDate(Date date, int projectId, int userId) {
        boolean accessible = false;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInProjectsEntity where userId = :userId and projectId = :projectId and dateTo >= :date and dateFrom <= :date");
            query.setParameter("userId", userId);
            query.setParameter("date", date);
            query.setParameter("projectId", projectId);
            accessible = !query.getResultList().isEmpty();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return accessible;
    }

    public static List<WorkloadEntity> getWorkloadByPeriod(int userId, Date dateFrom, Date dateTo){
        List<WorkloadEntity> returning = new ArrayList<WorkloadEntity>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query;
            if(userId != 0){
                query = session.createQuery("from WorkloadEntity w where w.date >= :dateFrom and w.date <= :dateTo and w.userId = :userId");
                query.setParameter("userId", userId);
            }else{
                query = session.createQuery("from WorkloadEntity w where w.date >= :dateFrom and w.date <= :dateTo");
            }
            query.setParameter("dateFrom", dateFrom);
            query.setParameter("dateTo", dateTo);
            returning = (List<WorkloadEntity>)query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return returning;
    }

    public static List<WorkloadEntity> getWorkloadByPeriod(int userId, int projectId, int typeId, Date dateFrom, Date dateTo){
        List<WorkloadEntity> returning = new ArrayList<WorkloadEntity>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query;
            if(userId==0) {
                if (projectId == 0) {
                    if (typeId == 0) {
                        query = session.createQuery("from WorkloadEntity w where w.date >= :dateFrom and w.date <= :dateTo");
                        query.setParameter("dateFrom", dateFrom);
                        query.setParameter("dateTo", dateTo);
                        returning = (List<WorkloadEntity>)query.list();
                    } else {
                        query = session.createQuery("from WorkloadEntity w where w.date >= :dateFrom and w.date <= :dateTo and w.workloadType = :wType");
                        query.setParameter("wType", typeId);
                        query.setParameter("dateFrom", dateFrom);
                        query.setParameter("dateTo", dateTo);
                        returning = (List<WorkloadEntity>)query.list();
                    }
                } else {
                    if (typeId == 0) {
                        query = session.createQuery("from WorkloadEntity w where w.date >= :dateFrom and w.date <= :dateTo and w.projectId = :projectId");
                        query.setParameter("projectId", projectId);
                        query.setParameter("dateFrom", dateFrom);
                        query.setParameter("dateTo", dateTo);
                        returning = (List<WorkloadEntity>)query.list();
                    } else {
                        query = session.createQuery("from WorkloadEntity w where w.date >= :dateFrom and w.date <= :dateTo and w.projectId = :projectId and w.workloadType = :wType");
                        query.setParameter("wType", typeId);
                        query.setParameter("projectId", projectId);
                        query.setParameter("dateFrom", dateFrom);
                        query.setParameter("dateTo", dateTo);
                        returning = (List<WorkloadEntity>)query.list();
                    }
                }
            }else{
                if (projectId == 0) {
                    if (typeId == 0) {
                        query = session.createQuery("from WorkloadEntity w where w.date >= :dateFrom and w.userId = :userId and w.date <= :dateTo");
                        query.setParameter("userId", userId);
                        query.setParameter("dateFrom", dateFrom);
                        query.setParameter("dateTo", dateTo);
                        returning = (List<WorkloadEntity>)query.list();
                    } else {
                        query = session.createQuery("from WorkloadEntity w where w.date >= :dateFrom and w.userId = :userId and w.date <= :dateTo and w.workloadType = :wType");
                        query.setParameter("wType", typeId);
                        query.setParameter("userId", userId);
                        query.setParameter("dateFrom", dateFrom);
                        query.setParameter("dateTo", dateTo);
                        returning = (List<WorkloadEntity>)query.list();
                    }
                } else {
                    if (typeId == 0) {
                        query = session.createQuery("from WorkloadEntity w where w.date >= :dateFrom and w.userId = :userId and w.date <= :dateTo and w.projectId = :projectId");
                        query.setParameter("projectId", projectId);
                        query.setParameter("userId", userId);
                        query.setParameter("dateFrom", dateFrom);
                        query.setParameter("dateTo", dateTo);
                        returning = (List<WorkloadEntity>)query.list();
                    } else {
                        query = session.createQuery("from WorkloadEntity w where w.date >= :dateFrom and w.userId = :userId and w.date <= :dateTo and w.projectId = :projectId and w.workloadType = :wType");
                        query.setParameter("wType", typeId);
                        query.setParameter("projectId", projectId);
                        query.setParameter("userId", userId);
                        query.setParameter("dateFrom", dateFrom);
                        query.setParameter("dateTo", dateTo);
                        returning = (List<WorkloadEntity>)query.list();
                    }
                }
            }

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return returning;
    }

}
