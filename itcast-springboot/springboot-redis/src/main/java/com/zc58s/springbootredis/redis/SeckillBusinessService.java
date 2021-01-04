package com.zc58s.springbootredis.redis;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/4 9:39
 * springboot-redis
 * com.zc58s.springbootredis.redis
 */
public class SeckillBusinessService extends Thread {
    private Service service;

    public SeckillBusinessService(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}
