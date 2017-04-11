package com.lgcns.erp.scheduleManagement.DBEntities;

import javax.persistence.*;

/**
 * Created by Sarvar on 11.04.2017.
 */
@Entity
@Table(name = "participant_in_schedule", schema = "schedule", catalog = "LgErpSystem")
@IdClass(ParticipantInScheduleEntityPK.class)
public class ParticipantInScheduleEntity {
    private int pisId;
    private int scheduleId;
    private Integer userId;
    private Integer status;
    private String reason;
    private ScheduleEntity scheduleByScheduleId;

    @Id
    @Column(name = "pis_id")
    public int getPisId() {
        return pisId;
    }

    public void setPisId(int pisId) {
        this.pisId = pisId;
    }

    @Id
    @Column(name = "schedule_id")
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipantInScheduleEntity that = (ParticipantInScheduleEntity) o;

        if (pisId != that.pisId) return false;
        if (scheduleId != that.scheduleId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pisId;
        result = 31 * result + scheduleId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id", nullable = false)
    public ScheduleEntity getScheduleByScheduleId() {
        return scheduleByScheduleId;
    }

    public void setScheduleByScheduleId(ScheduleEntity scheduleByScheduleId) {
        this.scheduleByScheduleId = scheduleByScheduleId;
    }
}
