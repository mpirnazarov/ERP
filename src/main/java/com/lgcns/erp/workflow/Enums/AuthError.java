package com.lgcns.erp.workflow.Enums;

/**
 * Created by Muslimbek on 20.01.2017.
 */
public enum AuthError {
    Date_Expired(1), Invalid_Token(2);

    int value;
    AuthError(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}