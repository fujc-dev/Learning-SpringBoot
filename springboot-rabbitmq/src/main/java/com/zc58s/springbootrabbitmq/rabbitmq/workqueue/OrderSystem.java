package com.zc58s.springbootrabbitmq.rabbitmq.workqueue;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zc58s.springbootrabbitmq.rabbitmq.utils.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * “ Work queues”模式
 *
 * <p>
 * 使用默认的交换器
 * </p>
 *
 * <p>
 * 消费者
 * </p>
 *
 * @author 白起老师
 * <p>
 * 发送者
 */
public class OrderSystem {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitConstant.QUEUE_SMS, false, false, false, null);

        for (int i = 1; i <= 100; i++) {
            SMS sms = new SMS("乘客" + i, "13900000" + i, "您的车票已预订成功");
            String jsonSMS = new Gson().toJson(sms);
            channel.basicPublish("", RabbitConstant.QUEUE_SMS, null, jsonSMS.getBytes());
        }
        System.out.println("发送数据成功");
        channel.close();
        connection.close();
    }
}
