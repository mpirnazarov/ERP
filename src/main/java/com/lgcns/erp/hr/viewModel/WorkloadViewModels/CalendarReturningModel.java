package com.lgcns.erp.hr.viewModel.WorkloadViewModels;

import com.lgcns.erp.tapps.model.DbEntities.ProjectsEntity;
import com.lgcns.erp.tapps.model.DbEntities.WorkloadEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by Rafatdin on 31.10.2016.
 */
public class CalendarReturningModel {
    private Date Monday;
    private List<ProjectsEntity> Projects;
    private List<WorkloadEntity> Workloads;

    public Date getMonday() {
        return Monday;
    }

    public void setMonday(Date monday) {
        Monday = monday;
    }

    public List<ProjectsEntity> getProjects() {
        return Projects;
    }

    public void setProjects(List<ProjectsEntity> projects) {
        Projects = projects;
    }

    public List<WorkloadEntity> getWorkloads() {
        return Workloads;
    }

    public void setWorkloads(List<WorkloadEntity> workloads) {
        Workloads = workloads;
    }
}
