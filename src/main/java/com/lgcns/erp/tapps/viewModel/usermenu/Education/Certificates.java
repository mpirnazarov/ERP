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
    private int id;
    private boolean passed;
    private int type;
    private String degree;

    public Certificates() {
    }

    public Certificates(String name, String organization, String number, Date dateTime, String mark, int id, int type, String degree) {
        this.name = name;
        this.organization = organization;
        this.number = number;
        this.dateTime = dateTime;
        this.mark = mark;
        this.id = id;
        this.type = type;
        this.degree = degree;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Certificates{" +
                "name='" + name + '\'' +
                ", organization='" + organization + '\'' +
                ", number='" + number + '\'' +
                ", dateTime=" + dateTime +
                ", mark='" + mark + '\'' +
                ", id=" + id +
                ", passed=" + passed +
                '}';
    }
}
