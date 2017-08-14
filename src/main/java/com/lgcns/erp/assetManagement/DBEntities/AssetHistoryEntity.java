package com.lgcns.erp.assetManagement.DBEntities;

import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "asset_history", schema = "asset_management", catalog = "LgErpSystem")
public class AssetHistoryEntity {
    private int id;
    private Integer assetInventNumber;
    private Integer userIdOld;
    private Integer userIdNew;
    private Date regDate;
    private UsersEntity usersByUserIdOld;
    private UsersEntity usersByUserIdNew;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "asset_invent_number")
    public Integer getAssetInventNumber() {
        return assetInventNumber;
    }

    public void setAssetInventNumber(Integer assetInventNumber) {
        this.assetInventNumber = assetInventNumber;
    }

    @Basic
    @Column(name = "user_id_old")
    public Integer getUserIdOld() {
        return userIdOld;
    }

    public void setUserIdOld(Integer userIdOld) {
        this.userIdOld = userIdOld;
    }

    @Basic
    @Column(name = "user_id_new")
    public Integer getUserIdNew() {
        return userIdNew;
    }

    public void setUserIdNew(Integer userIdNew) {
        this.userIdNew = userIdNew;
    }

    @Basic
    @Column(name = "reg_date")
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetHistoryEntity that = (AssetHistoryEntity) o;

        if (id != that.id) return false;
        if (assetInventNumber != null ? !assetInventNumber.equals(that.assetInventNumber) : that.assetInventNumber != null)
            return false;
        if (userIdOld != null ? !userIdOld.equals(that.userIdOld) : that.userIdOld != null) return false;
        if (userIdNew != null ? !userIdNew.equals(that.userIdNew) : that.userIdNew != null) return false;
        if (regDate != null ? !regDate.equals(that.regDate) : that.regDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (assetInventNumber != null ? assetInventNumber.hashCode() : 0);
        result = 31 * result + (userIdOld != null ? userIdOld.hashCode() : 0);
        result = 31 * result + (userIdNew != null ? userIdNew.hashCode() : 0);
        result = 31 * result + (regDate != null ? regDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_old", referencedColumnName = "id", insertable = false, updatable = false)
    public UsersEntity getUsersByUserIdOld() {
        return usersByUserIdOld;
    }

    public void setUsersByUserIdOld(UsersEntity usersByUserIdOld) {
        this.usersByUserIdOld = usersByUserIdOld;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_new", referencedColumnName = "id", insertable = false, updatable = false)
    public UsersEntity getUsersByUserIdNew() {
        return usersByUserIdNew;
    }

    public void setUsersByUserIdNew(UsersEntity usersByUserIdNew) {
        this.usersByUserIdNew = usersByUserIdNew;
    }
}
