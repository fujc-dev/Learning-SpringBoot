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