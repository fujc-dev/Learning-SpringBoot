package com.zc58s.springbootinfdemo;

import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringbootInfdemoApplication {


    public static void main(String[] args) {

        //
        InfNetSdk.INSTANCE.INF_NET_Init();
        //
        SpringApplication.run(SpringbootInfdemoApplication.class, args);
    }

}
