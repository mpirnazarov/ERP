package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "certificates", schema = "public", catalog = "LgErpSystem")
public class CertificatesEntity {
    private int id;
    private int userId;
    private Date dateTime;
    private String mark;
    private Boolean isPass;
    private Collection<CertificateLocalizationsEntity> certificateLocalizationsesById;
    private UsersEntity usersByUserId;

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
    @Column(name = "date_time", nullable = false)
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Basic
    @Column(name = "mark", nullable = true, length = 100)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "is_pass", nullable = true)
    public Boolean getPass() {
        return isPass;
    }

    public void setPass(Boolean pass) {
        isPass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CertificatesEntity that = (CertificatesEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;
        if (isPass != null ? !isPass.equals(that.isPass) : that.isPass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (isPass != null ? isPass.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "certificatesByCertificateId")
    public Collection<CertificateLocalizationsEntity> getCertificateLocalizationsesById() {
        return certificateLocalizationsesById;
    }

    public void setCertificateLocalizationsesById(Collection<CertificateLocalizationsEntity> certificateLocalizationsesById) {
        this.certificateLocalizationsesById = certificateLocalizationsesById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
