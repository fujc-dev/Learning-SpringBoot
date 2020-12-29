package com.zc58s.springbootmybatis.entity;



/*
 * springboot-database
 * com.zc58s.springbootdatabase.pojo
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/29 12:46
 */

import com.zc58s.springbootmybatis.enumeration.SexEnum;

public class User {

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
