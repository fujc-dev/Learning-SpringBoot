package com.kggs.c9000sdk.rxbus.event;

import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.vo.MachineNotify;

/**
 * 平台所管辖的报警主机状态消息事件
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/30 14:27
 */
public class MachineNotifyEvent extends Event {

    public MachineNotify event;

    public MachineNotifyEvent(MachineNotify event) {
        super(event);
        this.event = event;
    }

    public MachineNotify getEvent() {
        return event;
    }

    public void setEvent(MachineNotify event) {
        this.event = event;
    }
}
