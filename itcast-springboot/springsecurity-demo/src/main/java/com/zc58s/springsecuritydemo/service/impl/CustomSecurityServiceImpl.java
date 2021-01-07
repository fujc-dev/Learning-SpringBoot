package com.zc58s.springsecuritydemo.service.impl;

import com.zc58s.springsecuritydemo.service.CustomSecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/7 17:13
 * springsecurity-demo
 * com.zc58s.springsecuritydemo.service
 */
@Service
public class CustomSecurityServiceImpl implements CustomSecurityService {
    @Override
    public UserDetails findUserByUsername(String username) {
        return null;
    }
}
