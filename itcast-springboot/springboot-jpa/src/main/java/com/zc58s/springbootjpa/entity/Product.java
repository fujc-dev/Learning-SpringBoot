package com.zc58s.springbootjpa.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 16:07
 * springboot-database
 * com.zc58s.springbootdatabase.entity
 */
@Entity
@Table(name = "tab_product")
public class Product {
    private int id;
    private String name;
    private BigDecimal price;


    private Factory factory;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @ManyToOne
    @JoinColumn(name = "factory_id")
    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
