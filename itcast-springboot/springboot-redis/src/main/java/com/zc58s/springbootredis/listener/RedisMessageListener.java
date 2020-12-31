package com.zc58s.springbootredis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/31 14:55
 * springboot-redis
 * com.zc58s.springbootredis.listener
 */
public class RedisMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String topic = new String(bytes);
        String content = new String(message.getBody());
        System.out.println(topic);
        System.out.println(content);

    }
}
