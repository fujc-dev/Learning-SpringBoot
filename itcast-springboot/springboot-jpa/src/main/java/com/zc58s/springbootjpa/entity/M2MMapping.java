package com.zc58s.springbootjpa.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 16:33
 * springboot-database
 * com.zc58s.springbootdatabase.entity
 */
@Entity
@Table(name = "tab_mapping")
public class M2MMapping {
    private Integer id;
    private Integer userId;
    private Integer roleId;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "role_id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
