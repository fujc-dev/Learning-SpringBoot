package com.zc58s.springmvcdemo.request;

import java.io.Serializable;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/6 16:48
 * springmvc-demo
 * com.zc58s.springmvcdemo.request
 */
public class UserRequest implements Serializable {
    private int id;
    private String username;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
