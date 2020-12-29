package com.zc58s.springaopdemo;

import com.zc58s.springaopdemo.aspect.MyAspect;
import com.zc58s.springaopdemo.aspect.MyAspect2;
import com.zc58s.springaopdemo.aspect.MyAspect3;
import com.zc58s.springaopdemo.pojo.User;
import com.zc58s.springaopdemo.service.UserService;
import com.zc58s.springaopdemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication()
public class SpringaopDemoApplication {


    /**
     * 使用切面要在这里注入，写不写名称无所谓，如果是有多个MyAspect，每个都需要注入，
     * <p>如果需要指定顺序执行，需要实现{@link org.springframework.core.Ordered}接口</p>
     * <p>当然，也可以在MyAspect类上增加@{@link Order}注解</p>
     *
     * @return
     */
    @Bean(name = "myAspect")
    public MyAspect initMyAspect() {
        return new MyAspect();
    }

    @Bean
    public MyAspect2 initMyAspect2() {
        return new MyAspect2();
    }

    @Bean
    public MyAspect3 initMyAspect3() {
        return new MyAspect3();
    }


    public static void main(String[] args) {

        SpringApplication.run(SpringaopDemoApplication.class, args);

    }

}
