package com.lgcns.erp.hr.viewModel.AppointViewModels;


import java.util.Date;

/**
 * Created by Rafatdin on 10.11.2016.
 */
public class ProjectMembers {
    private int appointmentId;
    private String userName;
    private int userId;
    private Date participatingFrom;
    private Date participatingTo;
    private int roleId;
    private String roleName;

    public ProjectMembers(){}
    public ProjectMembers(int appointmentId, String name, int id, Date from, Date to, int role, String roleName){
        this.appointmentId = appointmentId;
        this.userName = name;
        this.userId = id;
        this.participatingFrom = from;
        this.participatingTo = to;
        this.roleId = role;
        this.roleName = roleName;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setApppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getParticipatingFrom() {
        return participatingFrom;
    }

    public void setParticipatingFrom(Date participatingFrom) {
        this.participatingFrom = participatingFrom;
    }

    public Date getParticipatingTo() {
        return participatingTo;
    }

    public void setParticipatingTo(Date participatingTo) {
        this.participatingTo = participatingTo;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
