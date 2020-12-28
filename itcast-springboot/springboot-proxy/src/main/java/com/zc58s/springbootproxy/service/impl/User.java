package com.zc58s.springbootproxy.service.impl;

import com.zc58s.springbootproxy.service.UserInterface;

/*
 * springboot-proxy
 * com.zc58s.springbootproxy.service.impl
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/28 10:03
 */
public class User implements UserInterface {
    @Override
    public void sayHello() {
        System.out.println("Hello World");
    }
}
