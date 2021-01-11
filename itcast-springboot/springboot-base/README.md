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