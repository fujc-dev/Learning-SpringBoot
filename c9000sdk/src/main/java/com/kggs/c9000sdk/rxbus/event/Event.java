package com.kggs.c9000sdk.rxbus.event;

import com.kggs.c9000sdk.vo.base.NotifyBase;

/**
 * RxBus事件消息总线传递事件消息对象
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 11:26
 */
public class Event {
    public NotifyBase event;

    public Event(NotifyBase event) {
        this.event = event;
    }

    public NotifyBase getEvent() {
        return event;
    }

    public void setEvent(NotifyBase event) {
        this.event = event;
    }
}
