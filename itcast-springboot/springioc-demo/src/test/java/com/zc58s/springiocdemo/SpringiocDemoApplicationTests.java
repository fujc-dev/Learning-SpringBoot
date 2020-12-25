package com.zc58s.springiocdemo;

import com.zc58s.springiocdemo.config.AppConfig;
import com.zc58s.springiocdemo.pojo.ScopeBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class SpringiocDemoApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void scopeBeanTest() {
        //AppConfig配置 扫描路径，其作用就是去定位Bean，然后将Bean放入SpringIoc容器中
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ScopeBean bean1  = ctx.getBean(ScopeBean.class);
        ScopeBean bean2  = ctx.getBean(ScopeBean.class);
        System.out.println(bean1.equals(bean2));
    }

}
