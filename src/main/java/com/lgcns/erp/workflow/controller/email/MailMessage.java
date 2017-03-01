package com.lgcns.erp.workflow.controller.email;

import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;

/**
 * Created by Muslimbek Pirnazarov on 3/1/2017.
 */
public class MailMessage {

    public static String generateSubject(int requestId, int actionStep) {
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(requestId);


        return "blabla";
    }

    public static String generateMessage(int requestId, int actionStep) {
        String msg = "";
        if(actionStep == 1)
            msg = "blablabla";
        return msg;
    }
}
