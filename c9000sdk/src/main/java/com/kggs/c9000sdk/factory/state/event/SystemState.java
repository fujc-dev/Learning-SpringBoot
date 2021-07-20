package com.kggs.c9000sdk.factory.state.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kggs.c9000sdk.factory.RxBusFactory;
import com.kggs.c9000sdk.factory.state.NotifyState;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.vo.ConnectNotify;
import com.kggs.c9000sdk.vo.base.VoBase;

/**
 * SDK和平台连接状态通知
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:26
 */
public class SystemState implements NotifyState {
    @Override
    public VoBase Serialize(Enum<Status> currentStatus, String szData) {
        if (currentStatus == Status.system) {
            return JSONObject.parseObject(szData, ConnectNotify.class);
        } else {
            return RxBusFactory.Serialize(Status.machine, szData);
        }
    }

}
