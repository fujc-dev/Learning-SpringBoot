package com.zc58s.springbootinfdemo.jna.sdk.callback.impl;

import com.zc58s.springbootinfdemo.jna.sdk.callback.MessageCallback;
import org.springframework.stereotype.Component;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/26 13:51
 */
@Component
public class MessageCallbackImp implements MessageCallback {

    @Override
    public void invoke(String szCmdId, int nHandle, String szAction, String szResult) {
        System.out.println(szCmdId);
        System.out.println(nHandle);
        System.out.println(szAction);
        System.out.println(szResult);
    }
}
