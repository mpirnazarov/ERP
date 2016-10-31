package com.lgcns.erp.hr.viewModel.WorkloadViewModels;

import com.lgcns.erp.tapps.entities.ProjectEntity;
import com.lgcns.erp.tapps.entities.WorkloadEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by Rafatdin on 31.10.2016.
 */
public class CalendarReturningModel {
    private Date Monday;
    private List<ProjectEntity> Projects;
    private List<WorkloadEntity> Workloads;

    public Date getMonday() {
        return Monday;
    }

    public void setMonday(Date monday) {
        Monday = monday;
    }

    public List<ProjectEntity> getProjects() {
        return Projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        Projects = projects;
    }

    public List<WorkloadEntity> getWorkloads() {
        return Workloads;
    }

    public void setWorkloads(List<WorkloadEntity> workloads) {
        Workloads = workloads;
    }
}
