package com.kggs.c9000sdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/3 13:21
 */
@Configuration
@ConfigurationProperties(prefix = "c9000sdk")
public class C9000SdkConfig {
    private String server;
    private  Integer port;


    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
