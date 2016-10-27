package com.lgcns.erp.tapps.viewModel;


import javax.validation.constraints.NotNull;

import com.lgcns.erp.tapps.DbContext.UserService;
import org.apache.tools.ant.taskdefs.email.EmailAddress;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

/**
 * Created by Rafatdin on 03.10.2016.
 */
public class RegistrationViewModel {

    public RegistrationViewModel(){
        new RegistrationViewModel(UserService.getLanguageIdAndName());
    }

    public RegistrationViewModel(Map<Integer, String> langIdAndName){
        RegistrationLocInfos = new ArrayList<RegistrationLocInfo>();
        RegistrationLocInfo temp = null;

        for (Map.Entry<Integer, String> language : langIdAndName.entrySet()){
            temp = new RegistrationLocInfo();
            temp.setLanguageId(language.getKey());
            temp.setLanguageCode(language.getValue());
            RegistrationLocInfos.add(temp);
        }
    }


    private List<RegistrationLocInfo> RegistrationLocInfos;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DateOfBirth;
    private int DepartmentId;
    private String MobilePhone;
    private String HomePhone;
    private EmailAddress Email;
    private EmailAddress CompanyEmail;
    private String UserName;
    private String Password;
    private String RepeatPassword;
    private int StatusId;
    private int ChiefId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date HiringDate;
    private boolean IsInPoliticalParty;
    private String PassportNumber;


    public List<RegistrationLocInfo> getRegistrationLocInfos() {
        return RegistrationLocInfos;
    }

    public void setRegistrationLocInfos(List<RegistrationLocInfo> registrationLocInfos) {
        RegistrationLocInfos = registrationLocInfos;
    }
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

    public boolean isHasHead() {
        return HasHead;
    }

    public void setHasHead(boolean hasHead) {
        HasHead = hasHead;
    }

    private boolean HasHead;

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

    @NotNull(message = "Personal Email is required")
    public EmailAddress getEmail() {
        return Email;
    }

    public void setEmail(EmailAddress email) {
        Email = email;
    }

    public EmailAddress getCompanyEmail() {return CompanyEmail;}

    public void setCompanyEmail(EmailAddress companyEmail) {CompanyEmail = companyEmail;}

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
