package com.zc58s.springmvcdemo.entity;



/*
 * springboot-mybatis
 * com.zc58s.springmvcdemo.pojo
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/29 12:46
 */

import com.zc58s.springmvcdemo.enumeration.Sex;
import org.apache.ibatis.type.Alias;


@Alias(value = "t_user")
public class User {

    private Long id = null;

    private String username = null;

    private Sex sex = null;

    private String note = null;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
