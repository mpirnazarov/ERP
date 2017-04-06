package com.lgcns.erp.scheduleManagement.DBEntities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DS on 05.04.2017.
 */
public class ReferenceInScheduleEntityPK implements Serializable {
    private int risId;
    private int scheduleId;

    @Column(name = "ris_id")
    @Id
    public int getRisId() {
        return risId;
    }

    public void setRisId(int risId) {
        this.risId = risId;
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

        ReferenceInScheduleEntityPK that = (ReferenceInScheduleEntityPK) o;

        if (risId != that.risId) return false;
        if (scheduleId != that.scheduleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = risId;
        result = 31 * result + scheduleId;
        return result;
    }
}
