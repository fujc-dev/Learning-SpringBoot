package com.zc58s.springbootbasic;

import com.zc58s.springbootbasic.config.AppConfig;
import com.zc58s.springbootbasic.config.Company;
import com.zc58s.springbootbasic.pojo.User;
import com.zc58s.springbootbasic.pojo.definition.impl.BusinessPearson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class SpringbootBasicApplicationTests {

    /**
     * 代码中，java配置文件{@link AppConfig}传递给{@link AnnotationConfigApplicationContext}的构造方法。
     * 这样它就能够读取配，然后将配置里面的Bean装配到Ioc容器中，于是就可以使用getBean方法获取对应的POJO。
     * <p>
     *
     * </p>
     */
    @Test
    void contextLoads() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = ctx.getBean(User.class);
        System.out.println(user.toString());
        //修改注入到容器的Bean对象
        user.setUsername("王大锤");
        //获取的值是修改后的值
        user = ctx.getBean(User.class);
        System.out.println(user.toString());
    }

    @Test
    void valueTest() {
        Company company = new Company();
        company.setName("四川旷谷信息工程有限公司x");
        System.out.println(company.toString());
    }

    @Test
    void diTest() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        BusinessPearson pearson = ctx.getBean(BusinessPearson.class);
        pearson.service();
    }

}
