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
@Table(name = "tab_role")
public class M2MRole {
    private Integer id;
    private String roleName;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
