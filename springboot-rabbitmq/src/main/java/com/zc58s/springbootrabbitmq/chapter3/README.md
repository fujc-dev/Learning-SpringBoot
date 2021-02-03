

#### exchangeDeclare 交换器声明
```java
//通过信道创建交换器方法。这是一个没有缺省所有参数的方法。
Exchange.DeclareOk exchangeDeclare(String exchange,
                                              String type,  //还有一个重载方法，该类型是BuiltinExchangeType，用枚举作为参数，书上说这个居然不常用，非常尴尬。
                                              boolean durable,
                                              boolean autoDelete,
                                              boolean internal,
                                              Map<String, Object> arguments) throws IOException;


/**
* exchange：交换器的名称；
* type：交换器的类型，常见的如：fanout、direct、topic、（headers）；
* durable：设置是否持久化。durable默认为true表示持久化，反之是非持久化。持久化表示将交换器存盘，在RabbitMQ服务被重启后，不会丢失相关信息；
* autoDelete：设置是否自动删除，autoDelete默认true表示自动删除，自动删除的前提是至少有一个队列或者交换器我我们当前声明的交换器进行绑定，然后呢
    之后所有与我们声明的这个交换器绑定的队列或者交换器都与我们声明的这个交换器解绑，然后才会删除这个交换器。另外，不能错误的简单理解为：我们声明
    的交换器与客户端断开时，RabbitMQ会自动删除我们声明的交换器。
    另外：这里有个非常难以理解的概念，就是我们声明的交换器与另外的交换器绑定，交换器怎么会与交换器绑定呢?交换器有内置交换器与对外交换器之分，可以通过
    下一个参数internal（内部的）进行理解。
* internal：设置是否是内置交换器，如果设置为true，则表示是内置交换器，客户端程序无法直接发送消息到这个交换器，只能通过交换器i路由到交换器的方法发送消息。
* arguments：其他的一些结构化参数，截至目前位置，我暂时还不明白具体的意思。
    分析：但是，我在看到type=headers参数时，我们将消息里面封装headers参数的，然后在
    声明交换器的时候，将我们的headers参数封装到交换器中，然后发送的消息匹配到相同的headers的时候，将消息分配的指定的队列，
    不知道这个arguments有没有这个作用，只能在后续的篇幅中找答案了。
*/
```

#### queueDeclare 队列声明
```java
    Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,
                                 Map<String, Object> arguments) throws IOException;


/**
* queue：队列名称
* durable：durable默认为true表示持久化，反之是非持久化。持久化表示将交换器存盘，在RabbitMQ服务被重启后，不会丢失相关信息；
* exclusive：设置是否排他，为true则设置队列为排他队列。如果一个队列被声明为排他队列，该队列j仅对首次声明它的连接有效，并在连接断开后自动删除。
    注意事项：
        1、排他队列是基于连接可见的，同一个连接的不同信道是可以同时同一连接创建的排他队列；
        2、首次。是指如果一个连接已经声明了排他队列，其他连接不允许建立同名的排他队列，这个与普通队列不同；
        3、即使该队列是持久化的，一旦连接关闭或者客户端退出，该排他队列都会被自动删除，这种队列适用于一个客户端同时发送和读取消息的应用场景。
* autoDelete：设置是否自动删除，autoDelete默认true表示自动删除。
    自动删除的前提是：生产者在创建队列之后，有消费者连接到这个队列，之后，在消费者都对这个队列断开连接时，才会自动删除；
    注意事项：在生产者创建队列后，没有队列连接，就算是生产者断开连接，队列也不会被删除。
* arguments：设置队列的其他参数。
*/
```

#### queueBind队列绑定

```java
    Queue.BindOk queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments) throws IOException;
    //书上说，该方法不仅可以将队列与交换器绑定，而且还可以将已经绑定的队列和交换器进行解绑。

/**
* queue：队列名称
* exchange：交换器名称
* routingKey：用来绑定队列和交换器的路由键
* arguments：定义绑定参数，这个参数暂时还没有理解到底是什么意思。
*/
```


#### exchangeBind 交换器绑定
 ```java
    //
    Exchange.BindOk exchangeBind(String destination, String source, String routingKey, Map<String, Object> arguments) throws IOException;
    
/**
* 交换器绑定，该方法是应对之前在绑定交换器时，有一个参数是internal，表示内部交换器，因为内部交换器无法直接与消费者协作，所以出现了交换器绑定。
* 我们使用exchangeBind绑定另外一个非internal的交换器，可以使我们将source交换器的消息转发到destination交换器上。
* destination：内部的交换器
* source：对外暴露的交换器名称
* routingKey：路由键，
* arguments：个参数暂时还没有理解到底是什么意思。
*/
```

#### basicPublish 生产消息
```java
    void basicPublish(String exchange, String routingKey, boolean mandatory, boolean immediate, BasicProperties props, byte[] body)
            throws IOException;

/***
* exchange：交换器名称，指明消息需要发送到那个交换器中，如果设置为空字符串，则消息会发送到RabbitMQ默认的交换器中。
* routingKey：路由键，交换器根据路由键将消息存储到相应的 队列之中。
* mandatory：后续第四章第一节详解
* immediate：后续第四章第一节详解
* props：消息的基本属性，书上说有14中，也就是这个对象有14个构造函数，但是我看源码里面对contentType的限制好像有5中，其他的需要后续了解。
* body：消息的具体类型，二进制byte格式
*/

```

#### routingKey 路由键详解

   ```text
    路由键与交换器的类型有关系。
    目前比较通过的交换器有：
    fanout：假如我们绑定了fanout类型的交换，那么

    topic：

    direct：
```
