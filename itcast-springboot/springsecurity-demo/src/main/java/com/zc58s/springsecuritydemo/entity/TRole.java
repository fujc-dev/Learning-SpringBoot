package com.zc58s.springsecuritydemo.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * (TRole)实体类
 *
 * @author makejava
 * @since 2021-01-08 08:49:31
 */
//@Alias("t_role")
public class TRole implements Serializable {
    private static final long serialVersionUID = -54675080389472285L;

    private Integer id;

    private String roleName;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}