package com.zc58s.springbootrabbitmq.chapter3;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.TimeoutException;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/3 10:46
 */
public class Test1 {
    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routing_key_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP = "49.233.163.243";
    private static final Integer PORT = 5672;


    /**
     * 对第一章的RabbitMQProducer进行异常捕获优化。
     *
     * <p>
     * 总结：
     * </p>
     * <p>
     * 交换器、队列他们之间的相互耦合，是多对多的关系，AA、AB、BA、BB的关系。
     * </p>
     * <p>
     * 通过编写的代码我们可以看到，信道包含交换器、队列以及路由键。信道绑定完成之后，就可以使用basicPublish生产消息。
     * </p>
     * <p>
     * 1、exchangeDeclare：通过改方法绑定信道与交换器。
     * </p>
     * <p>
     * 2、queueDeclare：通过该方法绑定信道与丢列的关系。
     * </p>
     * <p>
     * 3、queueBind：通过改方法绑定队列、交换器以及路由键的关系。
     * </p>
     * <p></p>
     *
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP);
        factory.setPort(PORT);
        factory.setUsername("fujc");
        factory.setPassword("123456");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            //书上说，建议在生产环境不使用isOpen方法，
            //我们从源码的角度去看，isOpen方法是通过判断ShutdownSignalException这个异常是否有值，
            //我们只需要在构建信道的时候，捕获ShutdownSignalException这个异常即可。
            //当然同时还是需要将IOException、SocketException捕获，以防Connection意外关闭。
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);
            //创建一个持久化、非排他的、非自动删除的队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            //使用路由键将队列与交换器绑定起来
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
            //发送消息
            String message = "Hello Word！";
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            //关闭资源
            channel.close();
            connection.close();
        } catch (ShutdownSignalException ex) {  //捕获信道异常

        } catch (SocketException ex) { //捕获连接异常，连接关闭的异常

        } catch (IOException ex) { //输入输出的IO连接异常

        } catch (TimeoutException ex) { //创建连接超时

        } catch (Exception ex) { //其他未知异常

        }
    }
}
