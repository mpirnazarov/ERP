package com.lgcns.erp.tapps.viewModel.usermenu;

import java.util.Date;

/**
 * Created by Muslimbek on 25-Oct-16.
 */

public class AppointmentrecViewModel {
    private Date appointDate;
    private String appointmentType;
    private String department;
    private String role;

    public AppointmentrecViewModel(Date dateFrom, String appointmentType, String department, String role) {
        this.appointDate=dateFrom;
        this.appointmentType=appointmentType;
        this.department = department;
        this.role = role;
    }


    public void setAppointDate(Date appointDate) {
        this.appointDate = appointDate;
    }
    public Date getAppointDate() {
        return appointDate;
    }


    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
