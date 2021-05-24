package com.zc58s.springbootinfdemo.jna.sdk.callback;

import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

/**
 * 流数据回调：媒体流格式见 LPMEDIAFRAME_INFO（未找到）
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/28 9:42
 */
public interface streamCallBack extends StdCallLibrary.StdCallCallback {

    /**
     *
     * @param pUser
     * @param nHandle
     * @param szType
     * @param szError
     * @param pBuf
     * @param nSize
     * @return
     */
    int invoke(Pointer pUser, int nHandle, String szType, String szError, Pointer pBuf, int nSize);
}
