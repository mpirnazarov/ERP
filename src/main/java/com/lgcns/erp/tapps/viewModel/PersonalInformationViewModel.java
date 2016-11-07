package com.lgcns.erp.tapps.viewModel;

import com.lgcns.erp.tapps.viewModel.usermenu.personalInfo.BirthPlace;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Muslimbek on 11/4/2016.
 */
public class PersonalInformationViewModel {
    private List<BirthPlace> birthPlace;
    private Date dateOfBirth;
    private String homePhone;
    private String mobilePhone;
    private String emailCompany;
    private String emailPersonal;

    public PersonalInformationViewModel() {
        this.birthPlace = new LinkedList<BirthPlace>();
    }

    public void addBirthPlace(String birthPlace, int languageId) {
        this.birthPlace.add(new BirthPlace(birthPlace, languageId));
    }

    public List<BirthPlace> getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(List<BirthPlace> birthPlace) {
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
