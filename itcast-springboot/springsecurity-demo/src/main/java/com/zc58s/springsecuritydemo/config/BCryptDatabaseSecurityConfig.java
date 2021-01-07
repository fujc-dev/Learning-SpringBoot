package com.zc58s.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
//@Configuration
//@EnableWebSecurity
public class BCryptDatabaseSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     */

    private DataSource dataSource = null;

    @Autowired
    public BCryptDatabaseSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    String pwdQuery = " SELECT USER_NAME,PWD,AVAILABLE FROM T_USER WHERE USER_NAME = ? ";

    String roleQuery = " SELECT U.USER_NAME,R.ROLE_NAME FROM T_USER U,T_ROLE R,T_USER_ROLE UR WHERE 1=1 AND U.ID = UR.USER_ID AND R.ID = UR.ROLE_ID AND U.USER_NAME = ? ";


    /**
     * 基于数据库的认证参数配置
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        //
        System.out.println(encoder.encode("123456"));
        auth.jdbcAuthentication()
                // 密码编码器
                .passwordEncoder(encoder)
                // 数据源
                .dataSource(dataSource)
                // 查询用户，自动判断密码是否一致
                .usersByUsernameQuery(pwdQuery)
                // 赋予权限
                .authoritiesByUsernameQuery(roleQuery);

        //super.configure(auth); 最后要把这个父类方法屏蔽或者删除了
    }
}
