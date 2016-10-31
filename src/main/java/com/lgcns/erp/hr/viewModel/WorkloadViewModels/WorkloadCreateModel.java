package com.lgcns.erp.hr.viewModel.WorkloadViewModels;

import com.lgcns.erp.tapps.entities.ProjectEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by Rafatdin on 31.10.2016.
 */
public class WorkloadCreateModel {
    private List<Integer> Types;
    private List<ProjectEntity> Projects;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Date;
    private Float Duration;
    private List<WorkloadViewModel> Workloads;
    private Float Total;

    public List<Integer> getTypes() {
        return Types;
    }

    public void setTypes(List<Integer> types) {
        Types = types;
    }

    public List<ProjectEntity> getProjects() {
        return Projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        Projects = projects;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public Float getDuration() {
        return Duration;
    }

    public void setDuration(Float duration) {
        Duration = duration;
    }

    public List<WorkloadViewModel> getWorkloads() {
        return Workloads;
    }

    public void setWorkloads(List<WorkloadViewModel> workloads) {
        Workloads = workloads;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float total) {
        Total = total;
    }
}
