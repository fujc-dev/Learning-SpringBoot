package com.zc58s.springsecuritydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zc58s.springsecuritydemo.*"})
public class SpringsecurityDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringsecurityDemoApplication.class, args);
    }

}
