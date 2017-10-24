package com.lgcns.erp.assetManagement.DBEntities;

import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "asset", schema = "asset_management", catalog = "LgErpSystem")
public class AssetEntity {
    private int id;
    private int inventNum;
    private String nameEn;
    private String nameRu;
    private int ownerId;
    private Date regDate;
    private String regInfo;
    private Double cost;
    private String location;
    private boolean checkPublic;
    private boolean checkEnabled;
    private boolean checkDeleted;
    private UsersEntity usersByOwnerId;
    private AssetHistoryEntity assetHistoryByInventNum;

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
    @Column(name = "name_en")
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Basic
    @Column(name = "name_ru")
    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @Basic
    @Column(name = "owner_id")
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "reg_date")
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Basic
    @Column(name = "reg_info")
    public String getRegInfo() {
        return regInfo;
    }

    public void setRegInfo(String regInfo) {
        this.regInfo = regInfo;
    }

    @Basic
    @Column(name = "cost")
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "check_public")
    public boolean isCheckPublic() {
        return checkPublic;
    }

    public void setCheckPublic(boolean checkPublic) {
        this.checkPublic = checkPublic;
    }

    @Basic
    @Column(name = "check_enabled")
    public boolean isCheckEnabled() {
        return checkEnabled;
    }

    public void setCheckEnabled(boolean checkEnabled) {
        this.checkEnabled = checkEnabled;
    }

    @Basic
    @Column(name = "check_deleted")
    public boolean isCheckDeleted() {
        return checkDeleted;
    }

    public void setCheckDeleted(boolean checkDeleted) {
        this.checkDeleted = checkDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetEntity that = (AssetEntity) o;

        if (id != that.id) return false;
        if (inventNum != that.inventNum) return false;
        if (ownerId != that.ownerId) return false;
        if (checkPublic != that.checkPublic) return false;
        if (checkEnabled != that.checkEnabled) return false;
        if (checkDeleted != that.checkDeleted) return false;
        if (nameEn != null ? !nameEn.equals(that.nameEn) : that.nameEn != null) return false;
        if (nameRu != null ? !nameRu.equals(that.nameRu) : that.nameRu != null) return false;
        if (regDate != null ? !regDate.equals(that.regDate) : that.regDate != null) return false;
        if (regInfo != null ? !regInfo.equals(that.regInfo) : that.regInfo != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + inventNum;
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (nameRu != null ? nameRu.hashCode() : 0);
        result = 31 * result + ownerId;
        result = 31 * result + (regDate != null ? regDate.hashCode() : 0);
        result = 31 * result + (regInfo != null ? regInfo.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (checkPublic ? 1 : 0);
        result = 31 * result + (checkEnabled ? 1 : 0);
        result = 31 * result + (checkDeleted ? 1 : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public UsersEntity getUsersByOwnerId() {
        return usersByOwnerId;
    }

    public void setUsersByOwnerId(UsersEntity usersByOwnerId) {
        this.usersByOwnerId = usersByOwnerId;
    }

    @ManyToOne
    @JoinColumn(name = "invent_num", referencedColumnName = "invent_num", nullable = false, insertable = false, updatable = false)
    public AssetHistoryEntity getAssetHistoryByInventNum() {
        return assetHistoryByInventNum;
    }

    public void setAssetHistoryByInventNum(AssetHistoryEntity assetHistoryByInventNum) {
        this.assetHistoryByInventNum = assetHistoryByInventNum;
    }
}
