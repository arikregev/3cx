package com.aregev.pbxconfig.threecx.auth;


import com.aregev.pbxconfig.threecx.BaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthComponent extends BaseRequest {

    @Value("${3cx.user.name}")
    private String user;

    @Value("${3cx.user.pass}")
    private String password;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private static final String PATH = "/webclient/api/Login/GetAccessToken";

    public <T> T execute() {
        try {
            return super.execute(getRequest());
        } catch (JsonProcessingException e) {
            LOGGER.error("Error: Unable to process map to json, Exception thrown: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    protected Request getRequest() throws JsonProcessingException {
        Map<String, String> map = new HashMap<>() {{
            put("SecurityCode", "");
            put("Username", user);
            put("Password", password);
        }};
        return new Request.Builder()
                .post(RequestBody.create(mapper.writeValueAsString(map), MediaType.get("application/json")))
                .url(getUrl())
                .build();
    }

    @Override
    protected HttpUrl getUrl() {
        HttpUrl.Builder httpUrl = new HttpUrl.Builder()
                .scheme((urlScheme) ? "https" : "http")
                .host(url).port(port);
        Arrays.stream(PATH.split("/")).forEach(httpUrl::addPathSegment);
        LOGGER.debug("Authentication URL: {}", httpUrl.build());
        return httpUrl.build();
    }

}
