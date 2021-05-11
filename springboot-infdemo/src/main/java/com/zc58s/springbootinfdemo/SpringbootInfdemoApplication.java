package com.zc58s.springbootinfdemo;

import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringbootInfdemoApplication {


    public static void main(String[] args) {
        //
        System.out.println("InfNetSdk Init Begin ...");
        InfNetSdk.INSTANCE.INF_NET_Init();
        System.out.println("InfNetSdk Init End ...");
        //
        SpringApplication.run(SpringbootInfdemoApplication.class, args);
    }

}
