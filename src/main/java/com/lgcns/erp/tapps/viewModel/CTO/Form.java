package com.lgcns.erp.tapps.viewModel.CTO;

/**
 * Created by Muslimbek on 11/28/2016.
 */
public class Form {
    private String id;
    private String firstName;
    private String lastName;
    private String comments;
    private String grade;
    private String[] grades = {"S", "A", "B", "C", "D"};
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String[] getGrades() {
        return grades;
    }

    public void setGrades(String[] grades) {
        this.grades = grades;
    }

}
