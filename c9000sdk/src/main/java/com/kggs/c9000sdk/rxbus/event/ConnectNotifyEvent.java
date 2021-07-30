package com.kggs.c9000sdk.rxbus.event;

import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.vo.ConnectNotify;

/**
 * SDK和平台连接状态通知平台连接消息事件
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/30 14:26
 */
public class ConnectNotifyEvent extends Event {

    public ConnectNotify event;

    public ConnectNotifyEvent(ConnectNotify event) {
        super(event);
        this.event = event;
    }

    public ConnectNotify getEvent() {
        return event;
    }

    public void setEvent(ConnectNotify event) {
        this.event = event;
    }
}
