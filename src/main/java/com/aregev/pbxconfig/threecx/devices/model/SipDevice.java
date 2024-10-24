package com.aregev.pbxconfig.threecx.devices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SipDevice {

    @JsonProperty("Id")
    private int id;

    @JsonProperty("ProvLink")
    private String provisioningLink;

    @ToString.Exclude
    @JsonProperty("PhoneWebPassword")
    private String phoneWebPassword;

    @JsonProperty("Registrar")
    private Registrar registrar;

}
