package com.zc58s.springbootdatabase.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 16:33
 * springboot-database
 * com.zc58s.springbootdatabase.entity
 */
@Entity
@Table(name = "tab_user")
public class M2MUser {
    private Integer id;
    private String name;
    private List<M2MRole> roleList;

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
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tab_mapping", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public List<M2MRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<M2MRole> roleList) {
        this.roleList = roleList;
    }
}
