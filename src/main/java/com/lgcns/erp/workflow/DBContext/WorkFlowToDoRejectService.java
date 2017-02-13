package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by DS on 11.02.2017.
 */
public class WorkFlowToDoRejectService {
    public static void reject(int reqId, int statusId, String comment){
        List<StepsEntity> list = WorkflowToDoApproveService.getTheStepsByReqId(reqId);
        int stepId = 0;

        for (StepsEntity stepsEntity : list) {
            if (stepsEntity.getActive()){
                stepId = stepsEntity.getId();
            }
        }
        WorkflowToDoApproveService.setStepComment(stepId, comment, statusId);
        WorkflowToDoApproveService.approve(reqId, statusId);
        WorkflowToDoApproveService.submitRequest(reqId, statusId);
    }
}
