package com.zc58s.springbootredis.service;

import com.zc58s.springbootredis.pojo.User;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/30 9:52
 * springboot-mybatis
 * com.zc58s.springbootredis.service
 */
public interface UserService {
    User getUser(Long id);
    int insertUser(User user);
}
