package com.lgcns.erp.assetManagement.DBEntities;

import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "asset", schema = "asset_management", catalog = "LgErpSystem")
public class AssetEntity {
    private int id;
    private Integer inventNumber;
    private Boolean isPublic;
    private Integer userId;
    private Date regDate;
    private Integer categoryId;
    private UsersEntity usersByUserId;
    private AssetCategoryEntity assetCategoryByCategoryId;
    private AssetHistoryEntity assetHistoryByInventNumber;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "invent_number")
    public Integer getInventNumber() {
        return inventNumber;
    }

    public void setInventNumber(Integer inventNumber) {
        this.inventNumber = inventNumber;
    }

    @Basic
    @Column(name = "is_public")
    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetEntity that = (AssetEntity) o;

        if (id != that.id) return false;
        if (inventNumber != null ? !inventNumber.equals(that.inventNumber) : that.inventNumber != null) return false;
        if (isPublic != null ? !isPublic.equals(that.isPublic) : that.isPublic != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (regDate != null ? !regDate.equals(that.regDate) : that.regDate != null) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (inventNumber != null ? inventNumber.hashCode() : 0);
        result = 31 * result + (isPublic != null ? isPublic.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (regDate != null ? regDate.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    public AssetCategoryEntity getAssetCategoryByCategoryId() {
        return assetCategoryByCategoryId;
    }

    public void setAssetCategoryByCategoryId(AssetCategoryEntity assetCategoryByCategoryId) {
        this.assetCategoryByCategoryId = assetCategoryByCategoryId;
    }

}
