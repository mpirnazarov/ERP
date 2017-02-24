package com.lgcns.erp.workflow.Enums;

/**
 * Created by Muslimbek on 20.01.2017.
 */
public enum Status {
    In_progress(1), Revision(2), Rejected(3), Draft(4), Approved(5), Terminated(6), Finished(7), Deleted(8);

    int value;
    Status(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}