package com.kggs.c9000sdk.rxbus.event;

import com.kggs.c9000sdk.rxbus.event.base.AlarmNotifyEvent;
import com.kggs.c9000sdk.vo.AlarmNotify;

/**
 * 增加撤布防的事件，用于接收类型编码为40D 40E 456 462 463 464 465 466 467 的事件消息
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/3 11:49
 */
public class ProtectionRemovalNotifyEvent extends AlarmNotifyEvent {

    /**
     * 报警消息事件扩展，撤布防的事件
     * @param event
     */
    public ProtectionRemovalNotifyEvent(AlarmNotify event) {
        super(event);
    }
}
