package com.zc58s.springaopdemo;

import com.zc58s.springaopdemo.proxy.ProxyBean;
import com.zc58s.springaopdemo.service.HelloService;
import com.zc58s.springaopdemo.service.impl.HelloServiceImpl;
import com.zc58s.springaopdemo.service.impl.MyInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringaopDemoApplicationTests {

    @Test
    void contextLoads() {
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());

        HelloService proxy1 = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("傅均承");

    }

}
