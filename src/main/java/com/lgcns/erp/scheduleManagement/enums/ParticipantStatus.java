package com.lgcns.erp.scheduleManagement.enums;

/**
 * Created by DS on 06.04.2017.
 */
public enum ParticipantStatus {
    Participate(1), Not_participate(2), Not_decided(3);

    int value;
    ParticipantStatus(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
