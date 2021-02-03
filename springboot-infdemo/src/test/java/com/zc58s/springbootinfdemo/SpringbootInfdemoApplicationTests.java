package com.zc58s.springbootinfdemo;

import com.zc58s.springbootinfdemo.jna.request.LoginRequest;
import com.zc58s.springbootinfdemo.jna.response.LoginResponse;
import com.zc58s.springbootinfdemo.jna.response.PtzResponse;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import com.zc58s.springbootinfdemo.jna.service.IPlatformService;
import com.zc58s.springbootinfdemo.jna.service.IPztControlService;
import com.zc58s.springbootinfdemo.jna.service.impl.VideoPlatformServiceImp;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SpringbootInfdemoApplicationTests {

    @Autowired
    private IPztControlService service;

    @Test
    void contextLoads() {

        try {
            System.out.println(service.PtzLeft("111"));
            IPlatformService platformService = new VideoPlatformServiceImp();
            InfNetSdk.INSTANCE.INF_NET_Init();
            String version = InfNetSdk.INSTANCE.INF_NET_GetVersion();
            System.out.println("SDK Version: " + version);
            LoginRequest request = new LoginRequest("", "", "");
            LoginResponse response = platformService.Login(request);
            System.out.println(response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Test
    void log() {
        Logger logger = LoggerFactory.getLogger(SpringbootInfdemoApplicationTests.class);
        logger.debug("This is a debug message");//注意 spring 默认日志输出级别为 info 所以默认情况下 这句不会打印到控制台
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
    }

}
