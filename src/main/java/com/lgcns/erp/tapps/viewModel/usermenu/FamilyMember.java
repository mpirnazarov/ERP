package com.lgcns.erp.tapps.viewModel.usermenu;

import java.sql.Date;

/**
 * Created by Muslimbek on 10/31/2016.
 */
public class FamilyMember {
    private String[] relation;
    private String[] fullName;
    private Date dateOfBirth;
    private String[] jobTitle;


    private int id;

     public FamilyMember(int num) {
        this.relation = new String[num];
        this.fullName = new String[num];
        this.jobTitle = new String[num];
    }

    public void add(String relation, String fullName, Date dateOfBirth, String jobTitle, int languageId, int familyInfoid) {
        this.relation[languageId-1] = relation;
        this.fullName[languageId-1] = fullName;
        this.dateOfBirth = dateOfBirth;
        this.jobTitle[languageId-1] = jobTitle;
        this.id = familyInfoid;
    }

    public String[] getFullName() {
        return fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public String[] getJobTitle() {
        return jobTitle;
    }
    public String[] getRelation() {
        return relation;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
