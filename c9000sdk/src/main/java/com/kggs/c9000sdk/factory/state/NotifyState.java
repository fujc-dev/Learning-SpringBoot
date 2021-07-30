package com.kggs.c9000sdk.factory.state;

import com.kggs.c9000sdk.rxbus.event.base.Event;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:17
 */
public interface NotifyState {

    /**
     * SDK和平台连接状态通知
     */
    Event Serialize(Enum<Status> currentStatus, String szData);


}
