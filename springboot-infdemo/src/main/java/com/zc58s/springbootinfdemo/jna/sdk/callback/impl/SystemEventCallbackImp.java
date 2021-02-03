package com.zc58s.springbootinfdemo.jna.sdk.callback.impl;

import com.zc58s.springbootinfdemo.jna.sdk.callback.SystemEventCallback;
import org.springframework.stereotype.Component;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/26 14:47
 */

@Component
public class SystemEventCallbackImp implements SystemEventCallback {
    @Override
    public void invoke(int nHandle, String szResult) {

    }
}
