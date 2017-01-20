package com.lgcns.erp.tapps.viewModel;

import java.util.List;

/**
 * Created by Muslimbek on 12/27/2016.
 */
public class PersonInfo implements Comparable{
    private String firstName, lastName, userName, id;
    private int employeeId, roleId, chiefId;
    private List<Integer> isChecked;
    private boolean enabled;
    public PersonInfo() {
    }

    public PersonInfo(String firstName, String lastName, int id) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.employeeId = id;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getChiefId() {
        return chiefId;
    }

    public void setChiefId(int chiefId) {
        this.chiefId = chiefId;
    }

    public List<Integer> getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(List<Integer> isChecked) {
        this.isChecked = isChecked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +

                ", employeeId=" + employeeId +
                ", chiefId=" + chiefId +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        PersonInfo p1 = (PersonInfo)o;
        return this.getChiefId() - p1.getChiefId();
    }
}
