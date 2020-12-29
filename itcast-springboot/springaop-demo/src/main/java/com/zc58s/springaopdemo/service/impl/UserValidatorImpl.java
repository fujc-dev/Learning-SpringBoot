package com.zc58s.springaopdemo.service.impl;

import com.zc58s.springaopdemo.pojo.User;
import com.zc58s.springaopdemo.service.UserValidator;

/*
 * springaop-demo
 * com.zc58s.springaopdemo.service.impl
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/29 10:13
 */
public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean validator(User user) {
        System.out.println("引入新的接口： " + UserValidator.class.getSimpleName());
        return user != null;
    }
}
