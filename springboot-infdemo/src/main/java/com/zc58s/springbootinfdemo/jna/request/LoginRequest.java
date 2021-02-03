package com.zc58s.springbootinfdemo.jna.request;

import com.zc58s.springbootinfdemo.jna.exception.LoginException;

/**
 * 登录英飞拓平台的登录请求数据模型
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/26 17:35
 */
public class LoginRequest {
    /**
     * 服务器地址
     */
    private String szUrl;
    /**
     * 登录服务器用户名
     */
    private String szUser;
    /**
     * 登录服务器密码
     */
    private String szPassword;
    /**
     * 登录服务器参数，一般为""
     */
    private String szParam = "";
    /**
     * 登录服务器超时时间
     */
    private Long szTimeout = 10 * 1000L; //默认登录服务器的超时时间为10s


    public LoginRequest(String szUrl, String szUser, String szPassword) {
        this.szUrl = szUrl;
        this.szUser = szUser;
        this.szPassword = szPassword;
        if (this.szTimeout < 2000L) throw new LoginException("超时时间不能小于2000毫秒");  //为了避免线程阻塞死锁，对超时限制为2s
    }

    public LoginRequest(String szUrl, String szUser, String szPassword, String szParam) {
        this(szUrl, szUser, szPassword);
        this.szParam = szParam;
    }

    public LoginRequest(String szUrl, String szUser, String szPassword, String szParam, Long szTimeout) {
        this(szUrl, szUser, szPassword, szParam);
        this.szTimeout = szTimeout;
    }

    public String getSzUrl() {
        return szUrl;
    }

    public void setSzUrl(String szUrl) {
        this.szUrl = szUrl;
    }

    public String getSzUser() {
        return szUser;
    }

    public void setSzUser(String szUser) {
        this.szUser = szUser;
    }

    public String getSzPassword() {
        return szPassword;
    }

    public void setSzPassword(String szPassword) {
        this.szPassword = szPassword;
    }

    public String getSzParam() {
        return szParam;
    }

    public void setSzParam(String szParam) {
        this.szParam = szParam;
    }

    public Long getSzTimeout() {
        return szTimeout;
    }

    public void setSzTimeout(Long szTimeout) {
        this.szTimeout = szTimeout;
    }
}
