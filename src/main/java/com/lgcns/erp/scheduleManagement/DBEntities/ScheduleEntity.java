package com.lgcns.erp.scheduleManagement.DBEntities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by DS on 11.04.2017.
 */
@Entity
@Table(name = "schedule", schema = "schedule", catalog = "LgErpSystem")
public class ScheduleEntity {
    private int scheduleId;
    private String title;
    private String description;
    private String place;
    private Integer stype;
    private String other;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private Boolean isCompulsory;
    private Boolean toNotify;
    private Boolean isDraft;
    private Integer autherId;
    private Boolean isPrivate;
    private Collection<ParticipantInScheduleEntity> participantInSchedulesByScheduleId;
    private Collection<ReferenceInCheduleEntity> referenceInChedulesByScheduleId;
    private Collection<ScheduleAttachmentsEntity> scheduleAttachmentssByScheduleId;

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "stype")
    public Integer getStype() {
        return stype;
    }

    public void setStype(Integer stype) {
        this.stype = stype;
    }

    @Basic
    @Column(name = "other")
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Basic
    @Column(name = "date_from")
    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Basic
    @Column(name = "date_to")
    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
    }

    @Basic
    @Column(name = "is_compulsory")
    public Boolean getCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(Boolean compulsory) {
        isCompulsory = compulsory;
    }

    @Basic
    @Column(name = "to_notify")
    public Boolean getToNotify() {
        return toNotify;
    }

    public void setToNotify(Boolean toNotify) {
        this.toNotify = toNotify;
    }

    @Basic
    @Column(name = "is_draft")
    public Boolean getDraft() {
        return isDraft;
    }

    public void setDraft(Boolean draft) {
        isDraft = draft;
    }

    @Basic
    @Column(name = "auther_id")
    public Integer getAutherId() {
        return autherId;
    }

    public void setAutherId(Integer autherId) {
        this.autherId = autherId;
    }

    @Basic
    @Column(name = "isPrivate")
    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleEntity that = (ScheduleEntity) o;

        if (scheduleId != that.scheduleId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (stype != null ? !stype.equals(that.stype) : that.stype != null) return false;
        if (other != null ? !other.equals(that.other) : that.other != null) return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(that.dateTo) : that.dateTo != null) return false;
        if (isCompulsory != null ? !isCompulsory.equals(that.isCompulsory) : that.isCompulsory != null) return false;
        if (toNotify != null ? !toNotify.equals(that.toNotify) : that.toNotify != null) return false;
        if (isDraft != null ? !isDraft.equals(that.isDraft) : that.isDraft != null) return false;
        if (autherId != null ? !autherId.equals(that.autherId) : that.autherId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = scheduleId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (stype != null ? stype.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (isCompulsory != null ? isCompulsory.hashCode() : 0);
        result = 31 * result + (toNotify != null ? toNotify.hashCode() : 0);
        result = 31 * result + (isDraft != null ? isDraft.hashCode() : 0);
        result = 31 * result + (autherId != null ? autherId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "scheduleByScheduleId")
    public Collection<ParticipantInScheduleEntity> getParticipantInSchedulesByScheduleId() {
        return participantInSchedulesByScheduleId;
    }

    public void setParticipantInSchedulesByScheduleId(Collection<ParticipantInScheduleEntity> participantInSchedulesByScheduleId) {
        this.participantInSchedulesByScheduleId = participantInSchedulesByScheduleId;
    }

    @OneToMany(mappedBy = "scheduleByScheduleId")
    public Collection<ReferenceInCheduleEntity> getReferenceInChedulesByScheduleId() {
        return referenceInChedulesByScheduleId;
    }

    public void setReferenceInChedulesByScheduleId(Collection<ReferenceInCheduleEntity> referenceInChedulesByScheduleId) {
        this.referenceInChedulesByScheduleId = referenceInChedulesByScheduleId;
    }

    @OneToMany(mappedBy = "scheduleByScheduleId")
    public Collection<ScheduleAttachmentsEntity> getScheduleAttachmentssByScheduleId() {
        return scheduleAttachmentssByScheduleId;
    }

    public void setScheduleAttachmentssByScheduleId(Collection<ScheduleAttachmentsEntity> scheduleAttachmentssByScheduleId) {
        this.scheduleAttachmentssByScheduleId = scheduleAttachmentssByScheduleId;
    }
}
