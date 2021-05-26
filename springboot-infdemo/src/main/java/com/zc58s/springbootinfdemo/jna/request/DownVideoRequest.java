package com.zc58s.springbootinfdemo.jna.request;

import com.zc58s.springbootinfdemo.jna.request.params.DownParam;

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


    @Override
    public String toString() {
        return "DownVideoRequest{" +
                "szFileName='" + szFileName + '\'' +
                ", szDownParam" + szDownParam.toString() +
                '}';
    }
}
