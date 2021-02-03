package com.zc58s.springbootinfdemo.jna.service;

import com.zc58s.springbootinfdemo.jna.request.LoginRequest;
import com.zc58s.springbootinfdemo.jna.response.LoginResponse;

/**
 * 视频服务接口，依赖平台的视频服务，用于登录与注销操作
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/26 17:33
 */
public interface IPlatformService {

    /**
     * 登录到平台，目前针对的是英飞拓
     *
     * @param request 登录请求参数
     * @return 登录结果
     */
    LoginResponse Login(LoginRequest request);


    /**
     * 注销，从服务器注销
     */
    void LoginOut();


}
