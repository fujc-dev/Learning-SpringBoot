package com.zc58s.springbootredis;

import com.zc58s.springbootredis.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void contextRedisTemplate() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForHash().put("hash", "field", "value");

        System.out.println(redisTemplate.opsForValue().get("key1"));
        //



    }
    @Test
    void  clearRedis(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        redisTemplate.delete("key1");
        redisTemplate.delete("key2");
        redisTemplate.delete("key3");
        redisTemplate.delete("hash");
    }

}
