package com.kggs.c9000sdk.factory.state.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kggs.c9000sdk.factory.RxBusFactory;
import com.kggs.c9000sdk.factory.state.NotifyState;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.vo.AlarmNotify;
import com.kggs.c9000sdk.vo.ConnectNotify;
import com.kggs.c9000sdk.vo.base.VoBase;

/**
 * 报警主机事件通知
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:27
 */
public class AlarmState implements NotifyState {
    @Override
    public VoBase Serialize(Enum<Status> currentStatus, String szData) {
        if (currentStatus == Status.alarm) {
            return  JSONObject.parseObject(szData, AlarmNotify.class);
        } else {
            return RxBusFactory.Serialize(Status.machine, szData);
        }
    }
}
