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
```