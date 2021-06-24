package com.kggs.modbus4jgateway.bean;

/**
 * Master主机对象
 * <p>
 * Modbus4j的ModbusMaster接收一个Ip地址
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/6/24 10:24
 */
public class Master {
    private String ip;

    public String getIp() {
        return ip;
    }


    public Master(String ip) {
        this.ip = ip;
    }
}
