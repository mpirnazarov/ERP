package com.lgcns.erp.workflow.Model;

/**
 * Created by DS on 03.02.2017.
 */
public class Attachment {
    private Long id;
    private String url;
    private String fileName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
