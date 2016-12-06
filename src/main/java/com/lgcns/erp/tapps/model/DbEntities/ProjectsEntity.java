package com.lgcns.erp.tapps.model.DbEntities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "projects", schema = "public", catalog = "LgErpSystem")
public class ProjectsEntity {
    private int id;
    private String code;
    private Date startDate;
    private Date endDate;
    private String type;
    private Integer status;
    private Float moneyUzs;
    private Float moneyUsd;
    private String name;
    private Integer customerId;
    private Collection<ContactInProjectsEntity> contactInProjectsesById;
    private Collection<UserInProjectsEntity> userInProjectsesById;
    private Collection<WorkloadEntity> workloadsById;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "money_uzs", nullable = false, length = 100)
    public Float getMoneyUzs() {
        return moneyUzs;
    }

    public void setMoneyUzs(Float moneyUzs) {
        this.moneyUzs = moneyUzs;
    }

    @Basic
    @Column(name = "money_usd", nullable = false, length = 100)
    public Float getMoneyUsd() {
        return moneyUsd;
    }

    public void setMoneyUsd(Float moneyUsd) {
        this.moneyUsd = moneyUsd;
    }

    @Basic
    @Column(name = "code", nullable = false, length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Basic
    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "type", nullable = false, precision = 0, length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status", nullable = false, precision = 0)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "customerid", nullable = false)
    public Integer getCustomerId() {return customerId;}

    public void setCustomerId(Integer customerId) {this.customerId = customerId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectsEntity that = (ProjectsEntity) o;

        if (id != that.id) return false;
        if (code != that.code) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "projectsByProjectId", cascade = CascadeType.ALL)
    public Collection<ContactInProjectsEntity> getContactInProjectsesById() {
        return contactInProjectsesById;
    }

    public void setContactInProjectsesById(Collection<ContactInProjectsEntity> contactInProjectsesById) {
        this.contactInProjectsesById = contactInProjectsesById;
    }

    @OneToMany(mappedBy = "projectsByProjectId", cascade = CascadeType.ALL)
    public Collection<UserInProjectsEntity> getUserInProjectsesById() {
        return userInProjectsesById;
    }

    public void setUserInProjectsesById(Collection<UserInProjectsEntity> userInProjectsesById) {
        this.userInProjectsesById = userInProjectsesById;
    }

    @OneToMany(mappedBy = "projectsByProjectId", cascade = CascadeType.ALL)
    public Collection<WorkloadEntity> getWorkloadsById() {
        return workloadsById;
    }

    public void setWorkloadsById(Collection<WorkloadEntity> workloadsById) {
        this.workloadsById = workloadsById;
    }
}
