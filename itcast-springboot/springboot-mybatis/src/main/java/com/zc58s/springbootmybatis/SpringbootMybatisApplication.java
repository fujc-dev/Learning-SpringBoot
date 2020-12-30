package com.zc58s.springbootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.zc58s.springbootmybatis.dao")
@SpringBootApplication
public class SpringbootMybatisApplication {

    public static void main(String[] args) {
        //
        SpringApplication.run(SpringbootMybatisApplication.class, args);
    }

}
