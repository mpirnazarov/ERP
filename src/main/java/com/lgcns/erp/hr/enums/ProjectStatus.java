package com.lgcns.erp.hr.enums;

/**
 * Created by Rafatdin on 31.10.2016.
 */
public enum ProjectStatus {
    Active(1),
    Preparing(2),
    Completed(3);

    private final int value;

    private ProjectStatus(int value){
        this.value = value;
    }
    public int getValue(){return value;}
}
