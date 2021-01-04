package com.zc58s.springbootredis;

import com.zc58s.springbootredis.redis.SeckillBusinessService;
import com.zc58s.springbootredis.redis.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootRedisApplication {

    public static void main(String[] args) {

        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            SeckillBusinessService businessService = new SeckillBusinessService(service);
            businessService.setDaemon(true);
            businessService.start();
        }

        SpringApplication.run(SpringbootRedisApplication.class, args
        );
    }

}
