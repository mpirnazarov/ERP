package com.lgcns.erp.tapps.viewModel.Manager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muslimbek on 11/28/2016.
 */
public class Form {
    private String id;
    private String firstName;
    private String lastName;
    private String comments;
    private int grade;
    private Map<Integer, String> grades = null;

    public Form() {
        grades = new HashMap<Integer, String>();
        grades.put(1, "S");
        grades.put(2, "A");
        grades.put(3, "B");
        grades.put(4, "C");
        grades.put(5, "D");
    }

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Map<Integer, String> getGrades() {
        return grades;
    }

    public void setGrades(Map<Integer, String> grades) {
        this.grades = grades;
    }

}
