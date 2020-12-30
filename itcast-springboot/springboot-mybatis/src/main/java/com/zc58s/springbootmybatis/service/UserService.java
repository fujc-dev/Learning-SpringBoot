package com.zc58s.springbootmybatis.service;

import com.zc58s.springbootmybatis.pojo.User;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/30 9:52
 * springboot-mybatis
 * com.zc58s.springbootmybatis.service
 */
public interface UserService {
    User getUser(Long id);
    int insertUser(User user);
}
