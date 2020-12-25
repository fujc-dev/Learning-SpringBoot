package com.zc58s.springaopdemo.service.impl;

import com.zc58s.springaopdemo.service.HelloService;

/*
 * springaop-demo
 * com.zc58s.springaopdemo.service.impl
 * 依赖java.lang.reflect.Proxy动态代理对象，进行自定义拦截开发。
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/25 14:25
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name) {
        if (name == null || name.trim() == "") throw new RuntimeException("parameter is null!");
        System.out.println("hello " + name);
    }
}
