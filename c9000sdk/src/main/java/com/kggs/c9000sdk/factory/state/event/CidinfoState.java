package com.kggs.c9000sdk.factory.state.event;

import com.alibaba.fastjson.JSONObject;
import com.kggs.c9000sdk.factory.state.NotifyState;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.rxbus.event.CIDNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.vo.CIDNotify;

/**
 * CID码详细信息
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:27
 */
public class CidinfoState implements NotifyState {
    @Override
    public Event Serialize(Enum<Status> currentStatus, String szData) {
        CIDNotify notify = JSONObject.parseObject(szData, CIDNotify.class);
        CIDNotifyEvent event = new CIDNotifyEvent(notify);
        return event;
    }
}
