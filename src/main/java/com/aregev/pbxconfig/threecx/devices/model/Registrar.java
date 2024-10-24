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

    @ToString.Exclude
    @JsonProperty("InterfaceLink")
    private String link;

    @JsonProperty("FirmwareVersion")
    private String firmwareVersion;

    @JsonProperty("FirmwareAvailable")
    private String firmwareAvailable;

}
