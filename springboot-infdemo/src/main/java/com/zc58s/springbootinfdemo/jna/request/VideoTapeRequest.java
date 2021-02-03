package com.zc58s.springbootinfdemo.jna.request;

/**
 * 快捷录像请求参数数据模型
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/28 10:48
 */
public class VideoTapeRequest {
    /**
     * 视像头唯一编号
     */
    private String szCameraId;
    /**
     * 录像时长，一般是当前时间加上该时长为结束录像时间
     */
    private long szTime;

    public VideoTapeRequest() {
    }

    public VideoTapeRequest(String szCameraId, long szTime) {
        this.szCameraId = szCameraId;
        this.szTime = szTime;
    }

    public String getSzCameraId() {
        return szCameraId;
    }

    public void setSzCameraId(String szCameraId) {
        this.szCameraId = szCameraId;
    }

    public long getSzTime() {
        return szTime;
    }

    public void setSzTime(long szTime) {
        this.szTime = szTime;
    }
}
