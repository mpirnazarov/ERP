package com.lgcns.erp.tapps.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Rafatdin on 16.09.2016.
 */
@Entity
@Table(name = "Project_LOG", schema = "public", catalog = "ErpSystem")
public class ProjectLogEntity {
    private long id;
    private Long projectId;
    private String name;
    private String oldName;
    private String code;
    private BigInteger contractUsd;
    private BigInteger oldUsd;
    private BigInteger contractUzs;
    private BigInteger oldUzs;
    private Integer managerId;
    private String oldManagerId;
    private Timestamp startDate;
    private Timestamp oldStart;
    private Timestamp endDate;
    private Timestamp oldEnd;
    private String status;
    private String oldStatus;
    private String cType;
    private Integer customerId;
    private Integer oldCustomerId;
    private Timestamp operationDate;
    private String operationType;
    private String user;

    @Id
    @Column(name = "ID", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ProjectID", nullable = true)
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "Name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "OldName", nullable = true, length = 100)
    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    @Basic
    @Column(name = "Code", nullable = true, length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "ContractUSD", nullable = true, precision = 0)
    public BigInteger getContractUsd() {
        return contractUsd;
    }

    public void setContractUsd(BigInteger contractUsd) {
        this.contractUsd = contractUsd;
    }

    @Basic
    @Column(name = "OldUSD", nullable = true, precision = 0)
    public BigInteger getOldUsd() {
        return oldUsd;
    }

    public void setOldUsd(BigInteger oldUsd) {
        this.oldUsd = oldUsd;
    }

    @Basic
    @Column(name = "ContractUZS", nullable = true, precision = 0)
    public BigInteger getContractUzs() {
        return contractUzs;
    }

    public void setContractUzs(BigInteger contractUzs) {
        this.contractUzs = contractUzs;
    }

    @Basic
    @Column(name = "OldUZS", nullable = true, precision = 0)
    public BigInteger getOldUzs() {
        return oldUzs;
    }

    public void setOldUzs(BigInteger oldUzs) {
        this.oldUzs = oldUzs;
    }

    @Basic
    @Column(name = "ManagerID", nullable = true)
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Basic
    @Column(name = "OldManagerID", nullable = true, length = 10)
    public String getOldManagerId() {
        return oldManagerId;
    }

    public void setOldManagerId(String oldManagerId) {
        this.oldManagerId = oldManagerId;
    }

    @Basic
    @Column(name = "StartDate", nullable = true)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "OldStart", nullable = true)
    public Timestamp getOldStart() {
        return oldStart;
    }

    public void setOldStart(Timestamp oldStart) {
        this.oldStart = oldStart;
    }

    @Basic
    @Column(name = "EndDate", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "OldEnd", nullable = true)
    public Timestamp getOldEnd() {
        return oldEnd;
    }

    public void setOldEnd(Timestamp oldEnd) {
        this.oldEnd = oldEnd;
    }

    @Basic
    @Column(name = "Status", nullable = true, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "OldStatus", nullable = true, length = 50)
    public String getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
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
    @Column(name = "CustomerID", nullable = true)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "OldCustomerID", nullable = true)
    public Integer getOldCustomerId() {
        return oldCustomerId;
    }

    public void setOldCustomerId(Integer oldCustomerId) {
        this.oldCustomerId = oldCustomerId;
    }

    @Basic
    @Column(name = "OperationDate", nullable = true)
    public Timestamp getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    @Basic
    @Column(name = "OperationType", nullable = true, length = 10)
    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Basic
    @Column(name = "USER", nullable = true, length = 100)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectLogEntity that = (ProjectLogEntity) o;

        if (id != that.id) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (oldName != null ? !oldName.equals(that.oldName) : that.oldName != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (contractUsd != null ? !contractUsd.equals(that.contractUsd) : that.contractUsd != null) return false;
        if (oldUsd != null ? !oldUsd.equals(that.oldUsd) : that.oldUsd != null) return false;
        if (contractUzs != null ? !contractUzs.equals(that.contractUzs) : that.contractUzs != null) return false;
        if (oldUzs != null ? !oldUzs.equals(that.oldUzs) : that.oldUzs != null) return false;
        if (managerId != null ? !managerId.equals(that.managerId) : that.managerId != null) return false;
        if (oldManagerId != null ? !oldManagerId.equals(that.oldManagerId) : that.oldManagerId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (oldStart != null ? !oldStart.equals(that.oldStart) : that.oldStart != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (oldEnd != null ? !oldEnd.equals(that.oldEnd) : that.oldEnd != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (oldStatus != null ? !oldStatus.equals(that.oldStatus) : that.oldStatus != null) return false;
        if (cType != null ? !cType.equals(that.cType) : that.cType != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (oldCustomerId != null ? !oldCustomerId.equals(that.oldCustomerId) : that.oldCustomerId != null)
            return false;
        if (operationDate != null ? !operationDate.equals(that.operationDate) : that.operationDate != null)
            return false;
        if (operationType != null ? !operationType.equals(that.operationType) : that.operationType != null)
            return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (oldName != null ? oldName.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (contractUsd != null ? contractUsd.hashCode() : 0);
        result = 31 * result + (oldUsd != null ? oldUsd.hashCode() : 0);
        result = 31 * result + (contractUzs != null ? contractUzs.hashCode() : 0);
        result = 31 * result + (oldUzs != null ? oldUzs.hashCode() : 0);
        result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
        result = 31 * result + (oldManagerId != null ? oldManagerId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (oldStart != null ? oldStart.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (oldEnd != null ? oldEnd.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (oldStatus != null ? oldStatus.hashCode() : 0);
        result = 31 * result + (cType != null ? cType.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (oldCustomerId != null ? oldCustomerId.hashCode() : 0);
        result = 31 * result + (operationDate != null ? operationDate.hashCode() : 0);
        result = 31 * result + (operationType != null ? operationType.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
