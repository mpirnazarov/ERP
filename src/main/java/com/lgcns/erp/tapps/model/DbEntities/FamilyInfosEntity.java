package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "family_infos", schema = "public", catalog = "LgErpSystem")
public class FamilyInfosEntity {
    private int id;
    private int userId;
    private String passport;
    private Collection<FamiliyInfoLocalizationsEntity> familiyInfoLocalizationsesById;
    private UsersEntity usersByUserId;
    private Date dateOfBirth;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "passport", nullable = true, length = 20)
    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FamilyInfosEntity that = (FamilyInfosEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (passport != null ? !passport.equals(that.passport) : that.passport != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "familyInfosByFamilyInfoid")
    public Collection<FamiliyInfoLocalizationsEntity> getFamiliyInfoLocalizationsesById() {
        return familiyInfoLocalizationsesById;
    }

    public void setFamiliyInfoLocalizationsesById(Collection<FamiliyInfoLocalizationsEntity> familiyInfoLocalizationsesById) {
        this.familiyInfoLocalizationsesById = familiyInfoLocalizationsesById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @Basic
    @Column(name = "date_of_birth", nullable = true)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
