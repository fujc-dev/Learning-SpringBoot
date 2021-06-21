package com.zc58s.springbootinfdemo.jna.response;

import com.zc58s.springbootinfdemo.jna.response.base.ResponseBase;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/21 11:34
 */
public class PlaybackResponse extends ResponseBase {

    /**
     * 用户收到的推流地址
     */
    private String url;
    private  String szPlayParam;
    private  int loginHandle;


    public PlaybackResponse(String szPlayParam, int loginHandle) {
        this.szPlayParam = szPlayParam;
        this.loginHandle = loginHandle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSzPlayParam() {
        return szPlayParam;
    }

    public void setSzPlayParam(String szPlayParam) {
        this.szPlayParam = szPlayParam;
    }

    public int getLoginHandle() {
        return loginHandle;
    }

    public void setLoginHandle(int loginHandle) {
        this.loginHandle = loginHandle;
    }
}
