package com.aregev.pbxconfig.threecx.devices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Registrar {

    @JsonProperty("Capabilities")
    private int capabilities;

    @JsonProperty("Vendor")
    private String vendor;

    @JsonProperty("Model")
    private String model;

    @JsonProperty("MAC")
    private String mac;

    @JsonProperty("UserAgent")
    private String userAgent;

    @JsonProperty("IpAddress")
    private String ipAddress;

    @JsonProperty("InterfaceLink")
    private String link;

    @JsonProperty("FirmwareVersion")
    private String firmwareVersion;

    @JsonProperty("FirmwareAvailable")
    private String firmwareAvailable;

    public int getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(int capabilities) {
        this.capabilities = capabilities;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getFirmwareAvailable() {
        return firmwareAvailable;
    }

    public void setFirmwareAvailable(String firmwareAvailable) {
        this.firmwareAvailable = firmwareAvailable;
    }

    @Override
    public String toString() {
        return "Registrar{" +
                "capabilities=" + capabilities +
                ", vendor='" + vendor + '\'' +
                ", model='" + model + '\'' +
                ", mac='" + mac + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", link='" + link + '\'' +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                ", firmwareAvailable='" + firmwareAvailable + '\'' +
                '}';
    }
}
