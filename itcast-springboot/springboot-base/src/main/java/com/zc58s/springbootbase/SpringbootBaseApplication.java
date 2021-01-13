package com.zc58s.springbootbase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
@ServletComponentScan
public class SpringbootBaseApplication {


    public static void main(String[] args) {
        //
        SpringApplication.run(SpringbootBaseApplication.class, args);
    }

}
