package com.lgcns.erp.hr.enums;

/**
 * Created by Rafatdin on 10.11.2016.
 */
public enum ProjectRole {
    Manager(1),
    PMO(2),
    Developer(3),
    DBA(4),
    Analyst_Designer(5),
    QA(6),
    HW_Architect(7),
    SW_Architect(8);

    private final int value;

    private ProjectRole(int value){
        this.value = value;
    }

    public int getValue(){return value;}

    public static String getName(int value){
        for(ProjectRole r : values()){
            if(r.value == value){
                return r.name();
            }
        }
        return "";
    }
    public static ProjectRole getRole(int value){
        for(ProjectRole r : values()){
            if(r.value == value){
                return r;
            }
        }
        return null;
    }
}
