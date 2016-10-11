package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "trainings", schema = "public", catalog = "LgErpSystem")
public class TrainingsEntity {
    private int id;
    private int userId;
    private Integer certificateId;
    private Date dateFrom;
    private Date dateTo;
    private int numberOfHours;
    private String mark;
    private Collection<TrainingLocalizationsEntity> trainingLocalizationsesById;
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
    @Column(name = "certificate_id", nullable = true, insertable = false, updatable = false)
    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    @Basic
    @Column(name = "date_from", nullable = false)
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Basic
    @Column(name = "date_to", nullable = true)
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Basic
    @Column(name = "number_of_hours", nullable = false)
    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    @Basic
    @Column(name = "mark", nullable = true, length = 100)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainingsEntity that = (TrainingsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (numberOfHours != that.numberOfHours) return false;
        if (certificateId != null ? !certificateId.equals(that.certificateId) : that.certificateId != null)
            return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(that.dateTo) : that.dateTo != null) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (certificateId != null ? certificateId.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + numberOfHours;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "trainingsByTrainingId")
    public Collection<TrainingLocalizationsEntity> getTrainingLocalizationsesById() {
        return trainingLocalizationsesById;
    }

    public void setTrainingLocalizationsesById(Collection<TrainingLocalizationsEntity> trainingLocalizationsesById) {
        this.trainingLocalizationsesById = trainingLocalizationsesById;
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
