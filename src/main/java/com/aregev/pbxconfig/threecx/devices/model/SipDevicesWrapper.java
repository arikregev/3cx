package com.aregev.pbxconfig.threecx.devices.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SipDevicesWrapper {

    @JsonProperty("value")
    private List<SipDevice> list;

    public List<SipDevice> getList() {
        return list;
    }

    public void setList(List<SipDevice> list) {
        this.list = list;
    }
}
