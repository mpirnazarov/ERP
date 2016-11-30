package com.lgcns.erp.tapps.viewModel.usermenu;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dell on 25-Oct-16.
 */
public class JobexpViewModel {
    private String organization;
    private String position;
    private Date startDate;
    private Date endDate;
    private int contractType;
    private Map<Integer, String> contracts =null;


    public JobexpViewModel(String organization, String post, Date startDate, Date endDate, int contractType) {
        this.organization = organization;
        this.position = post;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractType = contractType;
    }

    public JobexpViewModel() {
        contracts = new HashMap<Integer, String>();
        contracts.put(1, "FULL TIME");
        contracts.put(2, "CONTRACT TYPE");
        contracts.put(3, "PART TIME");
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

    public int getContractType() {
        return contractType;
    }

    public void setContractType(int contractType) {
        this.contractType = contractType;
    }

    public Map<Integer, String> getContracts() {
        return contracts;
    }

    public void setContracts(Map<Integer, String> contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "JobexpViewModel{" +
                "organization='" + organization + '\'' +
                ", position='" + position + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", Contract type=" + contractType +
                '}';
    }
}
