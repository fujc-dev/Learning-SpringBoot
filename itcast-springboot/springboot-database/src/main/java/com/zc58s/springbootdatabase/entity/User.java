package com.zc58s.springbootdatabase.entity;

import com.zc58s.springbootdatabase.converter.SexConverter;
import com.zc58s.springbootdatabase.enumeration.SexEnum;

import javax.persistence.*;

/*
 * springboot-database
 * com.zc58s.springbootdatabase.pojo
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/29 12:46
 */
@Entity(name = "user")
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(name = "user_name")
    private String username = null;
    @Convert(converter = SexConverter.class)
    private SexEnum sex = null;

    @Column(name = "note")
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
