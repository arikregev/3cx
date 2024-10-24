package com.aregev.pbxconfig.threecx;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public abstract class BaseRequest {

    @Value("${3cx.http.secure:true}")
    protected boolean urlScheme;

    @Value("${3cx.base.url}")
    protected String url;

    @Value("${3cx.port}")
    protected int port;

    @Autowired
    protected OkHttpClient okHttpClient;

    @Autowired
    protected ObjectMapper mapper;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    protected <T> T execute(Request request) {
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful())
                LOGGER.error(""); //ToDo: Log
            assert response.body() != null;
            return mapper.readValue(response.body().string(), new TypeReference<>() {});
        } catch (IOException e) {
            LOGGER.error("{} Thrown: {} : {}", e.getCause().getClass().getName(), e.getCause().getMessage(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    protected abstract Request getRequest() throws JsonProcessingException;

    protected abstract HttpUrl getUrl();
}
