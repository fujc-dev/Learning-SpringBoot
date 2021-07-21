package com.kggs.wg5000sdk.req;

/**
 * 构建需要连接到出入口系统的远程地址对象
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/21 9:00
 */
public class RemoteAddress {

    /**
     * ip
     */
    private String ip;
    /**
     * 端口
     */
    private int port;

    public RemoteAddress(String ip, int port) {
        this.ip = ip;
        this.port = port;
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

    @Override
    public String toString() {
        return "RemoteAddress{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
