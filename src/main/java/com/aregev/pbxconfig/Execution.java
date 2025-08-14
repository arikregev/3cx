package com.aregev.pbxconfig;

import com.aregev.pbxconfig.threecx.devices.SipDeviceFetcher;
import com.aregev.pbxconfig.threecx.devices.model.SipDevice;
import com.aregev.pbxconfig.threecx.devices.model.SipDevicesWrapper;
import com.aregev.pbxconfig.yealink.ConfigSaver;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(Execution.class);
    private final SipDeviceFetcher sipDeviceFetcher;
    private final YealinkConfigFetcher yealinkConfigFetcher;
    private final ConfigSaver configSaver;
    private final ObjectMapper mapper;

    @Autowired
    public Execution(SipDeviceFetcher sipDeviceFetcher, YealinkConfigFetcher yealinkConfigFetcher, ConfigSaver configSaver, ObjectMapper mapper) {
        this.sipDeviceFetcher = sipDeviceFetcher;
        this.yealinkConfigFetcher = yealinkConfigFetcher;
        this.configSaver = configSaver;
        this.mapper = mapper;
    }

    public void mainExecution(){
        var configs = new HashMap<SipDevice, String>();
        var sipList = mapper.convertValue(sipDeviceFetcher.execute(), SipDevicesWrapper.class).getList().stream()
                .filter(device -> !Strings.isBlank(device.getRegistrar().getMac()) &&
                        !Strings.isBlank(device.getRegistrar().getIpAddress()) &&
                        !device.getRegistrar().getIpAddress().contains("SBC")).toList();
        sipList.forEach(device -> {
            try {
                var config = yealinkConfigFetcher.getConfiguration(device);
                if(config != null) {
                    configs.put(device, config);
                    LOGGER.debug("Configuration for device: {} was added to map.", device);
                } else {
                    LOGGER.error("Error: returned null config for {}", device);
                }
            } catch (IOException e) {
                LOGGER.error("Skipping: {} because {} thrown with message: {}", device, e.getClass().getName(), e.getMessage());
            }
        });
        configSaver.saveConfigs(configs);
    }

}
