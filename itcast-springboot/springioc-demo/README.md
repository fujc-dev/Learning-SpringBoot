# SpringIoc 学习记录

### 标记了@Component但是getBean会失败
```$xslt
   1、确定是否真的标记了@Component注解；
   2、确定ComponentScan扫描包的地址，是否涵盖了被标记的类。
```

## @ComponentScan详解
### @ComponentScan参数加\*与不加\*的区别
```$xslt
    1、com.zc58s.springbootbasic.*
    2、com.zc58s.springbootbasic
        不加*，表示当前包文件夹下的所有类文件
        加*，表示当前包文件夹下所有文件夹中的所有类文件
```

### @ComponentScan 参数描述
```$xslt
    basePackages：填写需要扫描的包地址，说白了就是你要扫描的文件夹地址
    basePackages可以传递数组。格式：{"package1","package1"}
    basePackageClasses：扫描具体的类
    basePackageClasses也可以传递数组。格式： {A.class,B.class}
```

### @Scope作用域 单例，多实例
```$xslt
ConfigurableBeanFactory.SCOPE_PROTOTYPE
ConfigurableBeanFactory.SCOPE_SINGLETON
@Scope("prototype")
@Scope("singleton")

在WEB开发中，还有请求、会话、应用等等。参考WebApplicationContext类
```

### @Profile机制（了解一下就可以了）
```$xslt
这个针对环境使用的时候的注入，
@Profile("dev")
-Dspring.profile.active=dev
```

### 条件装配Bean
```$xslt
在@Bean
@Conditional(DatabaseConditional.class)
条件需要自己实现。通过实现Condition接口
public class DatabaseConditional implements Condition{
    
    @Override
    public boolean matches(ConditionContext context,AnnotatedTypeMetadata metadata){

    } 
}
```

### XML方式配置Bean
```$xslt
这种情况我个人觉得，一般是那种项目升级时用到的。将古老的spring-bean.xml文件
配置的AppConfig中。
格式：@ImportResource(value="{classpath:spring-bean.xml}")
```


### 使用Spring EL装配Bean
```$xslt
    了解有这个功能即可。
```