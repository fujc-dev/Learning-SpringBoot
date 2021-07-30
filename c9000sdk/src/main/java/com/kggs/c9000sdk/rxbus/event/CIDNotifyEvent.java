package com.kggs.c9000sdk.rxbus.event;

import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.vo.CIDNotify;

/**
 * CID消息事件
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/30 14:35
 */
public class CIDNotifyEvent extends Event {
    public CIDNotify event;

    public CIDNotifyEvent(CIDNotify event) {
        super(event);
        this.event = event;
    }

    public CIDNotify getEvent() {
        return event;
    }

    public void setEvent(CIDNotify event) {
        this.event = event;
    }
}
