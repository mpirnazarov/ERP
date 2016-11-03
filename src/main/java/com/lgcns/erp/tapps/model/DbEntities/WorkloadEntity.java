package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "workload", schema = "public", catalog = "LgErpSystem")
public class WorkloadEntity {
    private long id;
    private int userId;
    private Integer projectId;
    private Date date;
    private int duration;
    private Integer workloadType;
    private UsersEntity usersByUserId;
    private ProjectsEntity projectsByProjectId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "project_id", nullable = true, insertable = false, updatable = false)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "duration", nullable = false)
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "workload_type", nullable = false, precision = 0)
    public Integer getWorkloadType() {
        return workloadType;
    }

    public void setWorkloadType(Integer workloadType) {
        this.workloadType = workloadType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkloadEntity that = (WorkloadEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (duration != that.duration) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (workloadType != null ? !workloadType.equals(that.workloadType) : that.workloadType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + userId;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + duration;
        result = 31 * result + (workloadType != null ? workloadType.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    public ProjectsEntity getProjectsByProjectId() {
        return projectsByProjectId;
    }

    public void setProjectsByProjectId(ProjectsEntity projectsByProjectId) {
        this.projectsByProjectId = projectsByProjectId;
    }
}
