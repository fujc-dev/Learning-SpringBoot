package com.zc58s.springsecuritydemo.service.impl;

import com.zc58s.springsecuritydemo.dao.TUserDao;
import com.zc58s.springsecuritydemo.entity.TRole;
import com.zc58s.springsecuritydemo.entity.TUser;
import com.zc58s.springsecuritydemo.service.CustomSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    private final TUserDao userDao;

    @Autowired
    public CustomSecurityServiceImpl(TUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails findUserByUsername(String username) throws UsernameNotFoundException {
        TUser user = this.userDao.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (TRole role : user.getRoles()) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(authority);
        }
        return new User(user.getUserName(), user.getPwd(), authorities);
    }
}
