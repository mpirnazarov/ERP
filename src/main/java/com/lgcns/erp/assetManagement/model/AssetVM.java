package com.lgcns.erp.assetManagement.model;


import java.sql.Date;

/**
 * Created by DS on 16.08.2017.
 */
public class AssetVM {
    private int id;
    private int inventNumber;
    private boolean isPublic;
    private int userId;
    private Date regDate;
    private int categoryId;
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInventNumber() {
        return inventNumber;
    }

    public void setInventNumber(int inventNumber) {
        this.inventNumber = inventNumber;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
