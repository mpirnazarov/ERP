package com.lgcns.erp.tapps.viewModel.usermenu;

/**
 * Created by Dell on 04-Nov-16.
 */
public class DocsViewModel {
    private int docId;
    private String name;
    private String type;


    public DocsViewModel(int docId, String name, String type) {
        this.docId = docId;
        this.name = name;
        this.type = type;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
