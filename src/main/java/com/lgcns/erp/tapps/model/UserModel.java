package com.lgcns.erp.tapps.model;

/**
 * Created by DS on 11/24/2016.
 */
public class UserModel {
    private int ID;
    private String fullName;

    public UserModel() {
    }

    public UserModel(int id, String fullName) {
        this.ID = id;
        this.fullName = fullName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
