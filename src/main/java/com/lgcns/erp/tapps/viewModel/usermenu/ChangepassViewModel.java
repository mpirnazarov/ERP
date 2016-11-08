package com.lgcns.erp.tapps.viewModel.usermenu;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by JAS SHAYKHOV on 11/8/2016.
 */
public class ChangepassViewModel {

    @NotEmpty
    private String OldPassword;

    @NotEmpty
    private String Password;

    @NotEmpty
    private String RepeatPassword;

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRepeatPassword() {
        return RepeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        RepeatPassword = repeatPassword;
    }
}
