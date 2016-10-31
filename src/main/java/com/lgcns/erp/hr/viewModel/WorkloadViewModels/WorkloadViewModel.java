package com.lgcns.erp.hr.viewModel.WorkloadViewModels;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Rafatdin on 31.10.2016.
 */
public class WorkloadViewModel {
    public int Id;

    @NotEmpty
    private int UserId;
    private String EmployeeName;
    @NotEmpty
    private int ProjectId;
    private String Project;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date Date;
    @NotEmpty
    private Float Duration;
    public String Note;
    @NotEmpty
    private int WorkLoadTypeId;
    private String WorkLoadType;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public String getProject() {
        return Project;
    }

    public void setProject(String project) {
        Project = project;
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

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public int getWorkLoadTypeId() {
        return WorkLoadTypeId;
    }

    public void setWorkLoadTypeId(int workLoadTypeId) {
        WorkLoadTypeId = workLoadTypeId;
    }

    public String getWorkLoadType() {
        return WorkLoadType;
    }

    public void setWorkLoadType(String workLoadType) {
        WorkLoadType = workLoadType;
    }
}
