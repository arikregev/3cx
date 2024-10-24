package com.aregev.pbxconfig.yealink;

import com.aregev.pbxconfig.threecx.devices.model.SipDevice;
import com.aregev.pbxconfig.yealink.util.Endpoint;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class YealinkConfigFetcher {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("OkHttpClientFactoryNoVerification")
    OkHttpClient okHttpClient;

    public String getConfiguration(SipDevice sipDevice) throws IOException {
        var auth = getAuthenticationCookie(sipDevice).get("Set-Cookie");
        try (Response response = okHttpClient.newCall(getRequest(
                HttpUrl.parse(String.format("https://%s%s",sipDevice.getRegistrar().getIpAddress(), Endpoint.PULL_CONFIG.getEndpoint())),
                Endpoint.PULL_CONFIG, auth)).execute()){
            if(!response.isSuccessful()) {
                LOGGER.error("");
            }
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {

        }
        return null;

    }

    public Headers getAuthenticationCookie(SipDevice sipDevice) throws IOException {
        return getAuthenticationCookie(HttpUrl.parse(sipDevice.getRegistrar().getLink()));
    }

    private Headers getAuthenticationCookie(HttpUrl url) throws IOException {
        try (Response response = okHttpClient.newCall(getRequest(url)).execute()){
            if(!response.isSuccessful()) {
                LOGGER.error("Error: exit code is {} when trying to execute: {}", response.code(), url.toString());
            }
            return response.priorResponse().headers();
        } catch (IOException | NullPointerException e) {
            LOGGER.error("Exception Thrown: {} when trying to authenticate with {}", e.getMessage(), url.host());
            throw e;
        }
    }

    private Request getRequest(HttpUrl url) {
        return getRequest(url, Endpoint.AUTH, null);
    }

    private Request getRequest(HttpUrl url, Endpoint endpoint, String jsessionId) {
        var builder = endpoint.getBuilder().url(url);
        if(jsessionId != null){
            builder.header("Cookie", jsessionId)
                .header("Content-Length", "0")
                .header("Host", url.host());

            builder.post(RequestBody.create("", MediaType.Companion.parse("*/*")));
        }
        return builder.build();
    }
}
