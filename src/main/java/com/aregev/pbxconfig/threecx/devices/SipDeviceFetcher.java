package com.aregev.pbxconfig.threecx.devices;

import com.aregev.pbxconfig.threecx.BaseRequest;
import com.aregev.pbxconfig.threecx.auth.model.Authentication;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SipDeviceFetcher extends BaseRequest {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    Authentication authentication;

    private static final String PATH = "/xapi/v1/SipDevices";

    public <T> T execute() {
        try {
            return super.execute(getRequest());
        } catch (JsonProcessingException e) {
            LOGGER.error("Error: Unable to process map to json, Exception thrown: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Request getRequest() throws JsonProcessingException {
        return new Request.Builder()
                .get()
                .headers(Headers.of("Authorization", "Bearer " + authentication.getToken().getAccessToken()))
                .url(getUrl())
                .build();
    }

    @Override
    protected HttpUrl getUrl() {
        HttpUrl.Builder httpUrl = new HttpUrl.Builder()
                .scheme((urlScheme) ? "https" : "http")
                .host(url).port(port);
        Arrays.stream(PATH.split("/")).forEach(httpUrl::addPathSegment);
        LOGGER.debug("Generated URL: {}", httpUrl.build());
        return httpUrl.build();
    }
}
