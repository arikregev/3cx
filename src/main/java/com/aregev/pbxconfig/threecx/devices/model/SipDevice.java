package com.aregev.pbxconfig.threecx.devices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SipDevice {

    @JsonProperty("Id")
    private int id;

    @JsonProperty("ProvLink")
    private String provisioningLink;

    @JsonProperty("PhoneWebPassword")
    private String phoneWebPassword;

    @JsonProperty("Registrar")
    private Registrar registrar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvisioningLink() {
        return provisioningLink;
    }

    public void setProvisioningLink(String provisioningLink) {
        this.provisioningLink = provisioningLink;
    }

    public String getPhoneWebPassword() {
        return phoneWebPassword;
    }

    public void setPhoneWebPassword(String phoneWebPassword) {
        this.phoneWebPassword = phoneWebPassword;
    }

    public Registrar getRegistrar() {
        return registrar;
    }

    public void setRegistrar(Registrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public String toString() {
        return "SipDevice{" +
                "id=" + id +
                ", provisioningLink='" + provisioningLink + '\'' +
                ", phoneWebPassword='" + phoneWebPassword + '\'' +
                ", registrar=" + registrar +
                '}';
    }
}
