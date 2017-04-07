package com.lgcns.erp.workflow.Enums;

/**
 * Created by DS on 07.04.2017.
 */
public enum  LeavingHours {
    Eight_Hour(1), Four_AM(2), Four_PM(3);

    int value;
    LeavingHours(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
