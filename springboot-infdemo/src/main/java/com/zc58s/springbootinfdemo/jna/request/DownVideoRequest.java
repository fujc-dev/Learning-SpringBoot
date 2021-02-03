package com.zc58s.springbootinfdemo.jna.request;

/**
 * 快捷下载请求参数数据模型
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/28 10:48
 */
public class DownVideoRequest {
    /**
     * 文件名，
     */
    private String szFileName;
    /**
     * 下载历史视频参数 Json格式的字符串，直接调用toString方法即可
     */
    private DownParam szDownParam;

    public DownVideoRequest() {
    }

    public DownVideoRequest(String szFileName, DownParam szDownParam) {
        this.szFileName = szFileName;
        this.szDownParam = szDownParam;
    }

    public String getSzFileName() {
        return szFileName;
    }

    public void setSzFileName(String szFileName) {
        this.szFileName = szFileName;
    }

    public DownParam getSzDownParam() {
        return szDownParam;
    }

    public void setSzDownParam(DownParam szDownParam) {
        this.szDownParam = szDownParam;
    }

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
            this.beginDateTime = beginDateTime;
            this.endDateTime = endDateTime;
            this.speed = speed;
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
}
