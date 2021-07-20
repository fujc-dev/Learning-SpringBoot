package com.kggs.c9000sdk.vo;

import com.kggs.c9000sdk.vo.base.NotifyBase;

/**
 * 平台所管辖的报警主机状态对象模型
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 10:03
 */
public class MachineNotify extends NotifyBase {
    /**
     * 连接类型 1 为网络，2 为串口
     */
    private int commtype;
    /**
     * 报警主机连接号
     */
    private int connect;
    /**
     * 可能是IP地址，也可能是com ，比如如果commtype = 1 那么 ip就是类似"192.168.1.222",如果commtype = 2 那么 ip 就是 "com"
     */
    private String ip;
    /**
     * 如果commtype = 1 那么 port 类似 6000、6001 ,如果commtype = 2 那么 port 就是 1、2、3、4
     */
    private int port;
    /**
     * 报警主机状态 1 在线、0 下线
     */
    private int status;


    public int getCommtype() {
        return commtype;
    }

    public void setCommtype(int commtype) {
        this.commtype = commtype;
    }

    public int getConnect() {
        return connect;
    }

    public void setConnect(int connect) {
        this.connect = connect;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MachineNotify{" +
                "commtype=" + commtype +
                ", connect=" + connect +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", status=" + status +
                '}';
    }
}
