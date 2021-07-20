package com.kggs.c9000sdk.factory.state.event;

import com.alibaba.fastjson.JSONObject;
import com.kggs.c9000sdk.factory.StateFactory;
import com.kggs.c9000sdk.factory.state.NotifyState;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.vo.MachineNotify;
import com.kggs.c9000sdk.vo.base.VoBase;

/**
 * 平台所管辖的报警主机状态
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:26
 */
public class MachineState implements NotifyState {
    @Override
    public VoBase Serialize( Enum<Status> currentStatus, String szData) {
        if (currentStatus == Status.machine) {
            return JSONObject.parseObject(szData, MachineNotify.class);
        } else {
            return StateFactory.Serialize(Status.alarm, szData);
        }
    }
}
