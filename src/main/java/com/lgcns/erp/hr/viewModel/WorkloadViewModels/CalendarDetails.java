package com.lgcns.erp.hr.viewModel.WorkloadViewModels;

import com.lgcns.erp.hr.enums.WorkloadType;

import java.util.Date;

/**
 * Created by Rafatdin on 31.10.2016.
 */
public class CalendarDetails {
    private Date Date;
    private int Type;
    private float Duration;

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public float getDuration() {
        return Duration;
    }

    public void setDuration(float duration) {
        Duration = duration;
    }
}
