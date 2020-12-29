package com.zc58s.springaopdemo.aspect;

import com.zc58s.springaopdemo.service.UserValidator;
import com.zc58s.springaopdemo.service.impl.UserServiceImpl;
import com.zc58s.springaopdemo.service.impl.UserValidatorImpl;
import org.aspectj.lang.ProceedingJoinPoint;
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
 * </p>
 * <p>1、在代码中所有的方法都包含相同的正则式，这显然比较冗余，新增pointCut方法作为切点，并在方法上标记切点； </p>
 * <p>2、在老的接口不能变的情况下，我么通过Aop实现对新接口的引入，{@link com.zc58s.springaopdemo.service.UserValidator}; </p>
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
     * {@link DeclareParents}引入增强接口，在老接口不变的情况下，我们可以通过引入新的接口来增强功能。
     * <p>value：指向要增强的功能的目标对象，这里要增强的是{@link UserServiceImpl}，后面这个"+"，暂时就理解为强制必加的。</p>
     * <p>defaultImpl：引入增强功能的类，这里配置的是{@link UserValidatorImpl},用来提交校验用户是否为空</p>
     */
    @DeclareParents(value = "com.zc58s.springaopdemo.service.impl.UserServiceImpl", defaultImpl = UserValidatorImpl.class)
    public UserValidator validator;


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

    /**
     * 环绕通知
     *
     * @param jp
     * @throws Throwable
     */
    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("around before...");
        jp.proceed();
        System.out.println("around after...");
    }
}
