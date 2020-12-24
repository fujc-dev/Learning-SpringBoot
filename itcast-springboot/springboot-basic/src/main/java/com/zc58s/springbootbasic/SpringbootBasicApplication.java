package com.zc58s.springbootbasic;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Springboot默认启动类
 * <p>
 * 1、需要在类上添加一个{@link SpringBootApplication}的注解
 * </p>
 * <p>
 *
 * </p>
 */
@SpringBootApplication
public class SpringbootBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBasicApplication.class, args);
    }

}
