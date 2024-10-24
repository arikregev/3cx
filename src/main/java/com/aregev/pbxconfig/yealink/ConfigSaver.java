package com.aregev.pbxconfig.yealink;

import com.aregev.pbxconfig.threecx.devices.model.SipDevice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Component
public class ConfigSaver {

    @Value("${save.config.folder}")
    private String root;

    public void saveConfigs(HashMap<SipDevice, String> map) {
        var sdf = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
        var folder = new File(root + "/" + sdf.format(new Date()));
        assert folder.exists() || folder.mkdirs();
        map.forEach((key, value) -> {
            try (var fileWriter = new FileWriter(folder + "/" + key.getRegistrar().getMac() + ".cfg")) {
                fileWriter.write(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
