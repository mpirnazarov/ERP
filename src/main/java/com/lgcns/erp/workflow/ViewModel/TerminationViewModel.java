package com.lgcns.erp.workflow.ViewModel;

/**
 * Created by DS on 20.02.2017.
 */
public class TerminationViewModel {
   private int old_req_id;
   private int id;
   private String subject;
   private String description;
   private String executives;
   private String references;
   private String approves;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOld_req_id() {
        return old_req_id;
    }

    public void setOld_req_id(int old_req_id) {
        this.old_req_id = old_req_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getExecutives() {
        return executives;
    }

    public void setExecutives(String executives) {
        this.executives = executives;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getApproves() {
        return approves;
    }

    public void setApproves(String approves) {
        this.approves = approves;
    }
}
