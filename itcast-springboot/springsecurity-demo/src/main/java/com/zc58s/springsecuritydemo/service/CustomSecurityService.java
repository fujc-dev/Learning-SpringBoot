package com.zc58s.springsecuritydemo.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/7 17:13
 * springsecurity-demo
 * com.zc58s.springsecuritydemo.service
 */
public interface CustomSecurityService {
    UserDetails findUserByUsername(String username);
}
