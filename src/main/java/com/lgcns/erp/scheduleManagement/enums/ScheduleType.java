package com.lgcns.erp.scheduleManagement.enums;

/**
 * Created by DS on 06.04.2017.
 */
public enum ScheduleType {
    Meeting(1), Out_of_office(2), Personal(3), Other(4);

    int value;
    ScheduleType(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
