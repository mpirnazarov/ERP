package com.lgcns.erp.assetManagement.model;


import java.sql.Date;
import java.util.List;

/**
 * Created by DS on 16.08.2017.
 */
public class AssetVM {
    private int id;
    private int inventNum;
    private String nameEn;
    private String nameRu;
    private int ownerId;
    private String ownerFullName;
    private Date regDate;
    private String regInfo;
    private Double cost;
    private String location;
    private boolean checkPublic;
    private boolean checkEnabled;
    private boolean checkDeleted;
    private List<AssetHistoryVM> assetHistoryVMList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInventNum() {
        return inventNum;
    }

    public void setInventNum(int inventNum) {
        this.inventNum = inventNum;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getRegInfo() {
        return regInfo;
    }

    public void setRegInfo(String regInfo) {
        this.regInfo = regInfo;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isCheckPublic() {
        return checkPublic;
    }

    public void setCheckPublic(boolean checkPublic) {
        this.checkPublic = checkPublic;
    }

    public boolean isCheckEnabled() {
        return checkEnabled;
    }

    public void setCheckEnabled(boolean checkEnabled) {
        this.checkEnabled = checkEnabled;
    }

    public boolean isCheckDeleted() {
        return checkDeleted;
    }

    public void setCheckDeleted(boolean checkDeleted) {
        this.checkDeleted = checkDeleted;
    }

    public List<AssetHistoryVM> getAssetHistoryVMList() {
        return assetHistoryVMList;
    }

    public void setAssetHistoryVMList(List<AssetHistoryVM> assetHistoryVMList) {
        this.assetHistoryVMList = assetHistoryVMList;
    }

    @Override
    public String toString() {
        return "AssetVM{" +
                "id=" + id +
                ", inventNum=" + inventNum +
                ", nameEn='" + nameEn + '\'' +
                ", nameRu='" + nameRu + '\'' +
                ", ownerId=" + ownerId +
                ", ownerFullName='" + ownerFullName + '\'' +
                ", regDate=" + regDate +
                ", regInfo='" + regInfo + '\'' +
                ", cost=" + cost +
                ", location='" + location + '\'' +
                ", checkPublic=" + checkPublic +
                ", checkEnabled=" + checkEnabled +
                ", checkDeleted=" + checkDeleted +
                ", assetHistoryVMList=" + assetHistoryVMList +
                '}';
    }
}
