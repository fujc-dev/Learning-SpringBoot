package com.kggs.electronicpatrol.pojo;

import com.kggs.electronicpatrol.annotation.DefinitionName;

import java.util.Date;

/**
 * 平潭现场流水数据简单的数据模型
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/17 16:33
 */
public class Flow {
    /**
     * 人员卡号（当为空时，系统就自动掘弃该行数据，不做处理）
     */
    @DefinitionName(cellIndex = "A", cellName = "人员卡号")
    private String code;
    /**
     * 人员卡名
     */
    @DefinitionName(cellIndex = "B", cellName = "人员卡名")
    private String name;
    /**
     * 巡检时间
     */
    @DefinitionName(cellIndex = "D", cellName = "巡检时间", dataType = Date.class)
    private Date date;
    /**
     * 地址卡号
     */
    @DefinitionName(cellIndex = "E", cellName = "地址卡号")
    private String targetCode;
    /**
     * 地址卡名
     */
    @DefinitionName(cellIndex = "H", cellName = "地址卡名")
    private String targetName;
    /**
     * 事件号
     */
    @DefinitionName(cellIndex = "I", cellName = "事件号")
    private String eventCode;
    /**
     * 事件名
     */
    @DefinitionName(cellIndex = "J", cellName = "事件名")
    private String eventName;
    /**
     * 巡检器号
     */
    @DefinitionName(cellIndex = "K", cellName = "巡检器号")
    private String deviceCode;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", targetCode='" + targetCode + '\'' +
                ", targetName='" + targetName + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", eventName='" + eventName + '\'' +
                ", deviceCode='" + deviceCode + '\'' +
                '}';
    }
}
