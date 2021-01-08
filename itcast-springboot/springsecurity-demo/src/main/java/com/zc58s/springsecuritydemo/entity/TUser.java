package com.zc58s.springsecuritydemo.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * (TUser)实体类
 *
 * @author makejava
 * @since 2021-01-08 08:49:43
 */
@Alias("t_user")
public class TUser implements Serializable {
    private static final long serialVersionUID = -27869804244519049L;

    private Integer id;

    private String userName;

    private String pwd;

    private Integer available;

    private String note;

    private List<TRole> roles;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<TRole> getRoles() {
        return roles;
    }

    public void setRoles(List<TRole> roles) {
        this.roles = roles;
    }

}