package com.lgcns.erp.scheduleManagement.model;

import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;

/**
 * Created by DS on 06.04.2017.
 */
public class Attachment {
    private int attachmentId;
    private int scheduleId;
    private String attachmentPath;
    private ScheduleEntity scheduleByScheduleId;

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public ScheduleEntity getScheduleByScheduleId() {
        return scheduleByScheduleId;
    }

    public void setScheduleByScheduleId(ScheduleEntity scheduleByScheduleId) {
        this.scheduleByScheduleId = scheduleByScheduleId;
    }
}
