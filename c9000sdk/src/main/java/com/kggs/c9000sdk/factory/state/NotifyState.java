package com.kggs.c9000sdk.factory.state;

import com.kggs.c9000sdk.vo.base.NotifyBase;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:17
 */
public interface NotifyState {

    /**
     * SDK和平台连接状态通知
     */
    NotifyBase Serialize(Enum<Status> currentStatus, String szData);


}
