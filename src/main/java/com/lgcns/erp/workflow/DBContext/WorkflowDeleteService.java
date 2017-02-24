package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Created by DS trip_types_id_seqon 21.02.2017.
 */
public class WorkflowDeleteService {
    public static void DeleteRequest(int req_id, int statusId){
        deleteSteps(req_id, statusId);
        WorkflowToDoApproveService.submitRequest(req_id, statusId);
    }

    private static void deleteSteps(int req_id, int statusId){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update StepsEntity set statusId = :status, active = false where requestId= :reqId");
            query.setParameter("reqId", req_id);
            query.setParameter("status", statusId);
            query.executeUpdate();
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
