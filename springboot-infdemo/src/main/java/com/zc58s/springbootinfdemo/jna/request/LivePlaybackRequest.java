package com.zc58s.springbootinfdemo.jna.request;

import com.zc58s.springbootinfdemo.jna.sdk.UnsignedLong;

import java.security.PublicKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 直播的请求数据模型
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/4 10:03
 */
public class LivePlaybackRequest {
    /*
     * 标志该次的录像搜索ID，格式为uuid
     */
    private String szSearchId;
    /**
     * 摄像头编号
     */
    private String szCameraId;
    /*
     * 查询录像的开始时间(1970年1月1日开始的秒数1483642884)
     */
    private UnsignedLong dwBeginTime;
    /*
     * 查询录像的结束时间(1970年1月1日开始的秒数1483743684)
     */
    private UnsignedLong dwEndTime;
    /*
     * 搜索的录像类型 ("all" 所有 "auto" 自动 "alarm" 报警 一般赋值"all")
     */
    private RecordType szRecordType;

    /*
     * 回放类型（自适应0，内部1，外部2 一般赋值0）
     */
    private BackType iBackType;

    /*
     * 服务器返回结果的最大超时时间。
     */
    private UnsignedLong lTimeout;

    public LivePlaybackRequest() {
        //{92811773-C7CC-41BE-B848-CD90A4EB6A11}
        //{26D0278F-A2E3-4E7D-A612-1FF3793955BA}
        this.szSearchId = "{" + UUID.randomUUID().toString().toUpperCase() + "}";
        this.szRecordType = RecordType.all;
        this.iBackType = BackType.self_adaption;
        this.lTimeout = new UnsignedLong();
        this.lTimeout.setValue(5000);
    }

    /**
     * @param szCameraId
     * @param dwBeginTime
     * @param dwEndTime
     * @throws ParseException
     */
    public LivePlaybackRequest(String szCameraId, String dwBeginTime, String dwEndTime) throws ParseException {
        this();
        this.szCameraId = szCameraId;

        DateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        this.dwBeginTime = java_long_to_cpp_unsigned_long(dwBeginTime);
        this.dwEndTime = java_long_to_cpp_unsigned_long(dwEndTime);
    }

    private UnsignedLong java_long_to_cpp_unsigned_long(String dwTime) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        UnsignedLong unsignedLong = new UnsignedLong();
        long _ = df.parse(dwTime).getTime() / 1000L;
        unsignedLong.setValue(_);
        return unsignedLong;
    }

    public String getSzSearchId() {
        return szSearchId;
    }

    public void setSzSearchId(String szSearchId) {
        this.szSearchId = szSearchId;
    }

    public String getSzCameraId() {
        return szCameraId;
    }

    public void setSzCameraId(String szCameraId) {
        this.szCameraId = szCameraId;
    }

    public UnsignedLong getDwBeginTime() {
        return dwBeginTime;
    }

    public void setDwBeginTime(UnsignedLong dwBeginTime) {
        this.dwBeginTime = dwBeginTime;
    }

    public UnsignedLong getDwEndTime() {
        return dwEndTime;
    }

    public void setDwEndTime(UnsignedLong dwEndTime) {
        this.dwEndTime = dwEndTime;
    }

    public RecordType getSzRecordType() {
        return szRecordType;
    }

    public void setSzRecordType(RecordType szRecordType) {
        this.szRecordType = szRecordType;
    }

    public BackType getiBackType() {
        return iBackType;
    }

    public void setiBackType(BackType iBackType) {
        this.iBackType = iBackType;
    }

    public UnsignedLong getlTimeout() {
        return lTimeout;
    }

    public void setlTimeout(UnsignedLong lTimeout) {
        this.lTimeout = lTimeout;
    }

    /**
     * 搜索的录像类型 ("all" 所有 "auto" 自动 "alarm" 报警 一般赋值"all")
     */
    public enum RecordType {
        /**
         * 所有
         */
        all("all"),
        /**
         * 自动
         */
        auto("auto"),
        /**
         * 报警
         */
        alarm("alarm");
        private String type;

        RecordType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }

    /**
     * 回放类型（自适应0，内部1，外部2 一般赋值0）
     */
    public enum BackType {
        /**
         * 自适应
         */
        self_adaption(0),
        /**
         * 内部
         */
        internal(1),
        /**
         * 外部
         */
        external(2);
        private int type;

        BackType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }
    }

    @Override
    public String toString() {
        return "LivePlaybackRequest{" +
                "szSearchId='" + szSearchId + '\'' +
                ", szCameraId='" + szCameraId + '\'' +
                ", dwBeginTime=" + dwBeginTime.longValue() +
                ", dwEndTime=" + dwEndTime.longValue() +
                ", szRecordType='" + szRecordType.getType() + '\'' +
                ", iBackType=" + iBackType.getType() +
                ", lTimeout=" + lTimeout.longValue() +
                '}';
    }
}
