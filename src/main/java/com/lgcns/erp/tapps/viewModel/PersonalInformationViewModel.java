package com.lgcns.erp.tapps.viewModel;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Muslimbek on 11/4/2016.
 */
public class PersonalInformationViewModel {
    private String[] birthPlace;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String homePhone;
    private String mobilePhone;

    private String emailCompany;
    private String emailPersonal;
    int i=0;

    public PersonalInformationViewModel() {
        this.birthPlace = new String[3];
        i=0;
    }

    public void addBirthPlace(String birthPlace, int languageId) {
        this.birthPlace[languageId-1] = birthPlace;

    }

    public String[] getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String[] birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmailCompany() {
        return emailCompany;
    }

    public void setEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }
}
