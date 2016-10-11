package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "sessions", schema = "public", catalog = "LgErpSystem")
public class SessionsEntity {
    private int id;
    private int sessionNumber;
    private int userId;
    private Date endTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "session_number", nullable = false)
    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
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
    @Column(name = "end_time", nullable = false)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionsEntity that = (SessionsEntity) o;

        if (id != that.id) return false;
        if (sessionNumber != that.sessionNumber) return false;
        if (userId != that.userId) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + sessionNumber;
        result = 31 * result + userId;
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }
}
