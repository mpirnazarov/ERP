package com.lgcns.erp.tapps.viewModel;

import com.lgcns.erp.tapps.model.DbEntities.AuthTokenEntity;

/**
 * Created by Muslimbek on 5/20/2017.
 */
public class UserPasswordChangeModel {
    private int userId;
    private AuthTokenEntity authTokenEntity;
    private String newPassword;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public AuthTokenEntity getAuthTokenEntity() {
        return authTokenEntity;
    }

    public void setAuthTokenEntity(AuthTokenEntity authTokenEntity) {
        this.authTokenEntity = authTokenEntity;
    }
}
