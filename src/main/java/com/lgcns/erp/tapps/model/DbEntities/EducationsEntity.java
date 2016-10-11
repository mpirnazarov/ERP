package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "educations", schema = "public", catalog = "LgErpSystem")
public class EducationsEntity {
    private int id;
    private int userId;
    private Date startDate;
    private Date endDate;
    private Collection<EducationLocalizationsEntity> educationLocalizationsesById;
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
    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EducationsEntity that = (EducationsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "educationsByEducationId")
    public Collection<EducationLocalizationsEntity> getEducationLocalizationsesById() {
        return educationLocalizationsesById;
    }

    public void setEducationLocalizationsesById(Collection<EducationLocalizationsEntity> educationLocalizationsesById) {
        this.educationLocalizationsesById = educationLocalizationsesById;
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
