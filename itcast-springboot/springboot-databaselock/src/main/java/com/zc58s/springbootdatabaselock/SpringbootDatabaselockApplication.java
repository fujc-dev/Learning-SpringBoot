package com.zc58s.springbootdatabaselock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringbootDatabaselockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDatabaselockApplication.class, args);
    }

}
