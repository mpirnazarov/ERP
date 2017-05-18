package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "users", schema = "public", catalog = "LgErpSystem")
public class UsersEntity {
    private int id;
    private Date dateOfBirth;
    private Integer departmentId;
    private String mobilePhone;
    private String homePhone;
    private String eMail;
    private String userName;
    private Integer statusId;
    private String passwordHash;
    private Date hiringDate;
    private Integer chiefId;
    private String personalEmail;
    private boolean inPoliticalParty;
    private String passport;
    private boolean enabled;
    private Integer roleId;
    private Collection<CertificatesEntity> certificatesById;
    private Collection<DocumentsEntity> documentsesById;
    private Collection<EducationsEntity> educationsesById;
    private Collection<FamilyInfosEntity> familyInfosesById;
    private Collection<PersonalEvalutionsEntity> personalEvalutionsesById;
    private Collection<PostsEntity> postsesById;
    private Collection<SalaryHistoriesEntity> salaryHistoriesById;
    private Collection<TrainingsEntity> trainingsesById;
    private Collection<UserInLanguagesEntity> userInLanguagesById;
    private Collection<UserInProjectsEntity> userInProjectsesById;
    private Collection<UserLocalizationsEntity> userLocalizationsesById;
    private DepartmentsEntity departmentsByDepartmentId;
    private StatusesEntity statusesByStatusId;
    private UsersEntity usersByChiefId;
    private Collection<UsersEntity> usersesById;
    private Collection<VocationStatusEntity> vocationStatusesById;
    private Collection<WorkloadEntity> workloadsById;
    private Collection<WorksEntity> worksesById;
    private Boolean isPolitical;
    private Collection<UserInPostsEntity> userInPostsesById;
    private Collection<AuthTokenEntity> authTokensById;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "personal_email", nullable = true, length = 50)
    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    @Basic
    @Column(name = "is_political")
    public boolean isInPoliticalParty() {
        return inPoliticalParty;
    }

    public void setInPoliticalParty(boolean inPoliticalParty) {
        this.inPoliticalParty = inPoliticalParty;
    }

    @Basic
    @Column(name = "passport", nullable = true, length = 20)
    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Basic
    @Column(name = "date_of_birth", nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "department_id", nullable = false, insertable = false, updatable = false)
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "role_id", nullable = true)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "mobile_phone", nullable = true, length = 20)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "home_phone", nullable = true, length = 20)
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Basic
    @Column(name = "e_mail", nullable = false, length = 50)
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "status_id", nullable = false, insertable = false, updatable = false)
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "password_hash", nullable = false, length = 200)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Basic
    @Column(name = "hiring_date", nullable = false)
    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    @Basic
    @Column(name = "chief_id", nullable = true, insertable = false, updatable = false)
    public Integer getChiefId() {
        return chiefId;
    }

    public void setChiefId(Integer chiefId) {
        this.chiefId = chiefId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (departmentId != that.departmentId) return false;
        if (statusId != that.statusId) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (passwordHash != null ? !passwordHash.equals(that.passwordHash) : that.passwordHash != null) return false;
        if (hiringDate != null ? !hiringDate.equals(that.hiringDate) : that.hiringDate != null) return false;
        if (chiefId != null ? !chiefId.equals(that.chiefId) : that.chiefId != null) return false;
        if (personalEmail != null ? !personalEmail.equals(that.personalEmail) : that.personalEmail!=null) return false;
        if (passport != null ? !passport.equals(that.passport) : that.passport!=null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + departmentId;
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + statusId;
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (hiringDate != null ? hiringDate.hashCode() : 0);
        result = 31 * result + (chiefId != null ? chiefId.hashCode() : 0);
        result = 31 * result + (personalEmail != null ? personalEmail.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<CertificatesEntity> getCertificatesById() {
        return certificatesById;
    }

    public void setCertificatesById(Collection<CertificatesEntity> certificatesById) {
        this.certificatesById = certificatesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<DocumentsEntity> getDocumentsesById() {
        return documentsesById;
    }

    public void setDocumentsesById(Collection<DocumentsEntity> documentsesById) {
        this.documentsesById = documentsesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<EducationsEntity> getEducationsesById() {
        return educationsesById;
    }

    public void setEducationsesById(Collection<EducationsEntity> educationsesById) {
        this.educationsesById = educationsesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<FamilyInfosEntity> getFamilyInfosesById() {
        return familyInfosesById;
    }

    public void setFamilyInfosesById(Collection<FamilyInfosEntity> familyInfosesById) {
        this.familyInfosesById = familyInfosesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<PersonalEvalutionsEntity> getPersonalEvalutionsesById() {
        return personalEvalutionsesById;
    }

    public void setPersonalEvalutionsesById(Collection<PersonalEvalutionsEntity> personalEvalutionsesById) {
        this.personalEvalutionsesById = personalEvalutionsesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<SalaryHistoriesEntity> getSalaryHistoriesById() {
        return salaryHistoriesById;
    }

    public void setSalaryHistoriesById(Collection<SalaryHistoriesEntity> salaryHistoriesById) {
        this.salaryHistoriesById = salaryHistoriesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<TrainingsEntity> getTrainingsesById() {
        return trainingsesById;
    }

    public void setTrainingsesById(Collection<TrainingsEntity> trainingsesById) {
        this.trainingsesById = trainingsesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<UserInLanguagesEntity> getUserInLanguagesById() {
        return userInLanguagesById;
    }

    public void setUserInLanguagesById(Collection<UserInLanguagesEntity> userInLanguagesById) {
        this.userInLanguagesById = userInLanguagesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<UserInProjectsEntity> getUserInProjectsesById() {
        return userInProjectsesById;
    }

    public void setUserInProjectsesById(Collection<UserInProjectsEntity> userInProjectsesById) {
        this.userInProjectsesById = userInProjectsesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<UserLocalizationsEntity> getUserLocalizationsesById() {
        return userLocalizationsesById;
    }

    public void setUserLocalizationsesById(Collection<UserLocalizationsEntity> userLocalizationsesById) {
        this.userLocalizationsesById = userLocalizationsesById;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    public DepartmentsEntity getDepartmentsByDepartmentId() {
        return departmentsByDepartmentId;
    }

    public void setDepartmentsByDepartmentId(DepartmentsEntity departmentsByDepartmentId) {
        this.departmentsByDepartmentId = departmentsByDepartmentId;
    }

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public StatusesEntity getStatusesByStatusId() {
        return statusesByStatusId;
    }

    public void setStatusesByStatusId(StatusesEntity statusesByStatusId) {
        this.statusesByStatusId = statusesByStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "chief_id", referencedColumnName = "id")
    public UsersEntity getUsersByChiefId() {
        return usersByChiefId;
    }

    public void setUsersByChiefId(UsersEntity usersByChiefId) {
        this.usersByChiefId = usersByChiefId;
    }

    @OneToMany(mappedBy = "usersByChiefId")
    public Collection<UsersEntity> getUsersesById() {
        return usersesById;
    }

    public void setUsersesById(Collection<UsersEntity> usersesById) {
        this.usersesById = usersesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<VocationStatusEntity> getVocationStatusesById() {
        return vocationStatusesById;
    }

    public void setVocationStatusesById(Collection<VocationStatusEntity> vocationStatusesById) {
        this.vocationStatusesById = vocationStatusesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<WorkloadEntity> getWorkloadsById() {
        return workloadsById;
    }

    public void setWorkloadsById(Collection<WorkloadEntity> workloadsById) {
        this.workloadsById = workloadsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<WorksEntity> getWorksesById() {
        return worksesById;
    }

    public void setWorksesById(Collection<WorksEntity> worksesById) {
        this.worksesById = worksesById;
    }

    @Basic
    @Column(name = "is_political", nullable = true, length = 50, insertable=false, updatable=false)
    public Boolean getPolitical() {
        return isPolitical;
    }

    public void setPolitical(Boolean political) {
        isPolitical = political;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<UserInPostsEntity> getUserInPostsesById() {
        return userInPostsesById;
    }

    public void setUserInPostsesById(Collection<UserInPostsEntity> userInPostsesById) {
        this.userInPostsesById = userInPostsesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<AuthTokenEntity> getAuthTokensById() {
        return authTokensById;
    }

    public void setAuthTokensById(Collection<AuthTokenEntity> authTokensById) {
        this.authTokensById = authTokensById;
    }
}
