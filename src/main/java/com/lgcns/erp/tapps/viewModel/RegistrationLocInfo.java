package com.lgcns.erp.tapps.viewModel;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class RegistrationLocInfo{
    private Integer LanguageId;
    private String LanguageCode;

    @NotEmpty
    private String FirstName;

    @NotEmpty
    private String LastName;
    private String FathersName;

    @NotEmpty
    private String Address;
    private String BirthPlace;

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public Integer getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(Integer languageId) {
        LanguageId = languageId;
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

    public String getFathersName() {
        return FathersName;
    }

    public void setFathersName(String fathersName) {
        FathersName = fathersName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBirthPlace() {
        return BirthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        BirthPlace = birthPlace;
    }
}
