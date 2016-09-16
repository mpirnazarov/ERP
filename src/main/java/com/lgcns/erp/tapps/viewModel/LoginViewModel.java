package com.lgcns.erp.tapps.viewModel;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.registry.infomodel.User;
import java.io.Serializable;

/**
 * Created by Rafatdin on 15.09.2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginViewModel implements Serializable {
    @JsonProperty("Username")
    private String Username;
    @JsonProperty("Password")
    private String Password;
    @JsonProperty("RememberMe")
    private boolean RememberMe;
    @JsonProperty("ReturnUrl")
    private String ReturnUrl;

    public LoginViewModel(String Username, String Password, boolean RememberMe, String ReturnUrl)
    {
        this.Username = Username;
        this.Password = Password;
        this.RememberMe = RememberMe;
        this.ReturnUrl = ReturnUrl;
    }
    LoginViewModel() {}

    public String getUserName() {
        return Username;
    }

    public void setUserName(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isRememberMe() {
        return RememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        RememberMe = rememberMe;
    }

    public String getReturnUrl() {
        return ReturnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        ReturnUrl = returnUrl;
    }

}
