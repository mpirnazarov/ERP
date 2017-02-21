package com.lgcns.erp.workflow.Enums;

/**
 * Created by Muslimbek on 20.01.2017.
 */
public enum Type {
    Business_trip(1), Leave(2), Unformatted(3), Termination(4);

    int value;
    Type(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}