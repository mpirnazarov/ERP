package com.lgcns.erp.tapps.viewModel.usermenu;

import com.lgcns.erp.tapps.model.DbEntities.FamiliyInfoLocalizationsEntity;

import java.sql.Date;

/**
 * Created by Muslimbek on 10/31/2016.
 */
public class FamilyMember extends FamiliyInfoLocalizationsEntity {
    private String relation;
    private String fullName;
    private java.util.Date dateOfBirth;
    private String jobTitle;

    public FamilyMember(String relation, String s, Date dateOfBirth, String jobTitle) {
        this.relation = relation;
        this.fullName = s;
        this.dateOfBirth = dateOfBirth;
        this.jobTitle = jobTitle;
    }

    @Override
    public String getRelation() {
        return relation;
    }

    @Override
    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public java.util.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.util.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    @Override
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
