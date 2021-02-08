package com.zc58s.springbootinfdemo.jna.sdk.callback;

import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

import java.io.UnsupportedEncodingException;

/**
 * 系统事件回调接口，就目前测试来看，当我们修改了平台相关的参数时，会将信息回调到该接口
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/26 14:18
 */
public interface SystemEventCallback extends StdCallLibrary.StdCallCallback {
    public void invoke(int nHandle, String szResult, Pointer pUser) throws UnsupportedEncodingException;
}
