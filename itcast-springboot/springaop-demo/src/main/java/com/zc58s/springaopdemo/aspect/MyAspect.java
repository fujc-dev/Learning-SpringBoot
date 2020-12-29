package com.zc58s.springaopdemo.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;


/**
 * 创建AOP
 * <p>
 *     修改记录：
 * </p>
 */
@Aspect
public class MyAspect {

    /**
     * Spring以 @{@link Aspect}作为切面声明，当以@{@link Aspect}作为注解时，Spring就知道这是一个切面，然后我们就可以通过各类注解来定义各类通知。
     * <p>
     * @ {@link Before} 前置通知
     * </p>
     * <p>
     * @ {@link After} 后置通知
     * </p>
     * <p>
     * @ {@link AfterReturning} 事后返回通知
     * </p>
     * <p>
     * @ {@link AfterThrowing} 异常通知
     * </p>
     */
    public MyAspect() {

    }

    /**
     * 事前方法
     *
     * @return
     */
    @Before(value = "execution(* com.zc58s.springaopdemo.service.impl.UserServiceImpl.printUser(..))")
    public void before() {
        System.out.println("before...");
    }

    /**
     * 事后方法
     */
    @After(value = "execution(* com.zc58s.springaopdemo.service.impl.UserServiceImpl.printUser(..))")
    public void after() {
        System.out.println("after...");
    }


    /**
     * 是否返回方法，事件没有发生异常执行
     */
    @AfterReturning(value = "execution(* com.zc58s.springaopdemo.service.impl.UserServiceImpl.printUser(..))")
    public void afterReturning() {
        System.out.println("afterReturning...");
    }

    /**
     * 事后异常方法，当事件发生异常后执行
     */
    @AfterThrowing(value = "execution(* com.zc58s.springaopdemo.service.impl.UserServiceImpl.printUser(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing...");
    }
}
