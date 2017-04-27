package com.lgcns.erp.scheduleManagement.enums;

/**
 * Created by DS on 26.04.2017.
 */
public enum  ScheduleEventInvolvement {
    Participant(1), Referenced(2), Author(3);

    int value;
    ScheduleEventInvolvement(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
