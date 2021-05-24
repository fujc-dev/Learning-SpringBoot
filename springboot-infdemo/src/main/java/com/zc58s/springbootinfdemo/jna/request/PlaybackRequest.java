package com.zc58s.springbootinfdemo.jna.request;

/**
 * 回放历史视频参数，需要组装成 Json格式的字符串
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/21 14:52
 */
public class PlaybackRequest {
    private String cameraId;
    private String beginDateTime;
    private String endDateTime;
    private String direction = "forward";
    private String frameStepFlag = "false";
    private String speed = "1000";
    private String dateTimePosition;
    private String archiveServerUrl;


    public PlaybackRequest(String cameraId, String beginDateTime, String endDateTime, String archiveServerUrl) {
        this.cameraId = cameraId;
        this.beginDateTime = beginDateTime;
        this.dateTimePosition = beginDateTime;
        this.endDateTime = endDateTime;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getFrameStepFlag() {
        return frameStepFlag;
    }

    public void setFrameStepFlag(String frameStepFlag) {
        this.frameStepFlag = frameStepFlag;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDateTimePosition() {
        return dateTimePosition;
    }

    public void setDateTimePosition(String dateTimePosition) {
        this.dateTimePosition = dateTimePosition;
    }

    public String getArchiveServerUrl() {
        return archiveServerUrl;
    }

    public void setArchiveServerUrl(String archiveServerUrl) {
        this.archiveServerUrl = archiveServerUrl;
    }

    @Override
    public String toString() {
        return "PlaybackRequest{" +
                "cameraId='" + cameraId + '\'' +
                ", beginDateTime='" + beginDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                ", direction='" + direction + '\'' +
                ", frameStepFlag='" + frameStepFlag + '\'' +
                ", speed='" + speed + '\'' +
                ", dateTimePosition='" + dateTimePosition + '\'' +
                ", archiveServerUrl='" + archiveServerUrl + '\'' +
                '}';
    }
}
