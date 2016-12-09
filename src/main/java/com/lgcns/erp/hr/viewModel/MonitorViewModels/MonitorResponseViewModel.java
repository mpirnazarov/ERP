package com.lgcns.erp.hr.viewModel.MonitorViewModels;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rafatdin on 09.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MonitorResponseViewModel implements Serializable {
    @JsonProperty("Employee")
    private String Employee;
    @JsonProperty("Project")
    private String Project;
    @JsonProperty("Type")
    private String Type;
    @JsonProperty("Date")
    private String Date;
    @JsonProperty("Duration")
    private String Duration;

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public String getProject() {
        return Project;
    }

    public void setProject(String project) {
        Project = project;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }
}
