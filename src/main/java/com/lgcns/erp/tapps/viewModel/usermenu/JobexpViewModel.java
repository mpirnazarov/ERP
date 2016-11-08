package com.lgcns.erp.tapps.viewModel.usermenu;

import java.sql.Date;

/**
 * Created by Dell on 25-Oct-16.
 */
public class JobexpViewModel {
    private String organization;
    private String position;
    private Date startDate;
    private Date endDate;


    public JobexpViewModel(String organization, String post, Date startDate, Date endDate) {
        this.organization = organization;
        this.position = post;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
}
