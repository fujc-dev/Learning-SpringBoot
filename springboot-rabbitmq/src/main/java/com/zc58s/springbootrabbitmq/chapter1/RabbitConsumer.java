package com.zc58s.springbootrabbitmq.chapter1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 消息消费者，代码来源于《RabbitMQ实战指南》
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/2 9:56
 */
public class RabbitConsumer {
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP = "49.233.163.243";
    private static final Integer PORT = 5672;


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("fujc");
        factory.setPassword("123456");

        Address[] addresses = new Address[]{new Address(IP, PORT)};
        //这里的创建连接的方式与Producer略有不同，创建连接
        Connection connection = factory.newConnection(addresses);
        //创建信道
        final Channel channel = connection.createChannel();
        //设置客户端最多接收未被ack的消息个数
        channel.basicQos(64);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recv：" + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        //在真正消费之前，消费者需要向Broker发送Basic.Consume命令，将Channel设置为接收状态。
        channel.basicConsume(QUEUE_NAME, consumer);
        //等待回调函数执行完毕，关闭资源
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
}
