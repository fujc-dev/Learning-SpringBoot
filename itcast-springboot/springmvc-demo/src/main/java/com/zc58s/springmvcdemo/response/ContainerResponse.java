package com.zc58s.springmvcdemo.response;

import java.util.Date;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/6 15:02
 * springmvc-demo
 * com.zc58s.springmvcdemo.response
 */
public class ContainerResponse {
    private Date date;
    private  Double number;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }
}
