package com.lgcns.erp.hr.viewModel.WorkloadViewModels;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rafatdin on 31.10.2016.
 */
public class CalendarRequestDataJson implements Serializable{
    @JsonProperty("EnteredValue")
    private String enteredValue;
    @JsonProperty("Monday")
    private Date Monday;
    @JsonProperty("WeekDate")
    private int WeekDate;
    @JsonProperty("WorkloadName")
    private String WorkloadName;
    @JsonProperty("WorkloadType")
    private String WorkloadType;

    public String getEnteredValue() {
        return enteredValue;
    }

    public void setEnteredValue(String enteredValue) {
        this.enteredValue = enteredValue;
    }

    public Date getMonday() {
        return Monday;
    }

    public void setMonday(Date monday) {
        Monday = monday;
    }

    public int getWeekDate() {
        return WeekDate;
    }

    public void setWeekDate(int weekDate) {
        WeekDate = weekDate;
    }

    public String getWorkloadName() {
        return WorkloadName;
    }

    public void setWorkloadName(String workloadName) {
        WorkloadName = workloadName;
    }

    public String getWorkloadType() {
        return WorkloadType;
    }

    public void setWorkloadType(String workloadType) {
        WorkloadType = workloadType;
    }
}
