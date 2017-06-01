package com.lgcns.erp.scheduleManagement.enums;

/**
 * Created by DS on 13.04.2017.
 */
public enum ActionTypeId {
    Create(1), Update(2), ParticipantDecide(3), Delete(4), Send_Email(5);

    int value;
    ActionTypeId(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
