package com.zc58s.springbootinfdemo.jna.response;

import com.zc58s.springbootinfdemo.jna.base.Sdk;
import com.zc58s.springbootinfdemo.jna.response.base.ResponseBase;


/**
 * 登录英飞拓平台的登录响应数据模型
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/26 17:40
 */
public class LoginResponse extends ResponseBase {

    public LoginResponse() {
        this.setCode(Sdk.CODE_PLATFORM_LOGIN_TIMEOUT); //默认登录超时
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status=" + super.getStatus() +
                ", code='" + super.getCode() + '\'' +
                '}';
    }
}
