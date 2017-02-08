package com.lgcns.erp.workflow.util;

import com.lgcns.erp.workflow.DBContext.WorkflowService;

/**
 * Created by DS on 07.02.2017.
 */
public class DetailsAction {
    public static void doAction(String comment, int status, int reqId){
        if (status==5)
            approve(comment, status, reqId);
        else if (status==3)
            review(comment, status, reqId);
        else if (status==2)
            reject(comment, status, reqId);
    }

    private static void approve(String comment, int status, int reqId){
        WorkflowService.stepAction(reqId);
    }

    private static void reject(String comment, int status, int reqId){

    }

    private static void review(String comment, int status, int reqId){

    }
}
