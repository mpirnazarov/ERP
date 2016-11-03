package com.lgcns.erp.tapps.Enums;

/**
 * Created by Rafatdin on 11.10.2016.
 */
public enum Language_Ranking {
    A1(1), A2(2), B1(3), B2(4), C1(5), C2(6);

    int code;
    Language_Ranking(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }
}