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
    private String szFilePath;
    /**
     * 快照类型，0为Bmp，1为Jpeg，2为Png
     */
    private PhotographType iType;

    public enum PhotographType {
        Bmp(1),
        Jpeg(2),
        Png(3);
        private int type;

        PhotographType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }
    }

    /**
     * @param szCameraId
     * @param szFilePath
     * @param iType
     */
    public PhotographRequest(String szCameraId, String szFilePath, PhotographType iType) {
        this.szCameraId = szCameraId;
        this.szFilePath = szFilePath;
        this.iType = iType;
    }

    public String getSzCameraId() {
        return szCameraId;
    }

    public void setSzCameraId(String szCameraId) {
        this.szCameraId = szCameraId;
    }

    public String getSzFilePath() {
        return szFilePath;
    }

    public void setSzFilePath(String szSnapFileName) {
        this.szFilePath = szSnapFileName;
    }

    public PhotographType getiType() {
        return iType;
    }

    public void setiType(PhotographType iType) {
        this.iType = iType;
    }

    @Override
    public String toString() {
        return "PhotographRequest{" +
                "szCameraId='" + szCameraId + '\'' +
                ", szFilePath='" + szFilePath + '\'' +
                ", iType=" + iType +
                '}';
    }
}
