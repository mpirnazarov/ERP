package com.lgcns.erp.hr.viewModel.AppointViewModels;


import java.util.Date;
import java.util.List;

/**
 * Created by Rafatdin on 10.11.2016.
 */
public class AppointViewModel {
    private String projectName;
    private int projectId;
    private Date startDate;
    private Date endDate;
    private int status;

    public AppointViewModel(){}

    public AppointViewModel(String projectName, int projectId, Date startDate, Date endDate, int status) {
        this.projectName = projectName;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
