package com.aregev.pbxconfig.threecx.auth;

import com.aregev.pbxconfig.threecx.auth.model.Authentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthFactory {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    AuthComponent authComponent;

    @Bean
    Authentication getAuth() {
        return mapper.convertValue(
                authComponent.execute(),
                Authentication.class);
    }
}
