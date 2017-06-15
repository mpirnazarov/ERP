package com.lgcns.erp.attendanceManagement.enums;

/**
 * Created by DS on 06.04.2017.
 */
public enum AttendanceStatus {
    On_time(1), Late(2), Absent(3);

    int value;
    AttendanceStatus(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
