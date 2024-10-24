package com.aregev.pbxconfig.yealink;

import com.aregev.pbxconfig.threecx.devices.model.SipDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Component
public class ConfigSaver {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${save.config.folder}")
    private String root;

    public void saveConfigs(HashMap<SipDevice, String> map) {
        var sdf = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
        var folder = new File(root + "/" + sdf.format(new Date()));
        if(!folder.mkdirs()){
            LOGGER.error("Error: Logs folder was not created with path: {}.", folder);
        }
        map.forEach((key, value) -> {
            try (var fileWriter = new FileWriter(String.format("%s/%s.cfg", folder, key.getRegistrar().getMac()))) {
                fileWriter.write(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
