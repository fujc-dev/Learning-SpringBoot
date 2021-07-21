package com.kggs.wg5000sdk;

import com.kggs.wg5000sdk.factory.WG5000Factory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Wg5000sdkApplication {

    public static void main(String[] args) {
        //
        SpringApplication.run(Wg5000sdkApplication.class, args);
        //
        WG5000Factory.Open("127.0.0.1", 6000, "zhangsan", "zhangsan", "10001");
    }

}
