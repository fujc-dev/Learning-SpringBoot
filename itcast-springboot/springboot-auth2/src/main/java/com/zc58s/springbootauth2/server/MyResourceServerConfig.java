package com.zc58s.springbootauth2.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created on 2018/7/17.
 *
 * @author szy
 * @since 1.0
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 自定义登录成功处理器
     */

    private AuthenticationSuccessHandler appLoginInSuccessHandler;

    @Autowired
    public MyResourceServerConfig(AuthenticationSuccessHandler appLoginInSuccessHandler) {
        this.appLoginInSuccessHandler = appLoginInSuccessHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 登录成功处理器
                .successHandler(appLoginInSuccessHandler)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }
}
