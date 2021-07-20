package com.kggs.c9000sdk.sdk.callback;

import com.sun.jna.win32.StdCallLibrary;

import java.io.UnsupportedEncodingException;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/15 17:24
 */
public interface SDK9000ClientCallBack extends StdCallLibrary.StdCallCallback {

    /**
     * 回调函数原型csst_lhb9000_callback
     *
     * @param szData      接收数据(详见json数据格式说明.txt)
     * @param nDataLength 接收数据长度
     * @throws UnsupportedEncodingException
     */
    void invoke(String szData, int nDataLength) throws UnsupportedEncodingException;
}
