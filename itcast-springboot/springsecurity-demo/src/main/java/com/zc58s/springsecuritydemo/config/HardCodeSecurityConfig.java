package com.zc58s.springsecuritydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 硬编码的方法构建认证
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/7 14:04
 * springsecurity-demo
 * com.zc58s.springsecuritydemo.config
 */

public class HardCodeSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 认证部分
     * <p>{@link PasswordEncoder}，密码编码器，Spring5强制要求必须使用；</p>
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码编码器
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String p1 = encoder.encode("abc");
        String p2 = encoder.encode("123456");
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("admin").password(p1).roles("USER", "ADMIN")
                .and()
                .withUser("user").password(p2).roles("USER");

        //super.configure(auth); 最后要把这个父类方法屏蔽或者删除了
    }

    /**
     * 授权部分
     * <p></p>
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        super.configure(web);
    }
}
