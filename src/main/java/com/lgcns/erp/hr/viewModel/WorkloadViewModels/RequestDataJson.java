package com.lgcns.erp.hr.viewModel.WorkloadViewModels;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rafatdin on 31.10.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDataJson implements Serializable {
    @JsonProperty("Number")
    private String Number;
    @JsonProperty("ProjectId")
    private int ProjectId;
    @JsonProperty("Date")
    private Date Date;
    @JsonProperty("Duration")
    private String Duration;
    @JsonProperty("TypeId")
    private int TypeId;
    @JsonProperty("SelectedType")
    private String SelectedType;
    @JsonProperty("SelectedProject")
    private String SelectedProject;

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public void setProjectId() {
        ProjectId = 0;
    }


    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public String getSelectedType() {
        return SelectedType;
    }

    public void setSelectedType(String selectedType) {
        SelectedType = selectedType;
    }

    public String getSelectedProject() {
        return SelectedProject;
    }

    public void setSelectedProject(String selectedProject) {
        SelectedProject = selectedProject;
    }
}
