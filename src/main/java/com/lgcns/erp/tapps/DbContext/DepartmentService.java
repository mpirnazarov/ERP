package com.lgcns.erp.tapps.DbContext;

import com.lgcns.erp.tapps.model.DbEntities.DepartmentLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by DS on 15.02.2017.
 */
public class DepartmentService {

    public static DepartmentLocalizationsEntity getDepartmentLocsByDeptId(int id, int langId){
        DepartmentLocalizationsEntity entity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from DepartmentLocalizationsEntity where departmentId = :deptId and languageId = :langId");
            query.setParameter("deptId", id);
            query.setParameter("langId", langId);
            entity = (DepartmentLocalizationsEntity)query.getSingleResult();
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
