package com.lgcns.erp.scheduleManagement.model;

import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;

/**
 * Created by DS on 06.04.2017.
 */
public class Attachment {
    private int attachmentId;
    private int scheduleId;
    private String attachmentPath;
    private String attachmentName;

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

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

}
