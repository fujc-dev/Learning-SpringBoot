package com.zc58s.springbootproxy;

import com.zc58s.springbootproxy.handler.UserHandler;
import com.zc58s.springbootproxy.proxy.UserExtendProxy;
import com.zc58s.springbootproxy.proxy.UserProxy;
import com.zc58s.springbootproxy.service.UserInterface;
import com.zc58s.springbootproxy.service.impl.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Proxy;

/**
 * <h1>springboot-proxy 动态代理剖析</h1>
 * <p>
 * <h2><i>静态代理与动态代理</i></h2>
 * </p>
 * <p>
 * 静态代理实现：参阅{@link UserProxy}类，这种写法是在设计模式中，我们常规的代码写法；
 * </p>
 * <p>
 * 动态代理实现：java-sdk中为我们构建了一个动态代理，参阅{@link Proxy}类。
 * </p>
 * <p>
 * 本例子主要分析一下这个动态代理的怎么完成的？当然此时可以设想一下，静态和动态说白了就是这个{@link UserProxy}类是由开发者自己写还是由框架返回给我们。
 * 假如是框架返回给我们的{@link Proxy}，那指定是，使用反射通过动态实现我们的接口，完成一个动态的代理类的创建。
 * </p>
 * <p>
 * <h2><i>怎么完成这个动态代理类的创建？</i></h2>
 * </p>
 * <p>
 * 对比分析：动态代理与静态代理的区别是，静态代理包含一个{@link UserProxy}类，而动态多了{@link Proxy}和{@link UserHandler}。
 * </p>
 * <p>
 * {@link Proxy}类暂且不做分析，首先看{@link UserProxy}类和{@link UserHandler}类，这两个类的实现，{@link UserHandler}类有
 * 一个invoke方法，而{@link UserProxy}类有一个sayHello方法，实现两个方法的代码是一样的，由此我们可以猜想，这个{@link UserHandler}类
 * 其实是{@link UserProxy}类方法的一个深度业务分离，将业务的再一次抽象分离出来的接口。
 * </p>
 * <p>
 * 我们将{@link UserHandler}类与{@link UserProxy}进行扩展，参看{@link UserExtendProxy}类的实现。此时就差不多有点动态代理的雏形。
 * </p>
 * <p>
 * <h2><i>{@link Proxy}动态代理类的创建？</i></h2>
 * </p>
 */
public class SpringbootProxyApplication {

    public static void main(String[] args) {

        UserInterface u = new User();
        for (Class c : u.getClass().getInterfaces()) {
            System.out.println(c.getName());
        }

        //静态代理
        System.out.println("=======================静态代理=======================");
        // 实例化代理角色对象
        UserInterface userProxy = new UserProxy();
        // 调用了代理对象的sayHello()，其实是调用了真实角色的sayHello()
        userProxy.sayHello();
        //动态代理
        System.out.println("=======================自定义的动态代理=======================");

        UserHandler customHandler = new UserHandler(new User());
        UserExtendProxy proxy = new UserExtendProxy(customHandler);
        proxy.sayHello();

        System.out.println("=======================基于java-sdk Proxy的动态代理=======================");
        User user = new User();
        // 处理类和真实角色绑定
        UserHandler userHandler = new UserHandler(user);
        // 开启将代理类class文件保存到本地模式，平时可以省略
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 动态代理生成代理对象$Proxy0
        Object o = Proxy.newProxyInstance(SpringbootProxyApplication.class.getClassLoader(),
                new Class[]{UserInterface.class},
                userHandler);
        // 调用的其实是invoke()
        ((UserInterface) o).sayHello();

        o = Proxy.newProxyInstance(SpringbootProxyApplication.class.getClassLoader(),
                new Class[]{UserInterface.class},
                userHandler);
        o = Proxy.newProxyInstance(SpringbootProxyApplication.class.getClassLoader(),
                new Class[]{UserInterface.class},
                userHandler);
    }

}
