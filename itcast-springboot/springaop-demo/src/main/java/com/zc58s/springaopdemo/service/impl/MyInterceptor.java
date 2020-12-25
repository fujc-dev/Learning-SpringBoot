package com.zc58s.springaopdemo.service.impl;

import com.zc58s.springaopdemo.invoke.Invocation;
import com.zc58s.springaopdemo.service.Interceptor;


import java.lang.reflect.InvocationTargetException;
import java.util.TreeMap;

/*
 * springaop-demo
 * com.zc58s.springaopdemo.service.impl
 * 依赖java.lang.reflect.Proxy动态代理对象，进行自定义拦截开发。
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/25 14:34
 */
public class MyInterceptor implements Interceptor {
    private boolean useAround = false;

    public MyInterceptor() {
        this(true);
    }

    public MyInterceptor(Boolean useAround) {
        this.useAround = useAround;
    }

    @Override
    public boolean before() {
        System.out.println("before .....!");
        return true;
    }

    @Override
    public void after() {
        System.out.println("after .....!");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println(" around before .....!");
        Object obj = invocation.proceed();
        System.out.println(" around after .....!");
        this.useAround = true;
        return obj;
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning .....!");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing .....!");
    }

    @Override
    public boolean useAround() {
        return this.useAround;
    }
}
