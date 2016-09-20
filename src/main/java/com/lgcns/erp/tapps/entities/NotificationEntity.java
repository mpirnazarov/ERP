package com.lgcns.erp.tapps.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Rafatdin on 19.09.2016.
 */
@Entity
@Table(name = "Notification", schema = "public", catalog = "ErpSystem")
public class NotificationEntity {
    private int id;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Timestamp date;

    @Basic
    @Column(name = "Date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    private String message;

    @Basic
    @Column(name = "MESSAGE", nullable = false, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    private boolean isread;

    @Basic
    @Column(name = "ISREAD", nullable = false)
    public boolean getIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }


    private Integer fromId;
    @Basic
    @Column(name = "FROMID", nullable = true)
    public Integer getFromId() {return fromId;}

    public void setFromId(Integer fromId) {this.fromId = fromId;}


    private Integer toId;
    @Basic
    @Column(name = "TOID", nullable = false)
    public Integer getToId() {return toId;}

    public void setToId(Integer toId) {this.toId = toId;}

    private Integer ProjectId;
    @Basic
    @Column(name = "ProjectId", nullable = false)
    public Integer getProjectId() {return ProjectId;}

    public void setProjectId(Integer ProjectId) {this.ProjectId = ProjectId;}


    private Integer TypeId;
    @Basic
    @Column(name = "TYPEID", nullable = false)
    public Integer getTypeId() {return TypeId;}

    public void setTypeId(Integer TypeId) {this.TypeId = TypeId;}

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationEntity that = (NotificationEntity) o;

        if (id != that.id) return false;
        if (isread != that.isread) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (fromId != null ? !fromId.equals(that.fromId) : that.fromId != null) return false;
        if (toId != null ? !toId.equals(that.toId) : that.toId != null) return false;
        if (TypeId != null ? !TypeId.equals(that.TypeId) : that.TypeId != null) return false;
        if (ProjectId != null ? !ProjectId.equals(that.ProjectId) : that.ProjectId != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (contractUsd != null ? contractUsd.hashCode() : 0);
        result = 31 * result + (contractUzs != null ? contractUzs.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (cType != null ? cType.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        return result;
    }*/
}
