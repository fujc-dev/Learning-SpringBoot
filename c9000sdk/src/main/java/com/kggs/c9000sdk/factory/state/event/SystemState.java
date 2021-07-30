package com.kggs.c9000sdk.factory.state.event;

import com.alibaba.fastjson.JSONObject;
import com.kggs.c9000sdk.factory.StateFactory;
import com.kggs.c9000sdk.factory.state.NotifyState;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.rxbus.event.ConnectNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.vo.ConnectNotify;

/**
 * SDK和平台连接状态通知
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:26
 */
public class SystemState implements NotifyState {
    @Override
    public Event Serialize(Enum<Status> currentStatus, String szData) {
        if (currentStatus == Status.system) {
            ConnectNotify notify = JSONObject.parseObject(szData, ConnectNotify.class);
            return new ConnectNotifyEvent(notify);
        } else {
            return StateFactory.Serialize(Status.machine, szData);
        }
    }

}
