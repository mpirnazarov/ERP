package com.lgcns.erp.scheduleManagement.DBEntities;

import javax.persistence.*;

/**
 * Created by DS on 05.04.2017.
 */
@Entity
@Table(name = "reference_in_schedule", schema = "schedule", catalog = "LgErpSystem")
@IdClass(ReferenceInScheduleEntityPK.class)
public class ReferenceInScheduleEntity {
    private int risId;
    private int scheduleId;
    private Integer userId;
    private ScheduleEntity scheduleByScheduleId;

    @Id
    @Column(name = "ris_id")
    public int getRisId() {
        return risId;
    }

    public void setRisId(int risId) {
        this.risId = risId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReferenceInScheduleEntity that = (ReferenceInScheduleEntity) o;

        if (risId != that.risId) return false;
        if (scheduleId != that.scheduleId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = risId;
        result = 31 * result + scheduleId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
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
