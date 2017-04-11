package com.lgcns.erp.scheduleManagement.viewModel;

import com.lgcns.erp.workflow.Model.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by DS on 06.04.2017.
 */
public class ScheduleVM {
    private int scheduleId;
    private String title;
    private String description;
    private String place;
    private int sType;
    private String other;
    private Date dateFrom;
    private Date dateTo;
    private boolean compulsory;
    private boolean toNotify;
    private boolean isDraft;
    private int authorId;
    private int[] participants;
    private int[] references;
    private List<Attachment> attachments;
    private MultipartFile[] file;

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

    public int getsType() {
        return sType;
    }

    public void setsType(int sType) {
        this.sType = sType;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public boolean isCompulsory() {
        return compulsory;
    }

    public void setCompulsory(boolean compulsory) {
        this.compulsory = compulsory;
    }

    public boolean isToNotify() {
        return toNotify;
    }

    public void setToNotify(boolean toNotify) {
        this.toNotify = toNotify;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public MultipartFile[] getFile() {
        return file;
    }

    public void setFile(MultipartFile[] file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "ScheduleVM{" +
                "scheduleId=" + scheduleId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", place='" + place + '\'' +
                ", sType=" + sType +
                ", other='" + other + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", compulsory=" + compulsory +
                ", toNotify=" + toNotify +
                ", isDraft=" + isDraft +
                ", authorId=" + authorId +
                ", participants=" + Arrays.toString(participants) +
                ", references=" + Arrays.toString(references) +
                ", attachments=" + attachments +
                ", file=" + Arrays.toString(file) +
                '}';
    }
}
