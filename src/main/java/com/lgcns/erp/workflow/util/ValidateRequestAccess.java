package com.lgcns.erp.workflow.util;

import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;

import java.util.List;

/**
 * Created by DS on 11.03.2017.
 */
public class ValidateRequestAccess {
    public static boolean ValidRequestAccess(int requestId, int userId){
        boolean isValid = false;
        RequestsEntity entity = WorkflowService.getRequestsEntityById(requestId);
        if (entity.getUserFromId()==userId)
            isValid = true;
        else
            isValid = false;
        return isValid;
    }

    public static boolean ValidateTodoAccess(int requestId, int userId){
        boolean isValid = false;
        List<StepsEntity> stepsEntities = WorkflowService.getStepsByRequestId(requestId);

        for (StepsEntity stepsEntity : stepsEntities) {
            if (stepsEntity.getUserId()==userId)
                isValid = true;
        }

        return isValid;
    }
}
