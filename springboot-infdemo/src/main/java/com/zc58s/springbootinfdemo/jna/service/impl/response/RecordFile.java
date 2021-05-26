package com.zc58s.springbootinfdemo.jna.service.impl.response;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/26 10:32
 */
public class RecordFile {

    /**
     * 服务器URL
     */
    @JSONField(name = "serverUrl")
    private String serverUrl;
    /**
     * 录像文件名
     */
    @JSONField(name = "fileName")
    private String fileName;
    /**
     * 录像起始时间(UTC时间)
     */
    @JSONField(name = "beginDateTime")
    private String beginDateTime;
    /**
     * 录像结束时间(UTC时间)
     */
    @JSONField(name = "endDateTime")
    private String endDateTime;
    /**
     * 录像类型
     */
    @JSONField(name = "recordType")
    private String recordType;
    /**
     * 文件大小
     */
    @JSONField(name = "fileSize")
    private String fileSize;
    /**
     * 摄像机ID
     */
    @JSONField(name = "cameraId")
    private String cameraId;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }
}
