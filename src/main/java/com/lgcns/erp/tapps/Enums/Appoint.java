package com.lgcns.erp.tapps.Enums;

/**
 * Created by Rafatdin on 11.10.2016.
 */
public enum Appoint {
    FULL_TIME(1), PART_TIME(2), CONTRACT(3);

    int value;
    Appoint(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}