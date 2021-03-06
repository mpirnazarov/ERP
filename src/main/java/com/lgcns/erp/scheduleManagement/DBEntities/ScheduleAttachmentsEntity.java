package com.lgcns.erp.scheduleManagement.DBEntities;

import javax.persistence.*;

/**
 * Created by DS on 11.04.2017.
 */
@Entity
@Table(name = "schedule_attachments", schema = "schedule", catalog = "LgErpSystem")
public class ScheduleAttachmentsEntity {
    private int attachmentId;
    private Integer scheduleId;
    private String attachmentPath;
    private ScheduleEntity scheduleByScheduleId;

    @Id
    @Column(name = "attachment_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
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
    @Column(name = "attachment_path")
    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleAttachmentsEntity that = (ScheduleAttachmentsEntity) o;

        if (attachmentId != that.attachmentId) return false;
        if (scheduleId != null ? !scheduleId.equals(that.scheduleId) : that.scheduleId != null) return false;
        if (attachmentPath != null ? !attachmentPath.equals(that.attachmentPath) : that.attachmentPath != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attachmentId;
        result = 31 * result + (scheduleId != null ? scheduleId.hashCode() : 0);
        result = 31 * result + (attachmentPath != null ? attachmentPath.hashCode() : 0);
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
