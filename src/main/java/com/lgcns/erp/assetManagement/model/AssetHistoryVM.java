package com.lgcns.erp.assetManagement.model;


import com.lgcns.erp.tapps.model.UserModel;

import java.sql.Date;

/**
 * Created by Muslimbek on 05.09.2017.
 */
public class AssetHistoryVM {
    private int id;
    private int inventNumber;
    private UserModel userOldId;
    private UserModel userNewId;
    private Date regDate;

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

    public UserModel getUserOldId() {
        return userOldId;
    }

    public void setUserOldId(UserModel userOldId) {
        this.userOldId = userOldId;
    }

    public UserModel getUserNewId() {
        return userNewId;
    }

    public void setUserNewId(UserModel userNewId) {
        this.userNewId = userNewId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "AssetHistoryVM{" +
                "id=" + id +
                ", inventNumber=" + inventNumber +
                ", userOldId=" + userOldId +
                ", userNewId=" + userNewId +
                ", regDate=" + regDate +
                '}';
    }
}
