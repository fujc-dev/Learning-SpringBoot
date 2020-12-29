package com.zc58s.springaopdemo;

import com.zc58s.springaopdemo.aspect.MyAspect;
import com.zc58s.springaopdemo.pojo.User;
import com.zc58s.springaopdemo.service.UserService;
import com.zc58s.springaopdemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class SpringaopDemoApplication {


    @Bean(name = "myAspect")
    public MyAspect initMyAspect() {
        return new MyAspect();
    }

    public static void main(String[] args) {

        SpringApplication.run(SpringaopDemoApplication.class, args);

//        UserService service = new UserServiceImpl();
//        User user = new User();
//        user.setId(10001L);
//        user.setUsername("秋末-");
//        user.setNode("有些事情不努力下都不知道什么是绝望");
//        service.printUser(new User());
    }

}
