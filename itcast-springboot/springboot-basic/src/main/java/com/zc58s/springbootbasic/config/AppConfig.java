package com.zc58s.springbootbasic.config;

/*
 * springboot-basic
 * com.zc58s.springbootbasic.config
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/24 14:42
 */

import com.zc58s.springbootbasic.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * {@link Configuration}
 * <p>
 *
 * @Configuration 代表这是一个java的配置文件，Spring容器会根据它来生成Ioc容器去装备Bean；
 * </p>
 * <p>
 * @Bean 代表将initUser方法返回的POJO装配到Ioc容器中，而其属性name定义这个Bean名称，
 * 如没有没有为Bean配置名称，则将方法名称“initUser”作为Bean的名称保存到Spring Ioc容器中。
 * </p>
 * <p>
 * @ComponentScan 表明使用何种策略去扫描装配的Bean
 * </p>
 */
@Configuration
//关于扫描ComponentScan配置的几种写法
//@ComponentScan  //不带任何参数的扫描，仅仅值扫描当前报包下面的文件
//@ComponentScan("com.zc58s.springbootbasic.*")
//@ComponentScan(basePackages = "com.zc58s.springbootbasic.*")
@ComponentScan(basePackages = {"com.zc58s.springbootbasic.pojo.*"})
//@ComponentScan(basePackageClasses = Company.class)
//@ComponentScan(basePackageClasses = {Company.class})

public class AppConfig {

    /**
     * @return
     */
    @Bean
    public User initUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("傅均承");
        user.setNode("野生JAVA开发工程师");
        return user;
    }

    /**
     * 在使用了@Component注解的Company类中的属性使用@Value注解，那么此时再注入Company的Bean，标记了@Value标签的属性的值，好像是改不掉的。
     * @return
     */
    @Bean("test")
    public Company getDefaultUserInfo() {
        Company company = new Company();
        company.setName("四川旷谷信息工程有限公司x");
        return company;
    }
}
