package com.lgcns.erp.workflow.util;

import com.lgcns.erp.workflow.DBContext.WorkflowService;

import java.io.IOException;

/**
 * Created by DS on 07.02.2017.
 */
public class DetailsAction {
    public static void doAction(String comment, int status, int reqId) throws IOException {
        if (status==5)
            approve(comment, status, reqId);
        else if (status==3)
            reject(comment, status, reqId);
        else if (status==2)
            review(comment, status, reqId);
    }

    private static void approve(String comment, int status, int reqId) throws IOException {
        WorkflowService.stepApprove(reqId, status, comment);
    }

    private static void reject(String comment, int status, int reqId) throws IOException {
        WorkflowService.stepReject(reqId,status,comment);
    }

    private static void review(String comment, int status, int reqId){
        WorkflowService.stepReview(reqId,status,comment);
    }
}
