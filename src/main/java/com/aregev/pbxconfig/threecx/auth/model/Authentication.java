package com.aregev.pbxconfig.threecx.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Authentication {

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Token")
    private Token token;

    @JsonProperty("TwoFactorAuth")
    private String twoFactorAuth;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getTwoFactorAuth() {
        return twoFactorAuth;
    }

    public void setTwoFactorAuth(String twoFactorAuth) {
        this.twoFactorAuth = twoFactorAuth;
    }
}
