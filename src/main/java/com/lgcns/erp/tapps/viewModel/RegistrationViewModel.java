package com.lgcns.erp.tapps.viewModel;

import javax.xml.registry.infomodel.EmailAddress;
import java.util.Date;

/**
 * Created by Rafatdin on 03.10.2016.
 */
public class RegistrationViewModel {
    private String FirstName;
    private String LastName;
    private String FathersName;
    private Date DateOfBirth;
    private int DepartmentId;
    private String MobilePhone;
    private String HomePhone;
    private EmailAddress Email;
    private String Address;
    private String UserName;
    private String Password;
    private int StatusId;
    private int ChiefId;
    private Date HiringDate;

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

    public String getFathersName() {
        return FathersName;
    }

    public void setFathersName(String fathersName) {
        FathersName = fathersName;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        DepartmentId = departmentId;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return HomePhone;
    }

    public void setHomePhone(String homePhone) {
        HomePhone = homePhone;
    }

    public EmailAddress getEmail() {
        return Email;
    }

    public void setEmail(EmailAddress email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public int getChiefId() {
        return ChiefId;
    }

    public void setChiefId(int chiefId) {
        ChiefId = chiefId;
    }

    public Date getHiringDate() {
        return HiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        HiringDate = hiringDate;
    }


}
