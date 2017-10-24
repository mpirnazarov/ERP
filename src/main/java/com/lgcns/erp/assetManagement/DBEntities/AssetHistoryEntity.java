package com.lgcns.erp.assetManagement.DBEntities;

import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "asset_history", schema = "asset_management", catalog = "LgErpSystem")
public class AssetHistoryEntity implements java.io.Serializable{
    private int id;
    private int inventNum;
    private int userIdOld;
    private int userIdNew;
    private Date updateDate;
    private UsersEntity usersByUserIdOld;
    private UsersEntity usersByUserIdNew;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "user_id_old")
    public int getUserIdOld() {
        return userIdOld;
    }

    public void setUserIdOld(int userIdOld) {
        this.userIdOld = userIdOld;
    }

    @Basic
    @Column(name = "user_id_new")
    public int getUserIdNew() {
        return userIdNew;
    }

    public void setUserIdNew(int userIdNew) {
        this.userIdNew = userIdNew;
    }

    @Basic
    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetHistoryEntity that = (AssetHistoryEntity) o;

        if (id != that.id) return false;
        if (inventNum != that.inventNum) return false;
        if (userIdOld != that.userIdOld) return false;
        if (userIdNew != that.userIdNew) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + inventNum;
        result = 31 * result + userIdOld;
        result = 31 * result + userIdNew;
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_old", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public UsersEntity getUsersByUserIdOld() {
        return usersByUserIdOld;
    }

    public void setUsersByUserIdOld(UsersEntity usersByUserIdOld) {
        this.usersByUserIdOld = usersByUserIdOld;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_new", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public UsersEntity getUsersByUserIdNew() {
        return usersByUserIdNew;
    }

    public void setUsersByUserIdNew(UsersEntity usersByUserIdNew) {
        this.usersByUserIdNew = usersByUserIdNew;
    }
}
