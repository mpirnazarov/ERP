package com.lgcns.erp.workflow.ViewModel;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

/**
 * Created by Sarvar on 13.02.2017.
 */
public class UnformattedVM {

    private int id;
    private String Title;
    private String description;
    private Date start, end;
    private MultipartFile[] file;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
