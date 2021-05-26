package com.zc58s.springbootinfdemo.jna.service.impl.response;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/26 10:31
 */
public class Msg {
    @JSONField(name = "searchId")
    private String searchId;
    @JSONField(name = "cameraId")
    private String cameraId;
    @JSONField(name = "recordFile")
    private List<RecordFile> recordFile;

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public List<RecordFile> getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(List<RecordFile> recordFile) {
        this.recordFile = recordFile;
    }
}
