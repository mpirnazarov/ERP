package com.lgcns.erp.tapps.viewModel.usermenu.Education;

import java.sql.Date;

/**
 * Created by Muslimbek on 11/2/2016.
 */
public class Educations {
    private String name;
    private String major;
    private String degree;
    private Date startDate;
    private Date endDate;

    @Override
    public String toString() {
        return "Educations{" +
                "name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", degree='" + degree + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public Educations(String name, String major, String degree, Date startDate, Date endDate) {
        this.name = name;
        this.major = major;
        this.degree = degree;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
