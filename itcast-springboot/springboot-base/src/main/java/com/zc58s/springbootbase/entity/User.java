package com.zc58s.springbootbase.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/11 14:58
 * springboot-base
 * com.zc58s.springbootbase.entity
 */
@Entity //每个持久化的POJO类，都是一个实体的Bean，通过类的定义中的@Entity注解来进行声明
@Table(name = "t_user")  //声明此对象映射到数据的表名称。该注释不是必须的，如果没有，框架会使用默认值，实体的短类名。
public class User {
    private String id;
    private String username;
    private String password;

    //@id：指定表的主键
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
