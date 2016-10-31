package com.lgcns.erp.tapps.viewModel;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.lgcns.erp.tapps.DbContext.UserService;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
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

    @Valid
    private List<RegistrationLocInfo> RegistrationLocInfos;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DateOfBirth;
    private int DepartmentId;

    @Pattern(regexp="^\\(?(\\d{5})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$|",message="Phone number should be of (99893)123-4567 format")
    private String MobilePhone;
    private String HomePhone;

    @NotEmpty
    @Email
    private String Email;

    @NotEmpty
    @Email
    private String CompanyEmail;
    private String UserName;

    @NotEmpty
    private String Password;

    @NotEmpty
    private String RepeatPassword;

    private int StatusId;
    private int ChiefId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date HiringDate;
    private boolean IsInPoliticalParty;
    private String PassportNumber;
    private Integer RoleId;


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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCompanyEmail() {return CompanyEmail;}

    public void setCompanyEmail(String companyEmail) {CompanyEmail = companyEmail;}

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

    public Integer getRoleId() {
        return RoleId;
    }

    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }
}
