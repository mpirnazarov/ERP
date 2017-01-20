package com.lgcns.erp.tapps.viewModel.usermenu.Appointment;

import java.sql.Date;

/**
 * Created by Muslimbek on 11/7/2016.
 */
public class AppointmentSummary {
    private Date appointDate, endDate;
    private String appointmentType;
    private String department;
    private String role;
    private int userId;
    private int id;
    private int contractType, postId, departmentId, roleId, externalId;

    public AppointmentSummary() {
    }

    public AppointmentSummary(Date appointDate, String appointmentType, String department, String role, int id, int departmentId) {
        this.appointDate = appointDate;
        this.appointmentType = appointmentType;
        this.department = department;
        this.role = role;
        this.id = id;
        this.departmentId = departmentId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getContractType() {
        return contractType;
    }

    public void setContractType(int contractType) {
        this.contractType = contractType;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getExternalId() {
        return externalId;
    }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }

    @Override
    public String toString() {
        return "AppointmentSummary{" +
                "appointDate=" + appointDate +
                ", endDate=" + endDate +
                ", appointmentType='" + appointmentType + '\'' +
                ", department='" + department + '\'' +
                ", role='" + role + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                ", contractType=" + contractType +
                ", postId=" + postId +
                ", departmentId=" + departmentId +
                ", roleId=" + roleId +
                '}';
    }
}
