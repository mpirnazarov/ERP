package com.lgcns.erp.workflow.ViewModel;

import com.lgcns.erp.workflow.Model.Attachment;
import com.lgcns.erp.workflow.Model.StepComment;

import java.util.Date;
import java.util.List;

/**
 * Created by DS on 01.02.2017.
 */
public class DetailsViewModel {
    private int request_id;
    private String form_type;
    private int form_type_id;
    private String request_subject;
    private String request_description;
    private Date date_created;
    private int user_id;
    private String user_name;
    private String status;
    private int status_id;
    private String approvals;
    private String executives;
    private String references;
    private List<Attachment> attachments;
    private List<StepComment> comments;

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public List<StepComment> getComments() {
        return comments;
    }

    public void setComments(List<StepComment> comments) {
        this.comments = comments;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getApprovals() {
        return approvals;
    }

    public void setApprovals(String approvals) {
        this.approvals = approvals;
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

    public int getForm_type_id() {
        return form_type_id;
    }

    public void setForm_type_id(int form_type_id) {
        this.form_type_id = form_type_id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getForm_type() {
        return form_type;
    }

    public void setForm_type(String form_type) {
        this.form_type = form_type;
    }

    public String getRequest_subject() {
        return request_subject;
    }

    public void setRequest_subject(String request_subject) {
        this.request_subject = request_subject;
    }

    public String getRequest_description() {
        return request_description;
    }

    public void setRequest_description(String request_description) {
        this.request_description = request_description;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
