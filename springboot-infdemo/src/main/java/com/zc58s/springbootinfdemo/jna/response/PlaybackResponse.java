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

    /**
     * 回放ID，也就是相机唯一编号
     */
    private String cameraId;


    public PlaybackResponse(String cameraId) {
        this.cameraId = cameraId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }
}
