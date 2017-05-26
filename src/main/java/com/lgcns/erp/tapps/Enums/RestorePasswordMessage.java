package com.lgcns.erp.tapps.Enums;

/**
 * Created by Muslimbek on 20.01.2017.
 */
public enum RestorePasswordMessage {
    Email_Not_Exist(1), User_Disabled(2), Email_Not_Sent(3), Okey(200);

    int value;
    RestorePasswordMessage(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}