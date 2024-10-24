package com.aregev.pbxconfig.yealink.util;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public enum Endpoint {
    AUTH(){
        @Override
        public String getEndpoint() {
            return "/api/auth/login?@";
        }

        @Override
        public Request.Builder getBuilder() {
            return new Request.Builder().get();
        }
    },
    PULL_CONFIG {
        @Override
        public String getEndpoint() {
            return "/api/diagnosis/cfg/file?action=export&type=all";
        }

        @Override
        public Request.Builder getBuilder() {
            return new Request.Builder().post(RequestBody.create("", MediaType.parse("*/*")));
        }
    };

    public abstract String getEndpoint();

    public abstract Request.Builder getBuilder();

}
