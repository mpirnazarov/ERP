package com.lgcns.erp.hr.enums;

/**
 * Created by Rafatdin on 31.10.2016.
 */
public enum WorkloadType {
    Work_Project(1),
    Annual_leave(2),
    Sick_leave(3),
    Unpaid_leave(4),
    Training(5),
    Work_Administrative(6);

    private final int value;

    private WorkloadType(int value){
        this.value = value;
    }
    public int getValue(){return value;}
}
