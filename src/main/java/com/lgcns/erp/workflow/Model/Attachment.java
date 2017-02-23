package com.lgcns.erp.workflow.Model;

/**
 * Created by DS on 03.02.2017.
 */
public class Attachment {
    private int id;
    private String url;
    private String fileName;

    public Attachment() {
    }

    public Attachment(int id, String url, String filename) {
        this.id = id;
        this.url = url;
        this.fileName = filename;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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
