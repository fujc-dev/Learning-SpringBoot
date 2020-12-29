package com.zc58s.springbootdatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.zc58s.springbootdatabase.dao"})
@EntityScan(basePackages = {"com.zc58s.springbootdatabase.pojo"})
public class SpringbootDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDatabaseApplication.class, args);
    }

}
