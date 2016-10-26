package com.lgcns.erp.tapps.viewModel;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.tools.ant.taskdefs.email.EmailAddress;
import java.util.Date;

/**
 * Created by Rafatdin on 03.10.2016.
 */
public class RegistrationViewModel {
    private String FirstName;
    private String LastName;
    private String FathersName;
    private String FirstNameRu;
    private String LastNameRu;
    private String FathersNameRu;
    private String FirstNameUz;
    private String LastNameUz;
    private String FathersNameUz;
    private Date DateOfBirth;
    private int DepartmentId;
    private String MobilePhone;
    private String HomePhone;
    private EmailAddress Email;
    private EmailAddress CompanyEmail;
    private String Address;
    private String AddressRu;
    private String AddressUz;
    private String UserName;
    private String Password;
    private String RepeatPassword;
    private int StatusId;
    private int ChiefId;
    private Date HiringDate;
    private boolean IsInPoliticalParty;

    @NotNull
    @Size(min=9, max=9)
    @Pattern(regexp = "^[A-Za-z0-9]*$")
    private String PassportNumber;



    public boolean isIsInPoliticalParty() {
        return IsInPoliticalParty;
    }

    public void setInPoliticalParty(boolean inPoliticalParty) {
        IsInPoliticalParty = inPoliticalParty;
    }

    public String getPassportNumber() {
        return PassportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        PassportNumber = passportNumber;
    }

    @NotNull
    public String getFirstNameRu() {
        return FirstNameRu;
    }

    public void setFirstNameRu(String firstNameRu) {
        FirstNameRu = firstNameRu;
    }

    public String getLastNameRu() {
        return LastNameRu;
    }

    public void setLastNameRu(String lastNameRu) {
        LastNameRu = lastNameRu;
    }

    public String getFathersNameRu() {
        return FathersNameRu;
    }

    public void setFathersNameRu(String fathersNameRu) {
        FathersNameRu = fathersNameRu;
    }

    public String getFirstNameUz() {
        return FirstNameUz;
    }

    public void setFirstNameUz(String firstNameUz) {
        FirstNameUz = firstNameUz;
    }

    public String getLastNameUz() {
        return LastNameUz;
    }

    public void setLastNameUz(String lastNameUz) {
        LastNameUz = lastNameUz;
    }

    public String getFathersNameUz() {
        return FathersNameUz;
    }

    public void setFathersNameUz(String fathersNameUz) {
        FathersNameUz = fathersNameUz;
    }

    public String getAddressRu() {
        return AddressRu;
    }

    public void setAddressRu(String addressRu) {
        AddressRu = addressRu;
    }

    public String getAddressUz() {
        return AddressUz;
    }

    public void setAddressUz(String addressUz) {
        AddressUz = addressUz;
    }


    public boolean isHasHead() {
        return HasHead;
    }

    public void setHasHead(boolean hasHead) {
        HasHead = hasHead;
    }

    private boolean HasHead;


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

    public EmailAddress getCompanyEmail() {return CompanyEmail;}

    public void setCompanyEmail(EmailAddress companyEmail) {CompanyEmail = companyEmail;}

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

    public String getRepeatPassword() {return RepeatPassword;}

    public void setRepeatPassword(String repeatPassword) {RepeatPassword = repeatPassword;}

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
