package com.zc58s.springmvcdemo.service;

import com.zc58s.springmvcdemo.entity.User;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/30 9:52
 * springboot-mybatis
 * com.zc58s.springmvcdemo.service
 */
public interface UserService {
    User getUser(Long id);

    int insertUser(User user);
}
