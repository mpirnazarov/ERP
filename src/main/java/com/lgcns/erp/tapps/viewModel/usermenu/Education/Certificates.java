package com.lgcns.erp.tapps.viewModel.usermenu.Education;

import java.sql.Date;

/**
 * Created by Muslimbek on 11/2/2016.
 */
public class Certificates {

    private String name;
    private String organization;
    private String number;
    private Date dateTime;
    private String mark;

    public Certificates(String name, String organization, String number, Date dateTime, String mark) {
        this.name = name;
        this.organization = organization;
        this.number = number;
        this.dateTime = dateTime;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
