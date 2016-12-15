package com.lgcns.erp.tapps.Enums;

/**
 * Created by Muslimbek on 11.10.2016.
 */
public enum Document_Type {
    Workbook(1), Passport(2), Pension_Book(3), TIN(4), Copy_Of_Degree(5), Diploma(6), Certificates(7);

    int value;
    Document_Type(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}