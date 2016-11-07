package com.lgcns.erp.tapps.viewModel;

import com.lgcns.erp.tapps.viewModel.usermenu.FamilyMember;

import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 24-Oct-16.
 */
public class ProfileViewModel {

    public ProfileViewModel(int id, String first_name, String last_name, String father_name, String address, int language_id) {
        this.id = id;
        this.firstName[language_id-1] = first_name;
        this.lastName[language_id-1] = last_name;
        this.fathersName[language_id-1] = father_name;
        this.address[language_id-1] = address;
        this.languageId = language_id;
    }

    public ProfileViewModel() {
        this.firstName = new String[3];
        this.lastName = new String[3];
        this.fathersName = new String[3];
        this.address = new String[3];
    }

    private int id;
    private String[] lastName;
    private String[] firstName;
    private String[] fathersName;
    private String[] address;
    private String department;
    private String position;
    private String jointType;
    private String status;
    private String jobTitle;
    private String passportNumber;
    private int languageId;
    private Date entryDate;
    private int vacationDaysAll;
    private int vacationDaysLeft;
    private PersonalInformationViewModel personalInfo;
    private List<FamilyMember> familyLoc;

    public List<FamilyMember> getFamilyLoc() {
        return familyLoc;
    }

    public void setFamilyLoc(List<FamilyMember> familyLoc) {
        this.familyLoc = familyLoc;
    }



    public String[] getLastName() {
        return lastName;
    }

    public void setLastName(String[] lastName) {
        this.lastName = lastName;
    }

    public String[] getFirstName() {
        return firstName;
    }

    public void setFirstName(String[] firstName) {
        this.firstName = firstName;
    }

    public String[] getFathersName() {
        return fathersName;
    }

    public void setFathersName(String[] fathersName) {
        this.fathersName = fathersName;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJointType() {
        return jointType;
    }

    public void setJointType(String jointType) {
        this.jointType = jointType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobTitle() {
        return jobTitle;
    }


    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addData1(int id, String first_name, String last_name, String father_name, String address, int language_id) {
        this.id = id;
        this.firstName[language_id-1] = first_name;
        this.lastName[language_id-1] = last_name;
        this.fathersName[language_id-1] = father_name;
        this.address[language_id-1] = address;
        this.languageId = language_id;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public int getVacationDaysAll() {
        return vacationDaysAll;
    }

    public void setVacationDaysAll(int vacationDaysAll) {
        this.vacationDaysAll = vacationDaysAll;
    }

    public int getVacationDaysLeft() {
        return vacationDaysLeft;
    }

    public void setVacationDaysLeft(int vacationDaysLeft) {
        this.vacationDaysLeft = vacationDaysLeft;
    }

    public void setPersonalInfo(PersonalInformationViewModel personalInfo) {
        this.personalInfo = personalInfo;
    }

    public PersonalInformationViewModel getPersonalInfo() {
        return personalInfo;
    }
}
