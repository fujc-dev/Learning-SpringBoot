package com.zc58s.springbootinfdemo.jna.service.business.factory;

import com.zc58s.springbootinfdemo.jna.service.IPlatformService;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/26 9:14
 */
public class PlatformFactory {


    /**
     * 获取一个新的平台服务凭据
     *
     * @return
     */
    public static IPlatformService CreateNew() {
        //如果有必须要，每一次请求，都与登录绑定
        return null;
    }
}
