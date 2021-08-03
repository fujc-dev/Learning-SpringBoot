package com.kggs.c9000sdk.rxbus.event;

import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.vo.UnKnownNotify;

/**
 * CID消息事件
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/30 14:35
 */
public class UnKnownNotifyEvent extends Event {
    public UnKnownNotify event;

    public UnKnownNotifyEvent(UnKnownNotify event) {
        super(event);
        this.event = event;
    }

    public UnKnownNotify getEvent() {
        return event;
    }

    public void setEvent(UnKnownNotify event) {
        this.event = event;
    }
}
