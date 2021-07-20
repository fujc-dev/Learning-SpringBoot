package com.kggs.c9000sdk.rxbus.event;

import com.kggs.c9000sdk.vo.base.VoBase;

/**
 * RxBus事件消息总线传递事件消息对象
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 11:26
 */
public class Event {
    public VoBase event;

    public Event(VoBase event) {
        this.event = event;
    }

    public VoBase getEvent() {
        return event;
    }

    public void setEvent(VoBase event) {
        this.event = event;
    }
}
