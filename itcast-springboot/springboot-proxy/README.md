#
### WeakCache<K, P, V>
```java

//队列，弱引用队列
private final ReferenceQueue<K> refQueue = new ReferenceQueue<>();
//map 缓存的底层容器实现, key为一级缓存, value为二级缓存。 为了支持null, map的key类型设置为Object
//这里面存放的是两个map集合
//map集合中嵌套map集合
//在get方法的时候，很多翻译成一级缓存，二级缓存，很容易把人搞懵逼，如果先看这两个map的嵌套，可以更好的理解。
private final ConcurrentMap<Object, ConcurrentMap<Object, Supplier<V>>> map = new ConcurrentHashMap<>();
//map reverseMap记录了所有代理类生成器是否可用, 这是为了实现缓存的过期机制
private final ConcurrentMap<Supplier<V>, Boolean> reverseMap = new ConcurrentHashMap<>();
// key KeyFactory 通过K，P参数创建map的key，对key的创建
private final BiFunction<K, P, ?> subKeyFactory;
// value ProxyClassFactory，通过K，P参数创建一个map的value，而这个value的类型就是V，对value的创建，.class的生成
private final BiFunction<K, P, V> valueFactory;
```
```text
WeakCache：指的是缓存,K，P，V是泛型。
这个WeakCache<K, P, V>，这个类在java.lang.reflect包下，创建出来的目的就是为了反射服务的。想法就局限在这个角度，能方便代理的理解。
V,代表存储的值
K,key值
P,参数
```


### 针对动态创建Proxy时，缓存在其中的作用
```java
    
        /**
        *    key：null
        *    parameter：这个是本例中我们UserInterface接口的class，
        *    ConcurrentMap<Object, ConcurrentMap<Object, Supplier<V>>>这个是一个map包map的数据结构 
        */
        public V get(K key, P parameter) 
        {
            //parameter参数不能为空，如果要为某一个类创建代理，那么这个类必须是接口的实现类
            //本例，User与UserInterface的关系：User implements UserInterface
            Objects.requireNonNull(parameter);
            //清除过期的缓存
            expungeStaleEntries();
            //在本例中，将ClassLoader包装成CacheKey, 作为一级缓存的key
            //因为key值为null，此时的cacheKey为NULL_KEY = new Object()
            //
            Object cacheKey = CacheKey.valueOf(key, refQueue);
            //再通过一级缓存，获取得到二级缓存
            //一般情况下，第一次为对象创建动态代理时，valuesMap = null
            //当然还有二般情况，我之前已经创建过动态代理，此时可能就不为null，那就说明这个构建的代理对象已存在
            //当然还有三般情况，就是之前创建的动态代理，因为内存紧张已经被回收了，那么此时还是valuesMap = null
            ConcurrentMap<Object, Supplier<V>> valuesMap = map.get(cacheKey);
            //如果根据ClassLoader没有获取到对应的值
            if (valuesMap == null) {
                //1、putIfAbsent是线程安全的put
                //2、放入数据时，如果key重复，那么不会把值存入Map中
                //3、如果key对应的value已存在，则返回value
                //4、如果key对应的value不存在，则返回null
                //因为valuesMap是null。那么map.putIfAbsent执行后返回的还是null。
                //
                ConcurrentMap<Object, Supplier<V>> oldValuesMap = map.putIfAbsent(cacheKey, valuesMap = new ConcurrentHashMap<>());
                //此时这一步，那就是永远不会执行，有啥子意义?
                if (oldValuesMap != null) {
                    valuesMap = oldValuesMap;
                }
            }
            //Objects.requireNonNull 空指针检测，如果为null，则抛出一个NullPointerException
            //subKeyFactory.apply(key, parameter)，可以直接将其理解为产生map的key，内部是一个Key1，Key2，KeyX对象。
            //这个key指的是ClassLoader+UserInterface这两个对象为参数构建的key。
            Object subKey = Objects.requireNonNull(subKeyFactory.apply(key, parameter));
            //因为，这个子的map，文档上成为二级缓存。
            //去子的map里面找一下，有没有ClassLoader+UserInterface的二级缓存
            Supplier<V> supplier = valuesMap.get(subKey);
            //Factory对象必须要说说，感觉套这么多层，好复杂的
            //Factory初略一看，还以为是个工厂，用于创建对象；
            //Factory的构造函数，里面包含了ClassLoader,UserInterface,子map的key,以及子map。
            //但是后面supplier = factory这句，初略一看有点懵逼
            //我们的子map对象的类型为ConcurrentMap<Object, Supplier<V>>
            //分析：首先Factory实现了Supplier<V>接口。
            //内部有一个get的带返回值的方法，这个返回值就是我们需要的代理对象。
            //Proxy动态代理的的核心创建就在这里，创建了一个$Proxy0.class对象
            Factory factory = null;
            //============================================================================
            //写入的相关操作，替换，获取阶段
            
            //这个循环的代码解析：
            //第一段：
            //1、supplier不null，那么supplier.get()去创建.class的对象
            //2、当value有值，直接返回
            //3、当value没有值时，就需要通过factory = new Factory(key, parameter, subKey, valuesMap)去创建
            //4、然后通过valuesMap.replace(subKey, supplier, factory)。替换在缓存中的supplier
            //5、将我们创建的新的factory赋值给supplier
            //6、再一次的循环。完成代理对象的.class创建
            //第二段：
            //1、supplier为null
            //2、要通过factory = new Factory(key, parameter, subKey, valuesMap)去创建
            //3、我们创建的新的factory赋值给supplier
            //4、再一次的循环。完成代理对象的.class创建
            while (true) {
                if (supplier != null) {
                    V value = supplier.get();
                    if (value != null) {  //动态创建.class失败。目前来看的，那就是valueFactory.apply(key, parameter)执行出问题了才会等于Null
                        return value;
                    }
                }
                //第一次循环factory肯定是null，肯定是构建Factory
                if (factory == null) {
                    factory = new Factory(key, parameter, subKey, valuesMap);
                }
                //第一次循环supplier肯定也是为null，但是此时factory不会null
                if (supplier == null) {
                    //将子map构建出来了。
                    //因为valuesMap.putIfAbsent特性，supplier依旧为null
                    supplier = valuesMap.putIfAbsent(subKey, factory);
                    if (supplier == null) {
                        //将我们构建到的factory复制给Supplier<V>接口
                        supplier = factory;
                    }
                // 为什么要写这个呢?
                } else {
                    //替换原有值，
                    if (valuesMap.replace(subKey, supplier, factory)) {
                        //将新值赋给supplier
                        supplier = factory;
                    } else {
                        supplier = valuesMap.get(subKey);
                    }
                }
            }
        }

```

### valueFactory.apply(key, parameter) 创建.class文件
```java
private static final class ProxyClassFactory implements BiFunction<ClassLoader, Class<?>[], Class<?>>
{
        // prefix for all proxy class names
        private static final String proxyClassNamePrefix = "$Proxy";

        // next number to use for generation of unique proxy class names
        private static final AtomicLong nextUniqueNumber = new AtomicLong();
    
            
        /**
        * .class的生成。
        */
        @Override
        public Class<?> apply(ClassLoader loader, Class<?>[] interfaces) {
            //interfaces 接口类型，也就是.class
            //在本例中为UserInterface.class
            Map<Class<?>, Boolean> interfaceSet = new IdentityHashMap<>(interfaces.length);
            //下面这个循环，好像是用来验证对象有效性的
            for (Class<?> intf : interfaces) {
                Class<?> interfaceClass = null;
                try {
                    //获取UserInterface的class
                    //String name .class包名称
                    //boolean initialize ，false，不实例化，为什么要传false呢？这个与Class.forName有关
                    //默认Class.forName会初始化，因为是接口，没有需要被初始化的，所以传false
                    //参考：com.zc58s.springbootproxy.demo.Simple
                    //这样子，嘿嘿，这个static方法是会执行的。
                    //Class c = Class.forName(Simple.class.getName(), false, null);
                    //这样子是不执行的。
                    //c = Simple.class;
                    //ClassLoader loader 类加载器
                    interfaceClass = Class.forName(intf.getName(), false, loader);
                } catch (ClassNotFoundException e) {
                }
                if (interfaceClass != intf) {
                    throw new IllegalArgumentException(
                        intf + " is not visible from class loader");
                }
                if (!interfaceClass.isInterface()) {
                    throw new IllegalArgumentException(
                        interfaceClass.getName() + " is not an interface");
                }
                if (interfaceSet.put(interfaceClass, Boolean.TRUE) != null) {
                    throw new IllegalArgumentException(
                        "repeated interface: " + interfaceClass.getName());
                }
            }

            String proxyPkg = null;     // package to define proxy class in
            int accessFlags = Modifier.PUBLIC | Modifier.FINAL;
            for (Class<?> intf : interfaces) {
                //获取接口中方法的修饰符
                int flags = intf.getModifiers(); 
                // 验证接口是否是public，如果不是public，那么这个类文件就需要创建到指定的包文件中才能被访问。
                // 同一个包下的所有class文件可以不用public进行访问
                // 我们的UserInterface是public，所以跳过                
                if (!Modifier.isPublic(flags)) {
                    accessFlags = Modifier.FINAL;
                    String name = intf.getName();
                    int n = name.lastIndexOf('.');
                    String pkg = ((n == -1) ? "" : name.substring(0, n + 1));
                    //假如我们的UserInterface是没有public，那么此时取的pkg=com.zc58s.springbootproxy.service
                    //那么我们创建的$Proxy0.class文件，就会被存放到com.zc58s.springbootproxy.service这个路径下。
                    //因为只有这样子，$Proxy0.class才能实现UserInterface接口。
                    if (proxyPkg == null) {
                        proxyPkg = pkg;
                    } else if (!pkg.equals(proxyPkg)) {
                        throw new IllegalArgumentException(
                            "non-public interfaces from different packages");
                    }
                }
            }

            if (proxyPkg == null) {
                proxyPkg = ReflectUtil.PROXY_PACKAGE + ".";
            }
            long num = nextUniqueNumber.getAndIncrement();
            String proxyName = proxyPkg + proxyClassNamePrefix + num;
            //生产.class文件，最主要的就是这个，这里面花里胡哨的东西太多了，看着有难度，反正我没看。
            //
            byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags);
            try {
                // 通过创建的.class文件，然会返回我们所需的.class
                return defineClass0(loader, proxyName,proxyClassFile, 0, proxyClassFile.length);
            } catch (ClassFormatError e) {
                throw new IllegalArgumentException(e.toString());
            }
        }
}
```

### cons.newInstance(new Object[]{h}); 
```text

创建类实例
```