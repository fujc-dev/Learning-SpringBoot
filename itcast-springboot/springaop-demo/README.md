# 约定编程 - Spring AOP

### java.lang.reflect.Proxy实现动态代理
```$xslt
    暂时不知道java.lang.reflect.Proxy到底能玩出多大的花样。
    就目前我的理解来说，Proxy就是一个代理对象，想象成代理模式，单纯的理解这个这个代理模式要简单的多。
    代理模式原型：
        Subject类是一个抽象的方法类型；
        RealSubject是一个具体的实现类;
        Proxy是一个代理类，代理依赖RealSubject类，并且也同样实现Subject接口,在实现Subject接口的方法中同时会添加一些代理的信息。
    比如：我要看电影，正版的看不了，但是通过盗版网赞，我拿到了我想看的电影，
    但是，这个电影有一些莫名其妙的内置小广告（内置小广告这件事情就特么是代理干的）。

    java.lang.reflect.Proxy是就是SDK帮我们写好了的代理外壳，我们只需要想里面添加小广告就好了，连工具都准备好了，
    InvocationHandler就的这个工具，我们只需要实现invoke方法，就能添加我们自己的小广告。
    当然，还可以扩展，例如本来，我们就抽象了一个小广告业务（Interceptor），
    然后自己实现(MyInterceptor)，
    在通过一个简单工厂包装一下(ProxyBean.getProxyBean)，
    具有自己的特色。

    那么问题来了，java.lang.reflect.Proxy到底是怎么完成的？
    来看Proxy.newProxyInstance(p1,p2,p2)的几个参数；
    /**
    * ClassLoader loader：使用那个加载器去加载这个代理对象，用其他加载器有啥区别?
    * Class<?>[] interfaces,被代理的对象的接口
    * InvocationHandler h 代理需要做的那些使用
    */    
    简单猜测：
    1、ClassLoader，那没得说，类加载器，java中所有的class二进制文件都是这玩意儿完成解析的。
    2、另外一个参数是一个接口（这传递进去的是接口的Class对象，可以将其理解为.NET里面的类型），拿到了Class对象，就那到了方法。
    3、最后一个参数是InvocationHandler，表示的是代理具体要做的事情。
    最后关键的来了。
    能拿到类加载器，能拿到方法，能拿到具体要做的事情， 那么构建一个代理类就出来了（这是我的理解，但是怎么完成构建这个代理类的，我还需要继续学习）。
    Proxy.newProxyInstance返回的是一个Object，这个Object从定义来讲，返回的按理是继承自第二个参数的类的实例，
    也就是我们代理模式中Proxy类（就是已经被添加了小广告的产物）。

    单论反射，类加载器，类实例都能理解。
    

        
```

