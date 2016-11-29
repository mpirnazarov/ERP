package com.lgcns.erp.tapps.viewModel.usermenu;

import java.sql.Date;
import java.util.Arrays;

/**
 * Created by Muslimbek on 10/31/2016.
 */
public class FamilyMember {
    private String[] relation;
    private String[] lastName;
    private String[] firstName;
    private Date dateOfBirth;
    private String[] jobTitle;
    private int id;

    public FamilyMember() {
        this.relation = new String[3];
        this.lastName = new String[3];
        this.firstName = new String[3];
        this.jobTitle = new String[3];
    }

    public FamilyMember(int num) {
        this.relation = new String[num];
        this.lastName = new String[3];
        this.firstName = new String[3];
        this.jobTitle = new String[num];
    }

    public void add(String relation, String lastName, String firstName, Date dateOfBirth, String jobTitle, int languageId, int familyInfoid) {
        this.relation[languageId-1] = relation;
        this.lastName[languageId-1] = lastName;
        this.firstName[languageId-1] = firstName;
        this.dateOfBirth = dateOfBirth;
        this.jobTitle[languageId-1] = jobTitle;
        this.id = familyInfoid;
    }

    public String[] getLastName() {
        return lastName;
    }
    public String[] getFirstName() {
        return firstName;
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

    public void setRelation(String[] relation) {
        this.relation = relation;
    }

    public void setLastName(String[] lastName) {
        this.lastName = lastName;
    }
    public void setFirstName(String[] firstName) {
        this.firstName = firstName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setJobTitle(String[] jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "FamilyMember{" +
                "relation=" + Arrays.toString(relation) +
                ", lastName=" + Arrays.toString(lastName) +
                ", firstName=" + Arrays.toString(firstName) +
                ", dateOfBirth=" + dateOfBirth +
                ", jobTitle=" + Arrays.toString(jobTitle) +
                ", id=" + id +
                '}';
    }
}
