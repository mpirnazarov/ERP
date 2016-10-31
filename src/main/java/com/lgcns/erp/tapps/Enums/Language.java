package com.lgcns.erp.tapps.Enums;

/**
 * Created by Rafatdin on 11.10.2016.
 */
public enum Language {
    rus(1), uzb(2), eng(3);

    int code;
    Language(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }
}