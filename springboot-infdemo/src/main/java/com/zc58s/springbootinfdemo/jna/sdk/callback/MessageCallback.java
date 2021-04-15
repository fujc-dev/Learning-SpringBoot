package com.zc58s.springbootinfdemo.jna.sdk.callback;

import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

import java.io.UnsupportedEncodingException;

/**
 * 登录服务器的回调函数 通过szAction的地址判断做什么动作
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/26 13:20
 */
public interface MessageCallback extends StdCallLibrary.StdCallCallback {

    /**
     * 回调函数
     *
     * @param szCmdId
     * @param nHandle  句柄，当前句柄，后续很多请求都需要传递这个句柄，来完成SDK的调用
     * @param szAction 通过szAction的地址判断做什么动作
     * @param szResult
     * @throws UnsupportedEncodingException
     */
    void invoke(String szCmdId, int nHandle, String szAction, String szResult, Pointer pUser) throws UnsupportedEncodingException;
}
