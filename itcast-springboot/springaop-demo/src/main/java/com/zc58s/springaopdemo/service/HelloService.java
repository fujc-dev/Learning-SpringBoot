package com.zc58s.springaopdemo.service;

/*
 * springaop-demo
 * com.zc58s.springaopdemo.service
 * 一个不需要去解释的接口,依赖java.lang.reflect.Proxy动态代理对象，进行自定义拦截开发。
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/25 14:24
 */
public interface HelloService {
    /**
     * @param name
     */
    void sayHello(String name);
}
