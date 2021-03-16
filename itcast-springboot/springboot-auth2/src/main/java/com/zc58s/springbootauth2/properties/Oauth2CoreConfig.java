package com.zc58s.springbootauth2.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018/7/24 0024.
 *
 * @author szy
 * @email i@suntray.com
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(Oauth2Properties.class)
public class Oauth2CoreConfig {
}
