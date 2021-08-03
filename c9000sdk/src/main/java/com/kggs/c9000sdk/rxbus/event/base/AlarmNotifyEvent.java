package com.kggs.c9000sdk.rxbus.event.base;

import com.kggs.c9000sdk.vo.AlarmNotify;

/**
 * 报警消息事件
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/30 14:27
 */
public class AlarmNotifyEvent extends Event {

    public AlarmNotify event;

    /**
     * 默认的报警事件消息构造函数
     *
     * @param event
     */
    public AlarmNotifyEvent(AlarmNotify event) {
        super(event);
        this.event = event;
    }

    public AlarmNotify getEvent() {
        return event;
    }

    public void setEvent(AlarmNotify event) {
        this.event = event;
    }
}
