package com.lgcns.erp.tapps.DbContext;


import com.lgcns.erp.tapps.entities.ProjectEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
}
