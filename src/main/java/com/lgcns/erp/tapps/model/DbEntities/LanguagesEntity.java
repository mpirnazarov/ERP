package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "languages", schema = "public", catalog = "LgErpSystem")
public class LanguagesEntity {
    private int id;
    private Collection<CertificateLocalizationsEntity> certificateLocalizationsesById;
    private Collection<CurrnecyLocalizationsEntity> currnecyLocalizationsesById;
    private Collection<DepartmentLocalizationsEntity> departmentLocalizationsesById;
    private Collection<EducationLocalizationsEntity> educationLocalizationsesById;
    private Collection<FamiliyInfoLocalizationsEntity> familiyInfoLocalizationsesById;
    private Collection<LanguageLocalizationsEntity> languageLocalizationsesById;
    private Collection<PermissionLocalizationsEntity> permissionLocalizationsesById;
    private Collection<RoleLocalizationsEntity> roleLocalizationsesById;
    private Collection<StatusLocalizationsEntity> statusLocalizationsesById;
    private Collection<TrainingLocalizationsEntity> trainingLocalizationsesById;
    private Collection<UserInLanguagesEntity> userInLanguagesById;
    private Collection<UserLocalizationsEntity> userLocalizationsesById;
    private Collection<WorkLocalizationsEntity> workLocalizationsesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguagesEntity that = (LanguagesEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<CertificateLocalizationsEntity> getCertificateLocalizationsesById() {
        return certificateLocalizationsesById;
    }

    public void setCertificateLocalizationsesById(Collection<CertificateLocalizationsEntity> certificateLocalizationsesById) {
        this.certificateLocalizationsesById = certificateLocalizationsesById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<CurrnecyLocalizationsEntity> getCurrnecyLocalizationsesById() {
        return currnecyLocalizationsesById;
    }

    public void setCurrnecyLocalizationsesById(Collection<CurrnecyLocalizationsEntity> currnecyLocalizationsesById) {
        this.currnecyLocalizationsesById = currnecyLocalizationsesById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<DepartmentLocalizationsEntity> getDepartmentLocalizationsesById() {
        return departmentLocalizationsesById;
    }

    public void setDepartmentLocalizationsesById(Collection<DepartmentLocalizationsEntity> departmentLocalizationsesById) {
        this.departmentLocalizationsesById = departmentLocalizationsesById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<EducationLocalizationsEntity> getEducationLocalizationsesById() {
        return educationLocalizationsesById;
    }

    public void setEducationLocalizationsesById(Collection<EducationLocalizationsEntity> educationLocalizationsesById) {
        this.educationLocalizationsesById = educationLocalizationsesById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<FamiliyInfoLocalizationsEntity> getFamiliyInfoLocalizationsesById() {
        return familiyInfoLocalizationsesById;
    }

    public void setFamiliyInfoLocalizationsesById(Collection<FamiliyInfoLocalizationsEntity> familiyInfoLocalizationsesById) {
        this.familiyInfoLocalizationsesById = familiyInfoLocalizationsesById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<LanguageLocalizationsEntity> getLanguageLocalizationsesById() {
        return languageLocalizationsesById;
    }

    public void setLanguageLocalizationsesById(Collection<LanguageLocalizationsEntity> languageLocalizationsesById) {
        this.languageLocalizationsesById = languageLocalizationsesById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<PermissionLocalizationsEntity> getPermissionLocalizationsesById() {
        return permissionLocalizationsesById;
    }

    public void setPermissionLocalizationsesById(Collection<PermissionLocalizationsEntity> permissionLocalizationsesById) {
        this.permissionLocalizationsesById = permissionLocalizationsesById;
    }


    @OneToMany(mappedBy = "languagesByLenguageId")
    public Collection<RoleLocalizationsEntity> getRoleLocalizationsesById() {
        return roleLocalizationsesById;
    }

    public void setRoleLocalizationsesById(Collection<RoleLocalizationsEntity> roleLocalizationsesById) {
        this.roleLocalizationsesById = roleLocalizationsesById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<StatusLocalizationsEntity> getStatusLocalizationsesById() {
        return statusLocalizationsesById;
    }

    public void setStatusLocalizationsesById(Collection<StatusLocalizationsEntity> statusLocalizationsesById) {
        this.statusLocalizationsesById = statusLocalizationsesById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<TrainingLocalizationsEntity> getTrainingLocalizationsesById() {
        return trainingLocalizationsesById;
    }

    public void setTrainingLocalizationsesById(Collection<TrainingLocalizationsEntity> trainingLocalizationsesById) {
        this.trainingLocalizationsesById = trainingLocalizationsesById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<UserInLanguagesEntity> getUserInLanguagesById() {
        return userInLanguagesById;
    }

    public void setUserInLanguagesById(Collection<UserInLanguagesEntity> userInLanguagesById) {
        this.userInLanguagesById = userInLanguagesById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<UserLocalizationsEntity> getUserLocalizationsesById() {
        return userLocalizationsesById;
    }

    public void setUserLocalizationsesById(Collection<UserLocalizationsEntity> userLocalizationsesById) {
        this.userLocalizationsesById = userLocalizationsesById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<WorkLocalizationsEntity> getWorkLocalizationsesById() {
        return workLocalizationsesById;
    }

    public void setWorkLocalizationsesById(Collection<WorkLocalizationsEntity> workLocalizationsesById) {
        this.workLocalizationsesById = workLocalizationsesById;
    }
}
