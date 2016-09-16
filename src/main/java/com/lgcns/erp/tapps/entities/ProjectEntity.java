package com.lgcns.erp.tapps.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Rafatdin on 16.09.2016.
 */
@Entity
@Table(name = "Project", schema = "public", catalog = "ErpSystem")
public class ProjectEntity {
    private int id;
    private String name;
    private String code;
    private BigInteger contractUsd;
    private BigInteger contractUzs;
    private int managerId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String status;
    private String cType;
    private Integer customerId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Code", nullable = false, length = 50)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "ContractUSD", nullable = false, precision = 0)
    public BigInteger getContractUsd() {
        return contractUsd;
    }

    public void setContractUsd(BigInteger contractUsd) {
        this.contractUsd = contractUsd;
    }

    @Basic
    @Column(name = "ContractUZS", nullable = false, precision = 0)
    public BigInteger getContractUzs() {
        return contractUzs;
    }

    public void setContractUzs(BigInteger contractUzs) {
        this.contractUzs = contractUzs;
    }

    @Basic
    @Column(name = "ManagerID", nullable = false)
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Basic
    @Column(name = "StartDate", nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "EndDate", nullable = false)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "Status", nullable = false, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CType", nullable = true, length = 50)
    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    @Basic
    @Column(name = "CustomerId", nullable = true)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectEntity that = (ProjectEntity) o;

        if (id != that.id) return false;
        if (managerId != that.managerId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (contractUsd != null ? !contractUsd.equals(that.contractUsd) : that.contractUsd != null) return false;
        if (contractUzs != null ? !contractUzs.equals(that.contractUzs) : that.contractUzs != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (cType != null ? !cType.equals(that.cType) : that.cType != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (contractUsd != null ? contractUsd.hashCode() : 0);
        result = 31 * result + (contractUzs != null ? contractUzs.hashCode() : 0);
        result = 31 * result + managerId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (cType != null ? cType.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        return result;
    }
}
