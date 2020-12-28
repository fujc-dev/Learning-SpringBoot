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
 * <p>
 * 设计总结：
 * Interceptor称之为拦截器，其目的是需要对某一个函数方法的运行过程进行监听，我们总结并抽象出来的一个接口，
 * 里面包含了对方法的执行前、执行后以及正常执行以及异常等等的处理，或者称为处理办法。
 * ProxyBean实现了InvocationHandler接口，必须要实现的，因为java.lang.reflect.Proxy.newProxyInstance
 * 方法需要这个对象，其目的就是在动态创建代理对象的时候方便注入。
 * </p>
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
        System.out.println(target.getClass().getName());
        Class<?>[] dataset= target.getClass().getInterfaces();
        for (int i = 0;i<dataset.length;i++){
            System.out.println(dataset[0].getName());;
        }
        //target.getClass().getInterfaces() 获取target这个类所实现的所有接口
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
