package com.lgcns.erp.tapps.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Rafatdin on 16.09.2016.
 */
@Entity
@Table(name = "Workload", schema = "public", catalog = "ErpSystem")
public class WorkloadEntity {
    private int id;
    private Timestamp date;
    private int userId;
    private Integer projectId;
    private BigInteger duration;
    private String note;
    private int workloadTypeId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "UserID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "ProjectID", nullable = true)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "Duration", nullable = false, precision = 0)
    public BigInteger getDuration() {
        return duration;
    }

    public void setDuration(BigInteger duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "Note", nullable = true, length = 150)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "WorkloadTypeID", nullable = false)
    public int getWorkloadTypeId() {
        return workloadTypeId;
    }

    public void setWorkloadTypeId(int workloadTypeId) {
        this.workloadTypeId = workloadTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkloadEntity that = (WorkloadEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (workloadTypeId != that.workloadTypeId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + workloadTypeId;
        return result;
    }
}
