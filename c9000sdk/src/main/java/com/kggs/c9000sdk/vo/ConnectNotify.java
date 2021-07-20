package com.kggs.c9000sdk.vo;

import com.kggs.c9000sdk.vo.base.VoBase;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 9:56
 */
public class ConnectNotify extends VoBase {


    /**
     * 状态信息，1 连接 -1 未知
     */
    private int status;
    /**
     * 反馈描述信息
     */
    private String info;



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public String toString() {
        return "ConnectNotify{" +
                "status=" + status +
                ", info='" + info + '\'' +
                '}';
    }
}
