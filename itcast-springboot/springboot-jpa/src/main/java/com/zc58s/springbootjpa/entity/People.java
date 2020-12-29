package com.zc58s.springbootjpa.entity;

import javax.persistence.*;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 15:53
 * springboot-database
 * com.zc58s.springbootdatabase.entity
 */
@Entity
@Table(name = "tab_people")
public class People {
    private int id;
    private String name;
    private String sex;
    private Integer age;
    private Idcard idcard;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    public Idcard getIdcard() {
        return idcard;
    }

    public void setIdcard(Idcard idcard) {
        this.idcard = idcard;
    }

}
