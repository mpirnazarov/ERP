package com.lgcns.erp.workflow.ViewModel;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

/**
 * Created by Muslimbek Pirnazarov on 02/07/2017.
 */
public class LeaveApproveVM {
    private int id, absenceType;
    private String description;
    private Date start, end;
    private MultipartFile[] file;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public MultipartFile[] getFile() {
        return file;
    }

    public void setFile(MultipartFile[] file) {
        this.file = file;
    }

    public int getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(int absenceType) {
        this.absenceType = absenceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
