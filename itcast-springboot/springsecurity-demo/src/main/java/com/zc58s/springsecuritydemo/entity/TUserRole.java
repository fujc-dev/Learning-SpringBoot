package com.zc58s.springsecuritydemo.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * (TUserRole)实体类
 *
 * @author makejava
 * @since 2021-01-08 08:49:52
 */
@Alias("t_user_role")
public class TUserRole implements Serializable {
    private static final long serialVersionUID = 941433636272222738L;

    private Integer id;

    private Integer roleId;

    private Integer userId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}