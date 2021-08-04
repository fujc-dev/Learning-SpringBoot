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
import com.kggs.c9000sdk.vo.ProtectionRemovalNotify;
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
            JSONObject jsonObject = JSONObject.parseObject(szData);
            String cidcode = String.valueOf(jsonObject.get("cidcode"));
            switch (cidcode) {
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
                    return CreateUnKnownNotify(szData);
                //撤布防报警类型处理节点，成功
                case "40D":
                case "456":
                case "464":
                case "465":
                case "466":
                    return CreateProtectionRemovalNotify(szData, ProtectionRemovalNotify.Status.Success);
                //撤布防报警类型处理节点，失败
                case "40E":
                case "462":
                case "463":
                case "467":
                    return CreateProtectionRemovalNotify(szData, ProtectionRemovalNotify.Status.Fail);
                //
                default:
                    return CreateDefaultNotify(szData);
            }
        }
        return StateFactory.Serialize(Status.unknown, szData);
    }

    private AlarmNotifyEvent CreateDefaultNotify(String szData) {
        AlarmNotify alarmNotify = JSONObject.parseObject(szData, AlarmNotify.class);
        return new AlarmNotifyEvent(alarmNotify);
    }

    /**
     * 创建撤布防事件消息
     *
     * @param szData 事件消息源
     * @param status 处理状态
     * @return
     */
    private ProtectionRemovalNotifyEvent CreateProtectionRemovalNotify(String szData, ProtectionRemovalNotify.Status status) {
        ProtectionRemovalNotify successNotify = JSONObject.parseObject(szData, ProtectionRemovalNotify.class);
        successNotify.setStatus(status);
        return new ProtectionRemovalNotifyEvent(successNotify);
    }

    /**
     * 创建未知被识别的事件消息
     *
     * @param szData
     * @return
     */
    private UnKnownNotifyEvent CreateUnKnownNotify(String szData) {
        UnKnownNotify unKnownNotify = JSONObject.parseObject(szData, UnKnownNotify.class);
        return new UnKnownNotifyEvent(unKnownNotify);
    }
}
