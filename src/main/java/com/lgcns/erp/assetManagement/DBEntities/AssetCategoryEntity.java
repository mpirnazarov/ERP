package com.lgcns.erp.assetManagement.DBEntities;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "asset_category", schema = "asset_management", catalog = "LgErpSystem")
public class AssetCategoryEntity {
    private int id;
    private String assetItemName;
    private Boolean isEnabled;
    private Collection<AssetEntity> assetsById;

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
    @Column(name = "asset_item_name")
    public String getAssetItemName() {
        return assetItemName;
    }

    @Basic
    @Column(name = "is_enabled")
    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean aEnabled) {
        isEnabled = aEnabled;
    }

    public void setAssetItemName(String assetItemName) {
        this.assetItemName = assetItemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetCategoryEntity that = (AssetCategoryEntity) o;

        if (id != that.id) return false;
        if (assetItemName != null ? !assetItemName.equals(that.assetItemName) : that.assetItemName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (assetItemName != null ? assetItemName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "assetCategoryByCategoryId")
    public Collection<AssetEntity> getAssetsById() {
        return assetsById;
    }

    public void setAssetsById(Collection<AssetEntity> assetsById) {
        this.assetsById = assetsById;
    }
}
