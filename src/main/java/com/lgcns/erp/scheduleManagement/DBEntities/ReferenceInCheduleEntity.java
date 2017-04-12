package com.lgcns.erp.scheduleManagement.DBEntities;

import javax.persistence.*;

/**
 * Created by DS on 11.04.2017.
 */
@Entity
@Table(name = "reference_in_chedule", schema = "schedule", catalog = "LgErpSystem")
public class ReferenceInCheduleEntity {
    private int risId;
    private Integer scheduleId;
    private Integer userId;
    private ScheduleEntity scheduleByScheduleId;

    @Id
    @Column(name = "ris_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getRisId() {
        return risId;
    }

    public void setRisId(int risId) {
        this.risId = risId;
    }

    @Basic
    @Column(name = "schedule_id")
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
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

        ReferenceInCheduleEntity that = (ReferenceInCheduleEntity) o;

        if (risId != that.risId) return false;
        if (scheduleId != null ? !scheduleId.equals(that.scheduleId) : that.scheduleId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = risId;
        result = 31 * result + (scheduleId != null ? scheduleId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id", insertable = false, updatable = false)
    public ScheduleEntity getScheduleByScheduleId() {
        return scheduleByScheduleId;
    }

    public void setScheduleByScheduleId(ScheduleEntity scheduleByScheduleId) {
        this.scheduleByScheduleId = scheduleByScheduleId;
    }
}
