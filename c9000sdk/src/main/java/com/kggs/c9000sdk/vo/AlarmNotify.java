package com.kggs.c9000sdk.vo;

import com.kggs.c9000sdk.vo.base.NotifyBase;

/**
 * 报警主机事件通知对象模型
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 10:05
 */
public class AlarmNotify extends NotifyBase {
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
     * 事件报警类型返回值可能有两个 1,0 【 1代表是 dk903键盘事件】，【0 代表是 主机事件】
     */
    private int eventflag;
    /**
     * 事件触发返回值可能有两个     1,0 【 1代表是 触发事件】，     【0 代表是 恢复事件】
     */
    private int eventtriger;
    /**
     * CID码
     */
    private String cidcode;
    /**
     * CID级别
     */
    private int cidlevel;
    /**
     * CID类型
     */
    private String cidtype;
    /**
     * CID内容
     */
    private String cidmemo;
    /**
     * 分区号
     */
    private int partcode;
    /**
     * 防区号
     */
    private int guardcode;


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

    public int getEventflag() {
        return eventflag;
    }

    public void setEventflag(int eventflag) {
        this.eventflag = eventflag;
    }

    public int getEventtriger() {
        return eventtriger;
    }

    public void setEventtriger(int eventtriger) {
        this.eventtriger = eventtriger;
    }

    public String getCidcode() {
        return cidcode;
    }

    public void setCidcode(String cidcode) {
        this.cidcode = cidcode;
    }

    public int getCidlevel() {
        return cidlevel;
    }

    public void setCidlevel(int cidlevel) {
        this.cidlevel = cidlevel;
    }

    public String getCidtype() {
        return cidtype;
    }

    public void setCidtype(String cidtype) {
        this.cidtype = cidtype;
    }

    public String getCidmemo() {
        return cidmemo;
    }

    public void setCidmemo(String cidmemo) {
        this.cidmemo = cidmemo;
    }

    public int getPartcode() {
        return partcode;
    }

    public void setPartcode(int partcode) {
        this.partcode = partcode;
    }

    public int getGuardcode() {
        return guardcode;
    }

    public void setGuardcode(int guardcode) {
        this.guardcode = guardcode;
    }

    @Override
    public String toString() {
        return "AlarmNotify{" +
                "commtype=" + commtype +
                ", connect=" + connect +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", eventflag=" + eventflag +
                ", eventtriger=" + eventtriger +
                ", cidcode='" + cidcode + '\'' +
                ", cidlevel=" + cidlevel +
                ", cidtype='" + cidtype + '\'' +
                ", cidmemo='" + cidmemo + '\'' +
                ", partcode=" + partcode +
                ", guardcode=" + guardcode +
                '}';
    }
}
