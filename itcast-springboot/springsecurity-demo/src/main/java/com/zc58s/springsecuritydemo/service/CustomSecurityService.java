package com.zc58s.springsecuritydemo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/7 17:13
 * springsecurity-demo
 * com.zc58s.springsecuritydemo.service
 * <p>
 * 基于SpringSecurity，自定义用户认证服务
 * </p>
 */
public abstract class CustomSecurityService implements UserDetailsService {

    /**
     * @param username
     * @return
     */
    public abstract UserDetails findUserByUsername(String username) throws UsernameNotFoundException;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByUsername(username);
    }
}
