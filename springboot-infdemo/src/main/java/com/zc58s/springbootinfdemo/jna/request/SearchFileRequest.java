package com.zc58s.springbootinfdemo.jna.request;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/24 16:25
 */
public class SearchFileRequest {
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
    private int dwBeginTime;
    /*
     * 查询录像的结束时间(1970年1月1日开始的秒数1483743684)
     */
    private int dwEndTime;
    /*
     * 搜索的录像类型 ("all" 所有 "auto" 自动 "alarm" 报警 一般赋值"all")
     */
    private SearchFileRequest.RecordType szRecordType;

    /*
     * 回放类型（自适应0，内部1，外部2 一般赋值0）
     */
    private SearchFileRequest.BackType iBackType;

    /*
     * 服务器返回结果的最大超时时间。
     */
    private int lTimeout;

    public SearchFileRequest() {
        this.szSearchId = UUID.randomUUID().toString();
        this.szRecordType = SearchFileRequest.RecordType.all;
        this.iBackType = SearchFileRequest.BackType.self_adaption;
        this.lTimeout = 5000;
    }

    /**
     * @param szCameraId
     * @param dwBeginTime
     * @param dwEndTime
     * @throws ParseException
     */
    public SearchFileRequest(String szCameraId, String dwBeginTime, String dwEndTime) throws ParseException {
        this();
        System.out.println("szCameraId：" + szCameraId);
        System.out.println("dwBeginTime：" + dwBeginTime);
        System.out.println("dwEndTime：" + dwEndTime);

        this.szCameraId = szCameraId;
        this.dwBeginTime = java_long_to_cpp_unsigned_long(dwBeginTime);
        this.dwEndTime = java_long_to_cpp_unsigned_long(dwEndTime);
    }

    private int java_long_to_cpp_unsigned_long(String dwTime) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return (int) (df.parse(dwTime).getTime() / 1000L);

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

    public int getDwBeginTime() {
        return dwBeginTime;
    }

    public void setDwBeginTime(int dwBeginTime) {
        this.dwBeginTime = dwBeginTime;
    }

    public int getDwEndTime() {
        return dwEndTime;
    }

    public void setDwEndTime(int dwEndTime) {
        this.dwEndTime = dwEndTime;
    }

    public SearchFileRequest.RecordType getSzRecordType() {
        return szRecordType;
    }

    public void setSzRecordType(SearchFileRequest.RecordType szRecordType) {
        this.szRecordType = szRecordType;
    }

    public SearchFileRequest.BackType getiBackType() {
        return iBackType;
    }

    public void setiBackType(SearchFileRequest.BackType iBackType) {
        this.iBackType = iBackType;
    }

    public int getlTimeout() {
        return lTimeout;
    }

    public void setlTimeout(int lTimeout) {
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
        return "SearchFileRequest{" +
                "szSearchId='" + szSearchId + '\'' +
                ", szCameraId='" + szCameraId + '\'' +
                ", dwBeginTime=" + dwBeginTime +
                ", dwEndTime=" + dwEndTime +
                ", szRecordType=" + szRecordType +
                ", iBackType=" + iBackType +
                ", lTimeout=" + lTimeout +
                '}';
    }
}
