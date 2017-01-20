package com.lgcns.erp.workflow.Enums;

/**
 * Created by Muslimbek on 20.01.2017.
 */
public enum Status {
    In_progress(1), Rejected(2), Review(3), Draft(4);

    int value;
    Status(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}