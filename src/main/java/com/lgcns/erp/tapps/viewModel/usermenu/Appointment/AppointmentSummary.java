package com.lgcns.erp.tapps.viewModel.usermenu.Appointment;

import java.sql.Date;

/**
 * Created by Muslimbek on 11/7/2016.
 */
public class AppointmentSummary {
    private Date appointDate;
    private String appointmentType;
    private String department;
    private String role;

    public AppointmentSummary(Date appointDate, String appointmentType, String department, String role) {
        this.appointDate = appointDate;
        this.appointmentType = appointmentType;
        this.department = department;
        this.role = role;
    }

    public Date getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(Date appointDate) {
        this.appointDate = appointDate;
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
