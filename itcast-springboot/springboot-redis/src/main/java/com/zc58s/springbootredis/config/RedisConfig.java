package com.zc58s.springbootredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/30 13:43
 * springboot-redis
 * com.zc58s.springbootredis.config
 */
@Configuration
public class RedisConfig {

    private RedisConnectionFactory connectionFactory;

    /**
     * Redis连接，redis-cli.exe -h 127.0.0.1 -p 6379
     * <p>
     * auth 123456  验证密码
     * config set requirepass 123456 设置密码
     * config get requirepass 获取密码
     * </p>
     *
     * @return
     */
    @Bean
    public RedisConnectionFactory initRedisConnectionFactory() {
        if (this.connectionFactory != null) {
            System.out.println("redis已完成初始化。。。");
            return this.connectionFactory;
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(30);  //最大空弦数
        config.setMaxTotal(50); //最大连接数
        config.setMaxWaitMillis(2000); //最大等待毫秒数
        JedisConnectionFactory factory = new JedisConnectionFactory(config);
        RedisStandaloneConfiguration standaloneConfiguration = factory.getStandaloneConfiguration();
        standaloneConfiguration.setHostName("127.0.0.1"); //49.233.163.243
        standaloneConfiguration.setPort(6379);
        standaloneConfiguration.setPassword("123456");
        return this.connectionFactory = factory;
    }

    /**
     * {@link RedisTemplate}是操作Redis的关键
     *
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> initRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(this.connectionFactory);
        return redisTemplate;
    }

}
