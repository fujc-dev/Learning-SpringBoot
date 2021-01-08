package com.zc58s.springsecuritydemo.config;

import com.zc58s.springsecuritydemo.service.CustomSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/static/js/**", "/static/css/**", "/static/images/**");
        web.ignoring().antMatchers("/static/assets/js/**", "/static/assets/css/**", "/static/assets/img/**", "/static/assets/fonts/**");
        // super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/assets/js/**", "/assets/css/**", "/assets/img/**", "/assets/fonts/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页的路径
                .loginPage("/login")
                //指定自定义form表单请求的路径
                .loginProcessingUrl("/authentication/form")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/")
                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
                .permitAll();
        //默认都会产生一个hidden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
        http.csrf().disable();
    }
}
