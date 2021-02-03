package com.zc58s.springbootinfdemo.jna.request;

/**
 * 快捷拍照请求参数数据模型
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/28 10:48
 */
public class PhotographRequest {

    /**
     * 摄像机唯一编号
     */
    private String szCameraId;
    /**
     * 文件名
     */
    private String szSnapFileName;
    /**
     * 快照类型，0为Bmp，1为Jpeg，2为Png
     */
    private PhotographType iType;

    public enum PhotographType {
        Bmp,
        Jpeg,
        Png
    }
}
