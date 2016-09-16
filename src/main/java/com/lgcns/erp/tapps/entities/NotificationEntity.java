package com.lgcns.erp.tapps.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Rafatdin on 16.09.2016.
 */
@Entity
@javax.persistence.Table(name = "Notification", schema = "public", catalog = "ErpSystem")
public class NotificationEntity {
    private int id;

    @Id
    @javax.persistence.Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Timestamp date;

    @Basic
    @javax.persistence.Column(name = "Date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    private String message;

    @Basic
    @javax.persistence.Column(name = "MESSAGE", nullable = false, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private boolean isread;

    public boolean isread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }
}
