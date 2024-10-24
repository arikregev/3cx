package com.aregev.pbxconfig;

import com.aregev.pbxconfig.threecx.devices.SipDeviceFetcher;
import com.aregev.pbxconfig.threecx.devices.model.SipDevicesWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Execution {

    @Autowired
    SipDeviceFetcher sipDeviceFetcher;

    @Autowired
    ObjectMapper mapper;
    public void mainExecution(){
        var sip = mapper.convertValue(sipDeviceFetcher.execute(), SipDevicesWrapper.class).getList();
        var sipList = sip.stream()
                .filter(device -> !Strings.isBlank(device.getRegistrar().getMac()) &&
                        !Strings.isBlank(device.getRegistrar().getIpAddress())).toList();
        System.out.println(sipList);

    }

}
