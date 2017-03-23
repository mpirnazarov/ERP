package com.lgcns.erp.tapps.viewModel;

import com.lgcns.erp.tapps.viewModel.usermenu.FamilyMember;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 24-Oct-16.
 */
public class ProfileViewModel {

    public ProfileViewModel(String id, String first_name, String last_name, String father_name, String address, int language_id) {
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

    private String id;
    private String[] lastName;
    private String[] firstName;
    private String[] fathersName;
    private String[] address;
    private String department;
    private String position;
    private String jointType;
    private String status;
    private String jobTitle, external;
    private String passportNumber;
    private int roleId;
    private int languageId;
    private boolean enabled;
    private int departmentId, positionId, jobTitleId, postId, statusId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;
    private int vacationDaysAll;
    private float vacationDaysLeft;
    private boolean isPolitical;
    private String username;
    private PersonalInformationViewModel personalInfo;
    private List<FamilyMember> familyLoc;
    private int chiefId;
    private int externalId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addData1(String id, String first_name, String last_name, String father_name, String address, int language_id) {
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

    public float getVacationDaysLeft() {
        return vacationDaysLeft;
    }

    public void setVacationDaysLeft(float vacationDaysLeft) {
        this.vacationDaysLeft = vacationDaysLeft;
    }

    public void setPersonalInfo(PersonalInformationViewModel personalInfo) {
        this.personalInfo = personalInfo;
    }

    public PersonalInformationViewModel getPersonalInfo() {
        return personalInfo;
    }

    public boolean getIsPolitical() {
        return isPolitical;
    }

    public void setPolitical(boolean political) {
        isPolitical = political;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ProfileViewModel{" +
                "id='" + id + '\'' +
                ", lastName=" + Arrays.toString(lastName) +
                ", firstName=" + Arrays.toString(firstName) +
                ", fathersName=" + Arrays.toString(fathersName) +
                ", address=" + Arrays.toString(address) +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", jointType='" + jointType + '\'' +
                ", status='" + status + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", languageId=" + languageId +
                ", entryDate=" + entryDate +
                ", vacationDaysAll=" + vacationDaysAll +
                ", vacationDaysLeft=" + vacationDaysLeft +
                ", isPolitical='" + isPolitical + '\'' +
                ", username='" + username + '\'' +
                ", personalInfo=" + personalInfo +
                ", familyLoc=" + familyLoc +
                '}';
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getJobTitleId() {
        return jobTitleId;
    }

    public void setJobTitleId(int jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getChiefId() {
        return chiefId;
    }

    public void setChiefId(int chiefId) {
        this.chiefId = chiefId;
    }

    public int getExternalId() {
        return externalId;
    }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }
}
