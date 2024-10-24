package com.aregev.pbxconfig.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class Factory {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${okhttp3.max.limit:4}")
    private int maxLimit;

    @Value("${okhttp3.pause.duration:10}")
    private int pauseDuration;

    @Bean
    public OkHttpClient OkHttpClientFactory(){
        return new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    public ObjectMapper ObjectMapperFactory(){
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
