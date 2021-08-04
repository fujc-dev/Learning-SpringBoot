package com.kggs.c9000sdk.rxbus.event;

import com.kggs.c9000sdk.rxbus.event.base.AlarmNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.vo.AlarmNotify;
import com.kggs.c9000sdk.vo.ProtectionRemovalNotify;

/**
 * 增加撤布防的事件，用于接收类型编码为40D 40E 456 462 463 464 465 466 467 的事件消息
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/3 11:49
 */
public class ProtectionRemovalNotifyEvent extends Event {

    public ProtectionRemovalNotify event;

    /**
     * 默认的报警事件消息构造函数
     *
     * @param event
     */
    public ProtectionRemovalNotifyEvent(ProtectionRemovalNotify event) {
        super(event);
        this.event = event;
    }

    public ProtectionRemovalNotify getEvent() {
        return event;
    }

    public void setEvent(ProtectionRemovalNotify event) {
        this.event = event;
    }

}
