package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by DS on 11.02.2017.
 */
public class WorkFlowToDoReviewService {
    public static void review(int reqId, int statusId, String comment){

        List<StepsEntity> list = WorkflowToDoApproveService.getTheStepsByReqId(reqId);
        int stepId = 0;

        for (StepsEntity stepsEntity : list) {
            if (stepsEntity.getActive()){
                stepId = stepsEntity.getId();
            }
        }
        WorkflowToDoApproveService.setStepComment(stepId, comment, statusId);
        setReviewStatus(reqId, statusId);
        WorkflowToDoApproveService.submitRequest(reqId, statusId);
    }

    private static void setReviewStatus(int reqId, int statusId){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update StepsEntity set statusId = :status where requestId= :reqId and active = true");
            query.setParameter("reqId", reqId);
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
