package com.kggs.c9000sdk.factory.state.event;

import com.alibaba.fastjson.JSONObject;
import com.kggs.c9000sdk.factory.state.NotifyState;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.vo.CIDNotify;
import com.kggs.c9000sdk.vo.base.VoBase;

/**
 * CID码详细信息
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:27
 */
public class CidinfoState implements NotifyState {
    @Override
    public VoBase Serialize(Enum<Status> currentStatus, String szData) {
        return  JSONObject.parseObject(szData, CIDNotify.class);
    }
}
