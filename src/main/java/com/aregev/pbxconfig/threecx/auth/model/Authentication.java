package com.aregev.pbxconfig.threecx.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Authentication {

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Token")
    private Token token;

    @JsonProperty("TwoFactorAuth")
    private String twoFactorAuth;
}
