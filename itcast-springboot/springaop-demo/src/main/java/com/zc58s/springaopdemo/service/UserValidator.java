package com.zc58s.springaopdemo.service;

import com.zc58s.springaopdemo.pojo.User;

/*
 * springaop-demo
 * com.zc58s.springaopdemo.service
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/29 10:12
 */
public interface UserValidator {
    boolean validator(User user);
}
