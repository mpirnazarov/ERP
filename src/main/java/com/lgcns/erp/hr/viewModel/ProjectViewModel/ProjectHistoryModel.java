package com.lgcns.erp.hr.viewModel.ProjectViewModel;

/**
 * Created by Rafatdin on 15.12.2016.
 */
public class ProjectHistoryModel {
    private String projectName;
    private String projectManager;
    private String description;
    private String period;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
