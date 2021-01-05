package com.zc58s.springmvcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zc58s.springmvcdemo.*"})
public class SpringmvcDemoApplication {

    public static void main(String[] args) {
        //
        SpringApplication.run(SpringmvcDemoApplication.class, args);
    }

}
