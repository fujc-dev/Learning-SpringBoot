package com.zc58s.springbootproxy.proxy;

import com.zc58s.springbootproxy.service.UserInterface;

import java.lang.reflect.InvocationHandler;

/*
 * springboot-proxy
 * com.zc58s.springbootproxy.proxy
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/28 10:26
 */
public class UserExtendProxy implements UserInterface {
    // 持有处理类的对象
    private InvocationHandler handler;

    public UserExtendProxy(InvocationHandler handler) {
        this.handler = handler;
    }

    // 实现sayHello()方法，并调用invoke()
    @Override
    public void sayHello() {
        try {
            handler.invoke(this, UserInterface.class.getMethod("sayHello"), null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
