package com.zc58s.springbootinfdemo.jna.request.params;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/26 11:21
 */
public class DownParam {

    private String cameraId;
    private String beginDateTime;
    private String endDateTime;
    private String speed;
    private String archiveServerUrl;

    public DownParam() {

    }

    /**
     * 构建下载视频参数
     *
     * @param cameraId         视频唯一编号
     * @param beginDateTime    开始时间
     * @param endDateTime      结束时间
     * @param speed            下载速度  最高16x
     * @param archiveServerUrl 录像存储的服务器的地址
     */
    public DownParam(String cameraId, String beginDateTime, String endDateTime, String speed, String archiveServerUrl) {
        this.cameraId = cameraId;
        this.beginDateTime = beginDateTime.replace(" ", "T");
        this.endDateTime = endDateTime.replace(" ", "T");
        this.speed = speed;
        this.archiveServerUrl = archiveServerUrl;
    }


    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public String getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(String beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getArchiveServerUrl() {
        return archiveServerUrl;
    }

    public void setArchiveServerUrl(String archiveServerUrl) {
        this.archiveServerUrl = archiveServerUrl;
    }

    @Override
    public String toString() {
        return "{" +
                "cameraId='" + cameraId + '\'' +
                ", beginDateTime='" + beginDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                ", speed='" + speed + '\'' +
                ", archiveServerUrl='" + archiveServerUrl + '\'' +
                '}';
    }
}
