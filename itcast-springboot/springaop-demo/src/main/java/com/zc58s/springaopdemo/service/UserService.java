package com.zc58s.springaopdemo.service;

import com.zc58s.springaopdemo.pojo.User;

/*
 * springaop-demo
 * com.zc58s.springaopdemo.service
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/28 17:50
 */
public interface UserService {

    /**
     * 用于测试AOP的方法printUser
     *
     * @param user
     */
    void printUser(User user);

}
