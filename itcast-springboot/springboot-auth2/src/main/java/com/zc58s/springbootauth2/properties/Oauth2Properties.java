package com.zc58s.springbootauth2.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created on 2018/7/24 0024.
 *
 * @author szy
 * @email i@suntray.com
 * @since 1.0
 */
@ConfigurationProperties(prefix = "suntray.security.oauth2")
public class Oauth2Properties {

    private String jwtSigningKey = "suntray";

    private Oauth2ClientProperties[] clients = {};

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }

    public Oauth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(Oauth2ClientProperties[] clients) {
        this.clients = clients;
    }
}
