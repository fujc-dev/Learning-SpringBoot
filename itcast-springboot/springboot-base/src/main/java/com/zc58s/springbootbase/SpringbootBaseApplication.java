package com.zc58s.springbootbase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
@ServletComponentScan  //使用该注解后，@WebServlet @WebFilter @WebListener注解自动注册，无需其他代码。
public class SpringbootBaseApplication {


    public static void main(String[] args) {
        //
        SpringApplication.run(SpringbootBaseApplication.class, args);
    }

}
