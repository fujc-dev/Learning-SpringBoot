package com.zc58s.springbootinfdemo.jna.response;

import com.zc58s.springbootinfdemo.jna.response.base.ResponseBase;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/27 14:59
 */
public class PtzResponse extends ResponseBase {

    @Override
    public String toString() {
        return "PtzResponse{" +
                "status=" + super.getStatus() +
                ", mgs='" + super.getCode() + '\'' +
                '}';
    }
}
