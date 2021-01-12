package com.zc58s.springbootbase.service;

import com.zc58s.springbootbase.entity.User;

/**
 * springboot-base
 * com.zc58s.springbootbase.service
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/12 10:43
 */
public interface UserService {
    /**
     * @param id
     * @return
     */
    User findUserById(String id);
}
