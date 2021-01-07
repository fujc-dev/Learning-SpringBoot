package com.zc58s.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/7 14:36
 * springsecurity-demo
 * com.zc58s.springsecuritydemo.config
 */
public class DatabaseSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource = null;

    String pwdQuery = " SELECT USER_NAME,PWD,AVAILABLE FROM T_USER WHERE USER_NAME = ? ";

    String roleQuery = " SELECT U.USER_NAME,R.ROLE_NAME FROM T_USER U,T_ROLE R,T_USER_ROLE UR WHERE 1=1 AND U.ID = UR.USER_ID AND R.ID = UR.ROLE_ID AND U.USER_NAME = ? ";


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        //
        auth.jdbcAuthentication()
                //
                .passwordEncoder(encoder)
                //
                .dataSource(dataSource)
                //
                .usersByUsernameQuery(pwdQuery)
                //
                .authoritiesByUsernameQuery(roleQuery);
        super.configure(auth);
    }
}
