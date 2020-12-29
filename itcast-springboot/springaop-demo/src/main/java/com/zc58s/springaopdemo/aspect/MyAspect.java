package com.zc58s.springaopdemo.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;


/**
 * 切面定义
 * <p>
 * 在{@link MyAspect}切面类中，定义了before、after、afterReturning、afterThrowing这四个方法，并且在方法上面标记了正则式，如下：
 * </p>
 * <p>
 * <i>execution(* com.zc58s.springaopdemo.service.impl.UserServiceImpl.printUser(..))</i>
 * </p>
 * <p>
 * 修改记录：
 * 1、在代码中所有的方法都包含相同的正则式，这显然比较冗余，新增pointCut方法作为切点，并在方法上标记切点；
 * </p>
 */
@Aspect
public class MyAspect {

    /**
     * Spring以 @{@link Aspect}作为切面声明，当以@{@link Aspect}作为注解时，Spring就知道这是一个切面，然后我们就可以通过各类注解来定义各类通知。
     * <p>
     *
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
     * 定义一个公共的切点，减少代码冗余
     */
    @Pointcut("execution(* com.zc58s.springaopdemo.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut() {

    }

    /**
     * 事前方法
     *
     * @return
     */
    @Before("pointCut()")
    public void before() {
        System.out.println("before...");
    }

    /**
     * 事后方法
     */
    @After("pointCut()")
    public void after() {
        System.out.println("after...");
    }


    /**
     * 是否返回方法，事件没有发生异常执行
     */
    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning...");
    }

    /**
     * 事后异常方法，当事件发生异常后执行
     */
    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing...");
    }
}
