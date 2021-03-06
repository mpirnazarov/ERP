package com.lgcns.erp.workflow.Model;

/**
 * Created by DS on 15.02.2017.
 */
public class Member implements Comparable<Member>{
    private int id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String department;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " | " + department + ", " + jobTitle ;
    }

    @Override
    public int compareTo(Member o) {
        int comparedId = o.getId();
        if(this.getId() > comparedId)
            return 1;
        else if(this.getId() == comparedId)
            return 0;
        else
            return -1;
    }
}
