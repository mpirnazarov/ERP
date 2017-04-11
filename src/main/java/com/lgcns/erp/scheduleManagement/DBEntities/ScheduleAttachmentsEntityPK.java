package com.lgcns.erp.scheduleManagement.DBEntities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Sarvar on 11.04.2017.
 */
public class ScheduleAttachmentsEntityPK implements Serializable {
    private int attachmentId;
    private int scheduleId;

    @Column(name = "attachment_id")
    @Id
    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
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

        ScheduleAttachmentsEntityPK that = (ScheduleAttachmentsEntityPK) o;

        if (attachmentId != that.attachmentId) return false;
        if (scheduleId != that.scheduleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attachmentId;
        result = 31 * result + scheduleId;
        return result;
    }
}
