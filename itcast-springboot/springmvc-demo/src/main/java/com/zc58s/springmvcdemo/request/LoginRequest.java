package com.zc58s.springmvcdemo.request;

import java.io.Serializable;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/6 9:34
 * springmvc-demo
 * com.zc58s.springmvcdemo.request
 */
public class LoginRequest implements Serializable {
    private String username;
    private String note;

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
