package com.lgcns.erp.tapps.model.DbEntities;

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
    private int code;
    private Date startDate;
    private Date endDate;
    private BigInteger type;
    private BigInteger status;
    private Collection<ContactInProjectsEntity> contactInProjectsesById;
    private Collection<ProjectLocalizationsEntity> projectLocalizationsesById;
    private Collection<UserInProjectsEntity> userInProjectsesById;
    private Collection<WorkloadEntity> workloadsById;

    public void setCode(String code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code", nullable = false, length = 100)
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
    public BigInteger getType() {
        return type;
    }

    public void setType(BigInteger type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status", nullable = false, precision = 0)
    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

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
        result = 31 * result + code;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "projectsByProjectId")
    public Collection<ContactInProjectsEntity> getContactInProjectsesById() {
        return contactInProjectsesById;
    }

    public void setContactInProjectsesById(Collection<ContactInProjectsEntity> contactInProjectsesById) {
        this.contactInProjectsesById = contactInProjectsesById;
    }

    @OneToMany(mappedBy = "projectsByProjectId")
    public Collection<ProjectLocalizationsEntity> getProjectLocalizationsesById() {
        return projectLocalizationsesById;
    }

    public void setProjectLocalizationsesById(Collection<ProjectLocalizationsEntity> projectLocalizationsesById) {
        this.projectLocalizationsesById = projectLocalizationsesById;
    }

    @OneToMany(mappedBy = "projectsByProjectId")
    public Collection<UserInProjectsEntity> getUserInProjectsesById() {
        return userInProjectsesById;
    }

    public void setUserInProjectsesById(Collection<UserInProjectsEntity> userInProjectsesById) {
        this.userInProjectsesById = userInProjectsesById;
    }

    @OneToMany(mappedBy = "projectsByProjectId")
    public Collection<WorkloadEntity> getWorkloadsById() {
        return workloadsById;
    }

    public void setWorkloadsById(Collection<WorkloadEntity> workloadsById) {
        this.workloadsById = workloadsById;
    }
}
