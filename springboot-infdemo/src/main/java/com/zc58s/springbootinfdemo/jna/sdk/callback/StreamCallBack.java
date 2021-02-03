package com.zc58s.springbootinfdemo.jna.sdk.callback;

import com.sun.jna.Pointer;

/**
 * 流数据回调：媒体流格式见LPMEDIAFRAME_INFO
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/28 9:42
 */
public interface StreamCallBack {

    public void invoke(Pointer pUser, int nHandle, String szType, String szError, String pBuf, int nSize);
}
