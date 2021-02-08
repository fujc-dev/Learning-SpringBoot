

```java

    void basicPublish(String exchange, String routingKey, boolean mandatory, boolean immediate, BasicProperties props, byte[] body) throws IOException;
/**
* 在chapter3中对basicPublish的参数进行了描述，由两个参数没有进行阐述，如下：
* mandatory：表示当交换器没有通过路由键找到符合要求的队列时，消息的处理方式，当为true时，消息就原封不动的返回给生产者，当为false时，丢弃该消息；
* immediate：当设置为true时，表示该消息如果发送到队列时，该队列没有消费者，那么消息将返回给生产者，那么false时，不返回呗，直接投递给消费者。
* 还有一个疑问：直接投递是不是存到队列，然后队列将消息推给消费者？
*/
```