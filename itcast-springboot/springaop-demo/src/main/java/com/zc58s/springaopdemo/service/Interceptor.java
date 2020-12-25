package com.zc58s.springaopdemo.service;



import com.zc58s.springaopdemo.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

/*
 * springaop-demo
 * com.zc58s.springaopdemo.service
 * 依赖java.lang.reflect.Proxy动态代理对象，进行自定义拦截开发。
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/25 14:27
 */
public interface Interceptor {

    /**
     * 事前方法
     *
     * @return
     */
    boolean before();

    /**
     * 事后方法
     */
    void after();

    /**
     * 取代原有事件方法
     *
     * @param invocation 回调参数，可以用过它的proceed方法，回调原有事件
     * @return 原有事件返回对象
     * @throws InvocationTargetException 包装由调用方法或构造方法所抛出异常的受查异常
     * @throws IllegalAccessException    表示没有访问权限异常
     */
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    /**
     * 是否返回方法，事件没有发生异常执行
     */
    void afterReturning();

    /**
     * 事后异常方法，当事件发生异常后执行
     */
    void afterThrowing();

    /**
     * 是否使用around方法替代原有方法
     *
     * @return
     */
    boolean useAround();
}
