package com.zc58s.springbootbase.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionListener;


/**
 * 监听器随着容器，随着容器加载应用程序时被启动创建。
 * <p>
 * 当前类使用的是{@link ServletContextListener} 监听器，当然还有{@link HttpSessionListener}，用作Session的监听等等。
 * </p>
 * <p>
 * {@link WebListener}：用于将一个类声明为监听器，该注解将会在应用程序部署时被容器处理，
 * 容器根据具体的属性配置将相应的的类部署未监听器。这样我们在Web应用中使用监听时，不需
 * 要在web.xml文件中配置监听器的相关描述信息.
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/13 9:42
 */
@WebListener
public class UserListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(" ------------------> ServletContext上下文初始化 ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(" ------------------> ServletContext上下文销毁 ");
    }
}
