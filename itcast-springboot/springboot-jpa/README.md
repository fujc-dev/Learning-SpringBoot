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

### JPA单表查询语法
```java
/**
 * 使用方法命名的方式实现对数据库的查询
 * <p>以动词find/get开始，而以by代表按照上面内容进行查询；</p>
 * <p>
 * 例如：</p>
 * <p>getUserById：方法就是通过主键(id)对用户进行查询的，这样JPA就会根据方法命名生成SQL来查询数据库；</p>
 * <p>findByUsernameLike：方法是命名规则则多了一个like，它代表着采用模糊查询，也就是使用like关键字进行查询；</p>
 * <p>findByUsernameLikeOrNoteLike：这样的命名，则涉及到两个条件，一个是用户名username，另一个则是备注note，
 * 他们都采用了like，因此会执行模糊查询，而他们之间采用连接词or，所以SQL的生成也会添加Or。</p>
 *
 * <p>
 * findByUsernameLike 与 findUserByUsernameLike 等价
 * </p>
 * <p>
 * findByUsernameLikeOrNoteLike 与 findUserByUsernameLikeOrNoteLike等价
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @create time : 2020/12/29 13:14
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 不需要写脚本，按用户名模糊查询
     *
     * @param username
     * @return
     */
    List<User> findByUsernameLike(String username);

    List<User> findUserByUsernameLike(String username);

    /**
     * 根据用户名或者备注进行模糊查询
     *
     * @param username
     * @param note
     * @return
     */
    List<User> findUserByUsernameLikeOrNoteLike(String username, String note);

    List<User> findByUsernameLikeOrNoteLike(String username, String note);

    /**
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * @param id
     * @return
     */
    User getUserById(Long id);
    
}
```


### JPA多表查询语法
```sql
DROP TABLE IF EXISTS `bz_package_index`;
CREATE TABLE `bz_package_index` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `total_ity` int(11) DEFAULT NULL,
  `payer_account` varchar(30) DEFAULT NULL,
  `money_sum` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


```

```sql
DROP TABLE IF EXISTS `bz_payeelist_bankexecut`;
CREATE TABLE `bz_payeelist_bankexecut` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `package_id` varchar(64) DEFAULT NULL,
  `money` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```


### 另一种 Spring Data JPA实现多表的关联查询

#### Spring Data JPA 一对一 多表关联查询，先建表
```sql
-- 判断数据表是否存在，存在则删除
DROP TABLE IF EXISTS tab_people;
DROP TABLE IF EXISTS tab_idcard;
 
-- 创建“身份证信息”数据表
CREATE TABLE IF NOT EXISTS tab_idcard
( 
	id INT AUTO_INCREMENT PRIMARY KEY COMMENT '身份证ID',
	idCard_code VARCHAR(45) COMMENT '身份证号码'
) COMMENT = '身份证信息表';
 
-- 创建“公民信息”数据表
CREATE TABLE IF NOT EXISTS tab_people
( 
	id INT AUTO_INCREMENT PRIMARY KEY COMMENT '公民ID',
	NAME VARCHAR(45) NOT NULL COMMENT '公民名称',
	sex VARCHAR(2) COMMENT '公民性别',
	age INT COMMENT '公民年龄',
	card_id INT UNIQUE COMMENT '身份证ID',
	-- 创建外键约束
	FOREIGN KEY fk_card_id (card_id)
	REFERENCES tab_idcard(id)
) COMMENT = '公民信息表';
 
-- 添加数据
INSERT INTO tab_idcard(idCard_code) VALUE('123456789');
INSERT INTO tab_people(NAME,sex,age,card_id) VALUES('pan_junbiao的博客','男',32,1); 
```
#### Spring Data JPA 一对多 多表关联查询，先建表
```sql
-- 判断数据表是否存在，存在则删除
DROP TABLE IF EXISTS tab_factory;
DROP TABLE IF EXISTS tab_product;
 
-- 创建“生产商信息”数据表
CREATE TABLE IF NOT EXISTS tab_factory
( 
	factory_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '生产商ID',
	NAME VARCHAR(20) NOT NULL COMMENT '生产商名称'
) COMMENT = '生产商信息表';
 
-- 创建“产品信息”数据表
CREATE TABLE IF NOT EXISTS tab_product
( 
	id INT AUTO_INCREMENT PRIMARY KEY COMMENT '产品ID',
	NAME VARCHAR(20) NOT NULL COMMENT '产品名称',
	price DECIMAL(9,2) NOT NULL COMMENT '产品价格',
	factory_id INT COMMENT '生产商ID'
) COMMENT = '产品信息表';
 
-- 添加数据
INSERT INTO tab_factory(NAME) VALUES('华为公司');
INSERT INTO tab_product(NAME,price,factory_id) VALUES('华为手机',1299,1);
INSERT INTO tab_product(NAME,price,factory_id) VALUES('华为路由器',699,1);
```
#### Spring Data JPA 多对多 多表关联查询，先建表
```sql
-- 判断数据表是否存在，存在则删除
DROP TABLE IF EXISTS tab_user;
DROP TABLE IF EXISTS tab_role;
DROP TABLE IF EXISTS tab_mapping;
 
-- 创建“用户信息”数据表
CREATE TABLE IF NOT EXISTS tab_user
( 
	id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
	NAME VARCHAR(45) NOT NULL COMMENT '用户名称'
) COMMENT = '用户信息表';
 
-- 创建“权限信息”数据表
CREATE TABLE IF NOT EXISTS tab_role
( 
	id INT AUTO_INCREMENT PRIMARY KEY COMMENT '权限ID',
	role_name VARCHAR(45) NOT NULL COMMENT '权限名称'
) COMMENT = '权限信息表';
 
-- 创建“映射信息”数据表
CREATE TABLE IF NOT EXISTS tab_mapping
( 
	id INT AUTO_INCREMENT PRIMARY KEY COMMENT '映射ID',
	user_id INT COMMENT '用户Id',
	role_id INT COMMENT '权限Id'
) COMMENT = '映射信息表';
 
-- 添加数据
INSERT INTO tab_user(NAME) VALUES('admin'),('pan_junbiao的博客');
INSERT INTO tab_role(role_name) VALUES('系统管理员'),('新闻管理员'),('广告管理员');
INSERT INTO tab_mapping(user_id,role_id) VALUES(1,1),(1,2),(1,3),(2,2),(2,3);
```