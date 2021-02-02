package com.zc58s.springbootrabbitmq.chapter1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 消息生产者，代码来源于《RabbitMQ实战指南》
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/2 9:46
 */
public class RabbitProducer {

    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routing_key_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP = "49.233.163.243";
    private static final Integer PORT = 5672;


    /**
     * Producer =>  message  =>   Exchange => Routing Key => Queue
     *
     * <p><b><i>Exchange 、RoutingKey与BindingKey之间关系分析</i></b></p>
     * <p>
     * 在书中，我们看到作者将RabbitMQ比作，将包裹送到邮局柜台，邮局会暂存并最终通过邮递员将包裹送到收件人收中，
     * RabbitMQ在其中扮演的角色：邮局柜台、邮箱（仓库）、邮递员。
     * </p>
     * <p><b>Exchange：</b></p>
     * <p>相当于邮局柜台或者邮箱。</p>
     * <p><b>RoutingKey：</b></p>
     * <p>相当于填写在包裹上收件人地址。</p>
     * <p><b>BindingKey：</b></p>
     * <p>相当于包裹上的目的地。/p>
     *
     * <p>
     * 在本例中我们的RoutingKey与BindingKey，是同一个值，从语义上来看，也差不多一个意思。
     * </p>
     * <p>
     * 但是，书中也做了另外的解释，就是说，官方的API中说，RoutingKey与BindingKey，其实是把BindingKey当作RoutingKey。
     * 另外，还有一个通俗理解方式，我们在生产者创建消息时，需要设置queueBind以及推送的basicPublish时，BInd传递的为BindingKey，
     * 而Publish传递的为RoutingKey。
     * </p>
     *
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP);
        factory.setPort(PORT);
        factory.setUsername("fujc");
        factory.setPassword("123456");

        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //创建一个type=direct、持久化、非自动删除的交换器
        /**
         * String exchange  交换器名称
         * BuiltinExchangeType type 交换器类型
         * boolean durable 是否持久化，数据落盘
         * boolean autoDelete 设置自动删除这个交换器，这里有一个具体删除交换器是在什么时候？
         *  书上原话是，首先你得进行了绑定（至少有一个队列或者交换器与这个交换器进行了绑定），然后绑定被解除 ，才能删。
         * Map<String, Object> arguments 一些结构化参数？
         */
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);
        //创建一个持久化、非排他的、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //将交换器与队列通过路由键绑定
        //
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        //发送消息
        String message = "Hello Word！";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        //关闭资源
        channel.close();
        connection.close();
    }
}
