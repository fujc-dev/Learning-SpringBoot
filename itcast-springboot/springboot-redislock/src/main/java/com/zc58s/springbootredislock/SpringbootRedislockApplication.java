package com.zc58s.springbootredislock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringbootRedislockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedislockApplication.class, args);
    }

    @Autowired
    private Environment env;
    @Bean
    public Redisson redisson() {
// 创建配置实例
        Config config = new Config();
        // 传输模式既可以设置为EPOLL，也可以设置为NIO等
        config.setTransportMode(TransportMode.NIO);
        // 设置服务节点部署模式: 集群、单一节点/主从模式/哨兵模式
        // config.useClusterServers().addNodeAddress(env.getProperty("redisson.host.config"), env.getProperty("redisson.host.config"));
        config.useSingleServer().setAddress(env.getProperty("redisson.host.config")).setKeepAlive(true);
        return (Redisson) Redisson.create(config);
    }
}
