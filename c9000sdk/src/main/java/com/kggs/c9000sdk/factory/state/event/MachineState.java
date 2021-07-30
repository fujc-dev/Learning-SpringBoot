package com.kggs.c9000sdk.factory.state.event;

import com.alibaba.fastjson.JSONObject;
import com.kggs.c9000sdk.factory.StateFactory;
import com.kggs.c9000sdk.factory.state.NotifyState;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.rxbus.event.MachineNotifyEvent;
import com.kggs.c9000sdk.vo.MachineNotify;

/**
 * 平台所管辖的报警主机状态
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:26
 */
public class MachineState implements NotifyState {
    @Override
    public Event Serialize(Enum<Status> currentStatus, String szData) {
        if (currentStatus == Status.machine) {
            MachineNotify notify = JSONObject.parseObject(szData, MachineNotify.class);
            MachineNotifyEvent event = new MachineNotifyEvent(notify);
            return event;
        } else {
            return StateFactory.Serialize(Status.alarm, szData);
        }
    }
}
