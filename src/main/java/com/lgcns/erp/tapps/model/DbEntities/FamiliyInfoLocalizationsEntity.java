package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "familiy_info_localizations", schema = "public", catalog = "LgErpSystem")
public class FamiliyInfoLocalizationsEntity {
    private int id;
    private String lastName;
    private String firstName;
    private String relation;
    private int familyInfoid;
    private int languageId;
    private FamilyInfosEntity familyInfosByFamilyInfoid;
    private LanguagesEntity languagesByLanguageId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "relation", nullable = false, length = 50)
    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Basic
    @Column(name = "family_infoid", nullable = false, insertable = false, updatable = false)
    public int getFamilyInfoid() {
        return familyInfoid;
    }

    public void setFamilyInfoid(int familyInfoid) {
        this.familyInfoid = familyInfoid;
    }

    @Basic
    @Column(name = "language_id", nullable = false, insertable = false, updatable = false)
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FamiliyInfoLocalizationsEntity that = (FamiliyInfoLocalizationsEntity) o;

        if (id != that.id) return false;
        if (familyInfoid != that.familyInfoid) return false;
        if (languageId != that.languageId) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (relation != null ? !relation.equals(that.relation) : that.relation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (relation != null ? relation.hashCode() : 0);
        result = 31 * result + familyInfoid;
        result = 31 * result + languageId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "family_infoid", referencedColumnName = "id", nullable = false)
    public FamilyInfosEntity getFamilyInfosByFamilyInfoid() {
        return familyInfosByFamilyInfoid;
    }

    public void setFamilyInfosByFamilyInfoid(FamilyInfosEntity familyInfosByFamilyInfoid) {
        this.familyInfosByFamilyInfoid = familyInfosByFamilyInfoid;
    }

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = false)
    public LanguagesEntity getLanguagesByLanguageId() {
        return languagesByLanguageId;
    }

    public void setLanguagesByLanguageId(LanguagesEntity languagesByLanguageId) {
        this.languagesByLanguageId = languagesByLanguageId;
    }
}
