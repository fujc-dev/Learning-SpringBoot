package com.zc58s.springsecuritydemo.service.impl;

import com.zc58s.springsecuritydemo.service.CustomSecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/7 17:13
 * springsecurity-demo
 * com.zc58s.springsecuritydemo.service
 *
 * <p>{@link UserDetailsService}</p>
 * <p>
 * 基于SpringSecurity，自定义用户认证服务
 * </p>
 */
@Service
public class CustomSecurityServiceImpl extends CustomSecurityService {

    @Override
    public UserDetails findUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
