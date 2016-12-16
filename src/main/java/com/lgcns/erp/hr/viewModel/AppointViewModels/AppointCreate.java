package com.lgcns.erp.hr.viewModel.AppointViewModels;

import java.util.Date;

/**
 * Created by Rafatdin on 15.12.2016.
 */
public class AppointCreate {
    private int ProjectId;
    private int EmpId;
    private int RoleId;
    private String DateFrom;
    private String DateTo;

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int empId) {
        EmpId = empId;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        DateFrom = dateFrom;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        DateTo = dateTo;
    }
}
