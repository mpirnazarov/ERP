package com.lgcns.erp.tapps.model;

//import com.sun.istack.internal.Nullable;

/**
 * Created by Rafatdin on 15.09.2016.
 */
public class UserInfo {
    private int ID;
    private String FirstName;
    private String LastName;
    private String Phone;
    private String Email;
    private int PositionID;
    private String Username;
    private String Password;
    private String Address;
    private String Major;
    private Integer DirectHead; //using Integer instead of int, because first Integer is nullable class

    public UserInfo(int i, String s) {
        this.ID = i;
        this.FirstName = s;
    }

    public UserInfo() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPositionID() {
        return PositionID;
    }

    public void setPositionID(int positionID) {
        PositionID = positionID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public Integer getDirectHead() {
        return DirectHead;
    }

    public void setDirectHead(Integer directHead) {
        DirectHead = directHead;
    }

}
