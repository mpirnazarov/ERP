package com.lgcns.erp.workflow.DBEntities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Muslimbek Pirnazarov on 1/23/2017.
 */
@Entity
@Table(name = "to_do", schema = "workflow", catalog = "LgErpSystem")
public class ToDoEntity {
    private int id;
    private int requestId;
    private Date date;
    private String description;
    private RequestsEntity requestsByRequestId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "request_id")
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDoEntity that = (ToDoEntity) o;

        if (id != that.id) return false;
        if (requestId != that.requestId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + requestId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public RequestsEntity getRequestsByRequestId() {
        return requestsByRequestId;
    }

    public void setRequestsByRequestId(RequestsEntity requestsByRequestId) {
        this.requestsByRequestId = requestsByRequestId;
    }

    @Override
    public String toString() {
        return "ToDoEntity{" +
                "id=" + id +
                ", requestId=" + requestId +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", requestsByRequestId=" + requestsByRequestId +
                '}';
    }
}
