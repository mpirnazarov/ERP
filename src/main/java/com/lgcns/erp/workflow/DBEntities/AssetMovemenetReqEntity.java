package com.lgcns.erp.workflow.DBEntities;

import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;

import javax.persistence.*;

@Entity
@Table(name = "asset_movemenet_req", schema = "workflow", catalog = "LgErpSystem")
public class AssetMovemenetReqEntity {
    private int id;
    private int inventNum;
    private int userFromOld;
    private int userToNew;
    private int requestId;
    private UsersEntity usersByUserFromOld;
    private UsersEntity usersByUserToNew;
    private RequestsEntity requestsByRequestId;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "invent_num")
    public int getInventNum() {
        return inventNum;
    }

    public void setInventNum(int inventNum) {
        this.inventNum = inventNum;
    }

    @Basic
    @Column(name = "user_from_old")
    public int getUserFromOld() {
        return userFromOld;
    }

    public void setUserFromOld(int userFromOld) {
        this.userFromOld = userFromOld;
    }

    @Basic
    @Column(name = "user_to_new")
    public int getUserToNew() {
        return userToNew;
    }

    public void setUserToNew(int userToNew) {
        this.userToNew = userToNew;
    }

    @Basic
    @Column(name = "request_id")
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetMovemenetReqEntity that = (AssetMovemenetReqEntity) o;

        if (id != that.id) return false;
        if (inventNum != that.inventNum) return false;
        if (userFromOld != that.userFromOld) return false;
        if (userToNew != that.userToNew) return false;
        if (requestId != that.requestId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + inventNum;
        result = 31 * result + userFromOld;
        result = 31 * result + userToNew;
        result = 31 * result + requestId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_from_old", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public UsersEntity getUsersByUserFromOld() {
        return usersByUserFromOld;
    }

    public void setUsersByUserFromOld(UsersEntity usersByUserFromOld) {
        this.usersByUserFromOld = usersByUserFromOld;
    }

    @ManyToOne
    @JoinColumn(name = "user_to_new", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public UsersEntity getUsersByUserToNew() {
        return usersByUserToNew;
    }

    public void setUsersByUserToNew(UsersEntity usersByUserToNew) {
        this.usersByUserToNew = usersByUserToNew;
    }

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public RequestsEntity getRequestsByRequestId() {
        return requestsByRequestId;
    }

    public void setRequestsByRequestId(RequestsEntity requestsByRequestId) {
        this.requestsByRequestId = requestsByRequestId;
    }
}
