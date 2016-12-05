package com.lgcns.erp.tapps.viewModel.usermenu;

import java.sql.Date;

/**
 * Created by Dell on 25-Oct-16.
 */
public class TrainViewModel {
    private String name, mark;
    private Integer certificateId, numberOfHours;
    private String organization;
    private Date dateFrom, dateTo;
    private int id;
    public TrainViewModel(String name, Integer certificateId, String organization, Date dateFrom, Date dateTo, int numberOfHours, String mark, int id) {
        this.name = name;
        this.certificateId = certificateId;
        this.organization = organization;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.numberOfHours = numberOfHours;
        this.mark = mark;
        this.id=id;
    }

    public TrainViewModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TrainViewModel{" +
                "name='" + name + '\'' +
                ", mark='" + mark + '\'' +
                ", certificateId=" + certificateId +
                ", numberOfHours=" + numberOfHours +
                ", organization='" + organization + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
