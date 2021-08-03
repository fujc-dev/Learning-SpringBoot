package com.kggs.c9000sdk.factory.state.event;

import com.alibaba.fastjson.JSONObject;
import com.kggs.c9000sdk.factory.StateFactory;
import com.kggs.c9000sdk.factory.state.NotifyState;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.rxbus.event.ProtectionRemovalNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.UnKnownNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.base.AlarmNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.vo.AlarmNotify;
import com.kggs.c9000sdk.vo.UnKnownNotify;

/**
 * 报警主机事件通知--报警类型（撤布防）
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:27
 */
public class AlarmState implements NotifyState {
    @Override
    public Event Serialize(Enum<Status> currentStatus, String szData) {
        if (currentStatus == Status.alarm) {
            AlarmNotifyEvent event;
            AlarmNotify notify = JSONObject.parseObject(szData, AlarmNotify.class);
            switch (notify.getCidcode()) {
                //撤布防
                case "400":
                case "401":
                case "402":
                case "403":
                case "404":
                case "405":
                case "406":
                case "407":
                case "408":
                case "409":
                case "40A":
                case "40B":
                case "441":
                    UnKnownNotify unKnownNotify = JSONObject.parseObject(szData, UnKnownNotify.class);
                    UnKnownNotifyEvent unKnownNotifyEvent = new UnKnownNotifyEvent(unKnownNotify);
                    return unKnownNotifyEvent;
                case "40D":
                case "40E":
                case "456":
                case "462":
                case "463":
                case "464":
                case "465":
                case "466":
                case "467":
                    event = new ProtectionRemovalNotifyEvent(notify);
                    return event;
                //
                default:
                    event = new AlarmNotifyEvent(notify);
                    return event;

            }
        } else {
            return StateFactory.Serialize(Status.unknown, szData);
        }
    }
}
