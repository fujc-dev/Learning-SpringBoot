# 访问数据库

### Spring Data JPA pom.xml
```xml
        <!-- Spring data jpa依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <!-- mysql依赖 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!-- 数据库连接池依赖 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.4</version>
        </dependency>
        <!-- -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
```

### 创建数据库脚本


```sql
create TABLE t_user(
    id int(12) not null auto_increment,
    user_name varchar(60) not null,
    sex int(3) not null default 1 CHECK (SEX in (1,2)),
    note VARCHAR(256) NULL,
    PRIMARY key (id)
)
```

