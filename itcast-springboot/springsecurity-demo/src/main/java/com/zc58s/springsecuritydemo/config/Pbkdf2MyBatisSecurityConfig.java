package com.zc58s.springsecuritydemo.config;

import com.zc58s.springsecuritydemo.service.CustomSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/8 8:42
 * springsecurity-demo
 * com.zc58s.springsecuritydemo.config
 */
@Configuration
@EnableWebSecurity
public class Pbkdf2MyBatisSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${system.user.password.secret}")
    private String secret = null;

    private final CustomSecurityService securityService;

    @Autowired
    public Pbkdf2MyBatisSecurityConfig(CustomSecurityService securityService) {
        this.securityService = securityService;
    }

    /**
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new Pbkdf2PasswordEncoder(this.secret);
        auth.userDetailsService(this.securityService).passwordEncoder(encoder);
    }
}
