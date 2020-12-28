package com.zc58s.springbootproxy.handler;

import com.zc58s.springbootproxy.service.impl.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/*
 * springboot-proxy
 * com.zc58s.springbootproxy.handler
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/28 10:04
 */
public class UserHandler implements InvocationHandler {

    private User user;

    public UserHandler(User user) {
        this.user = user;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoking start....");
        method.invoke(user);
        System.out.println("invoking stop....");
        return user;
    }
}
