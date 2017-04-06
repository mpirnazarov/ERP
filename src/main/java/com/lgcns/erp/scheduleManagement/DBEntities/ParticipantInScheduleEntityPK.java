package com.lgcns.erp.scheduleManagement.DBEntities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DS on 05.04.2017.
 */
public class ParticipantInScheduleEntityPK implements Serializable {
    private int pisId;
    private int scheduleId;

    @Column(name = "pis_id")
    @Id
    public int getPisId() {
        return pisId;
    }

    public void setPisId(int pisId) {
        this.pisId = pisId;
    }

    @Column(name = "schedule_id")
    @Id
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipantInScheduleEntityPK that = (ParticipantInScheduleEntityPK) o;

        if (pisId != that.pisId) return false;
        if (scheduleId != that.scheduleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pisId;
        result = 31 * result + scheduleId;
        return result;
    }
}
