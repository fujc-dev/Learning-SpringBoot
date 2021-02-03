package com.zc58s.springbootinfdemo.jna.sdk.callback;

import com.sun.jna.win32.StdCallLibrary;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/26 14:18
 */
public interface SystemEventCallback extends StdCallLibrary.StdCallCallback {
    public void invoke(int nHandle, String szResult);
}
