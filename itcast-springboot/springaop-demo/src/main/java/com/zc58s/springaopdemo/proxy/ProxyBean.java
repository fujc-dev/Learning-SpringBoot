package com.zc58s.springaopdemo.proxy;

import com.zc58s.springaopdemo.invoke.Invocation;
import com.zc58s.springaopdemo.service.Interceptor;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * 依赖java.lang.reflect.Proxy动态代理对象，进行自定义拦截开发。
 * InvocationHandler是一个抽象的调用处理程序。
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/25 14:41
 */
public class ProxyBean implements InvocationHandler {

    private Object target = null;
    private Interceptor interceptor = null;


    /**
     * @param target
     * @param interceptor
     * @return
     */
    public static Object getProxyBean(Object target, Interceptor interceptor) {
        ProxyBean proxyBean = new ProxyBean();
        proxyBean.target = target;
        proxyBean.interceptor = interceptor;
        /**
         * ClassLoader loader：使用那个加载器去加载这个代理对象，用其他加载器有啥区别?
         * Class<?>[] interfaces,被代理的对象的接口
         * InvocationHandler h 代理需要做的那些使用
         */
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyBean);
        return proxy;
    }


    /**
     * 处理代理对象方法逻辑
     *
     * @param proxy  代理对象，被代理的实现类，
     * @param method 当前方法
     * @param args   运行参数
     * @return 方法调用结果
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        //异常标识
        boolean exceptionFlag = false;
        Invocation invocation = new Invocation(target, method, args);
        Object retObj = null;

        try {
            if (this.interceptor.useAround()) {
                retObj = this.interceptor.around(invocation);
            } else {
                retObj = method.invoke(target, args);
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            exceptionFlag = true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            exceptionFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
            exceptionFlag = true;
        }
        this.interceptor.after();
        if (exceptionFlag) this.interceptor.afterThrowing();
        else {
            this.interceptor.afterReturning();
            return retObj;
        }
        return null;
    }
}
