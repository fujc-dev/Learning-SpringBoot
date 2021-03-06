package com.zc58s.springbootredis.pojo;



/*
 * springboot-mybatis
 * com.zc58s.springbootredis.pojo
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/29 12:46
 */

import com.zc58s.springbootredis.enumeration.SexEnum;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;


@Alias(value = "t_user")
public class User implements Serializable {

    private Long id = null;

    private String username = null;

    private SexEnum sex = null;

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

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
