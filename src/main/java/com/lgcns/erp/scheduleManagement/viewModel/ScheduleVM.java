package com.lgcns.erp.scheduleManagement.viewModel;

import com.lgcns.erp.workflow.Model.Attachment;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by DS on 06.04.2017.
 */
public class ScheduleVM {
    private int scheduleId;
    private String title;
    private String description;
    private String place;
    private Integer sType;
    private String other;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private Boolean isCompulsory;
    private Boolean toNotify;
    private Boolean isDraft;
    private int authorId;
    private int[] participants;
    private int[] references;
    private List<Attachment> attachments;

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public int[] getParticipants() {
        return participants;
    }

    public void setParticipants(int[] participants) {
        this.participants = participants;
    }

    public int[] getReferences() {
        return references;
    }

    public void setReferences(int[] references) {
        this.references = references;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getsType() {
        return sType;
    }

    public void setsType(Integer sType) {
        this.sType = sType;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
    }

    public Boolean getCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(Boolean compulsory) {
        isCompulsory = compulsory;
    }

    public Boolean getToNotify() {
        return toNotify;
    }

    public void setToNotify(Boolean toNotify) {
        this.toNotify = toNotify;
    }

    public Boolean getDraft() {
        return isDraft;
    }

    public void setDraft(Boolean draft) {
        isDraft = draft;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
