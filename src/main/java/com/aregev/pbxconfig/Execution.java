package com.aregev.pbxconfig;

import com.aregev.pbxconfig.threecx.devices.SipDeviceFetcher;
import com.aregev.pbxconfig.threecx.devices.model.SipDevice;
import com.aregev.pbxconfig.threecx.devices.model.SipDevicesWrapper;
import com.aregev.pbxconfig.yealink.YealinkConfigFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class Execution {

    @Autowired
    SipDeviceFetcher sipDeviceFetcher;

    @Autowired
    YealinkConfigFetcher yealinkConfigFetcher;

    @Autowired
    ObjectMapper mapper;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public void mainExecution(){
        var configs = new HashMap<SipDevice, String>();
        var sip = mapper.convertValue(sipDeviceFetcher.execute(), SipDevicesWrapper.class).getList();
        var sipList = sip.stream()
                .filter(device -> !Strings.isBlank(device.getRegistrar().getMac()) &&
                        !Strings.isBlank(device.getRegistrar().getIpAddress())).toList();
        sipList.forEach(device -> {
            try {
                configs.put(device, yealinkConfigFetcher.getConfiguration(device));
            } catch (IOException e) {
                LOGGER.error("Skipping: {}", device);
            }
        });

        System.out.println(configs);
    }

}
