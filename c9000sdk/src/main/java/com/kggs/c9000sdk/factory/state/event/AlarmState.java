package com.kggs.c9000sdk.factory.state.event;

import com.alibaba.fastjson.JSONObject;
import com.kggs.c9000sdk.factory.StateFactory;
import com.kggs.c9000sdk.factory.state.NotifyState;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.rxbus.event.AlarmNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.vo.AlarmNotify;

/**
 * 报警主机事件通知
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:27
 */
public class AlarmState implements NotifyState {
    @Override
    public Event Serialize(Enum<Status> currentStatus, String szData) {
        if (currentStatus == Status.alarm) {
            AlarmNotify notify = JSONObject.parseObject(szData, AlarmNotify.class);
            AlarmNotifyEvent event = new AlarmNotifyEvent(notify);
            return event;
        } else {
            return StateFactory.Serialize(Status.machine, szData);
        }
    }
}
