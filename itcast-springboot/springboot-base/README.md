## SpringBoot中application.properties基础

### .properties与.yml
```text
    在同一个目录中，包含相同的.properties与.yml，.properties的优先级比.yml优先级高，SpringBoot将使用.properties中的配置。
```

### SpringBoot配置文件的加载顺序

```text
    1. 项目根目录中的config/application.properties
    2. 项目根目录下的application.properties
    3. 项目src/main/resources/config/下的application.properties
    4. 项目src/main/resources/下的application.properties（项目创建自带）
```

### SpringBoot中resources资源


### pom文件概述
```text
    
        spring-boot-starter-parent：是一个特殊的starter，用来提供相关的Maven的默认依赖，
    使用它之后，常用的包依赖可以省去version标签；
        spring-boot-maven-plugin：这个是一个Maven插件，能够以Maven的方式为应用程序提供SpringBoot支持，
    即为SpringBoot应用提供执行Maven操作的可能，能够将SpringBoot应用打包为jar或war。
```
其他参见：[Spring Boot Starter依赖包及作用 ](http://note.youdao.com/noteshare?id=7fcd1efd9d83eb271748b7e0d7fc3051&sub=73D9C40C38E249939BE802FD11B252EB) 


### JPA中涉及到的注解解释
```text

    @Component：泛指组件，只要学习Spring框架必须对这个注解进行详细深入的了解，我们涉及到所有的类需要被导入时，
都可以使用该组件 ，与Component相同类型的分支，有@Service，@Repository，@Controller等

    @Service ：      与Component注解意义一样，只是为了代码的可读性，我们将一些注解进行分类，用Service表示是服务层导入。


    @Repository：同上，@Repository表示仓储或者持久层需要被导入的实现类

    @Resource：该注解属于J2EE，默认按名称进行装配，名称可以通过name属性进行指定。
如果没有指定name属性，当注解写在字段上（成员变量）时，就默认取字段名称进行查询，
如果注解写在setter上，就默认取属性名进行装配，当找不到与名称匹配的bean时，才按照类型进行
装配。但需要注意的是，name属性一旦指定，就只会按照名称进行装配，具体代码如下：

    @Resource(name="userRepository")
    private UserRepository repository;

    @Autowired： 该注解属于Spring的注入标注，默认按照类型进行装配，要求依赖对象必须存在，
如果可以为null，需要在Autowired中配置required = false进行声明，如果需要使用按照名称装配，
需要结合@Qualifier()注解使用，具体代码如下：
    @Autowired
    @Qualifier("userRepository")
    private UserRepository repository;
    
    另外

    @Autowired
    public UserServiceImpl(@Qualifier("userRepository") UserRepository repository) {
    
    }



    
```
其他参见：[SpringIoc案例](https://github.com/fujc-dev/Learning-SpringBoot/tree/main/itcast-springboot/springioc-demo)


###
```java
   


```