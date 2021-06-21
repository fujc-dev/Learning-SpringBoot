package com.zc58s.springbootinfdemo.jna.response;

import com.zc58s.springbootinfdemo.jna.response.base.ResponseBase;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/26 10:13
 */
public class DownVideoResponse extends ResponseBase {
    private   byte[]  szDownloadTaskId;

    public byte[] getSzDownloadTaskId() {
        return szDownloadTaskId;
    }

    public void setSzDownloadTaskId(byte[] szDownloadTaskId) {
        this.szDownloadTaskId = szDownloadTaskId;
    }
}
