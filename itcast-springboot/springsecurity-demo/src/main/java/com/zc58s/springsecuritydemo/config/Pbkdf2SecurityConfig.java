package com.zc58s.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/7 16:04
 * springsecurity-demo
 * com.zc58s.springsecuritydemo.config
 */
@Configuration
@EnableWebSecurity
public class Pbkdf2SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     */

    private DataSource dataSource = null;

    @Autowired
    public Pbkdf2SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    String pwdQuery = " SELECT USER_NAME,PWD,AVAILABLE FROM T_USER WHERE USER_NAME = ? ";

    String roleQuery = " SELECT U.USER_NAME,R.ROLE_NAME FROM T_USER U,T_ROLE R,T_USER_ROLE UR WHERE 1=1 AND U.ID = UR.USER_ID AND R.ID = UR.ROLE_ID AND U.USER_NAME = ? ";


    @Value("${system.user.password.secret}")
    private String secret = null;

    /**
     * 基于数据库的认证参数配置
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new Pbkdf2PasswordEncoder(this.secret);
        //
        System.out.println(" ------> " + encoder.matches("123456", "7cf6a3ddba50a6d0341270933922f8ef49a8ae459cea6a8ae715d211e2664ad1d007bdfdadc7830b"));
        //System.out.println(" ------> " + new Pbkdf2PasswordEncoder().encode("123456"));
        //切换到数据库就授权失败，找不到原因，不知道为啥子。
        //auth.inMemoryAuthentication()
        //      .passwordEncoder(encoder)
        //      .withUser("test")
        //      .password("7cf6a3ddba50a6d0341270933922f8ef49a8ae459cea6a8ae715d211e2664ad1d007bdfdadc7830b")
        //      .roles("USER");

        auth.jdbcAuthentication()
                // 密码编码器
                .passwordEncoder(encoder)
                // 数据源
                .dataSource(dataSource)
                // 查询用户，自动判断密码是否一致
                .usersByUsernameQuery(pwdQuery)
                // 赋予权限
                .authoritiesByUsernameQuery(roleQuery);

    }
}
