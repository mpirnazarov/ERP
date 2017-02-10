package com.lgcns.erp.workflow.Enums;

/**
 * Created by Muslimbek on 20.01.2017.
 */
public enum LeaveType {
    Sick_leave(1), Annual_leave(2), Maternity_leave(3), Unpaid_leave(4);

    int value;
    LeaveType(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}