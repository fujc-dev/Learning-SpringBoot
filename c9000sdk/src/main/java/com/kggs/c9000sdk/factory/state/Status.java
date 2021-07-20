package com.kggs.c9000sdk.factory.state;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:25
 */
public enum Status {
    /**
     * SDK和平台连接状态通知
     */
    system,
    /**
     * 平台所管辖的报警主机状态
     */
    machine,
    /**
     * 报警主机事件通知
     */
    alarm,
    /**
     * CID码详细信息
     */
    cidinfo,
    /**
     * 未知的消息类型
     */
    nothing
}
