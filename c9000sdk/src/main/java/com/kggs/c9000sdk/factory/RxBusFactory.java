package com.kggs.c9000sdk.factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.kggs.c9000sdk.factory.state.NotifyState;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.factory.state.event.AlarmState;
import com.kggs.c9000sdk.factory.state.event.CidinfoState;
import com.kggs.c9000sdk.factory.state.event.MachineState;
import com.kggs.c9000sdk.factory.state.event.SystemState;
import com.kggs.c9000sdk.rxbus.RxBus;
import com.kggs.c9000sdk.rxbus.event.Event;
import com.kggs.c9000sdk.vo.base.VoBase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对报警数据JSON字符串对应的消息类型进行统一处理
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 13:16
 */
public class RxBusFactory {
    /**
     *
     */
    private static Map<Enum<Status>, Enum<Status>> statusContainers = new ConcurrentHashMap<Enum<Status>, Enum<Status>>();

    /**
     *
     */
    private static Map<Enum<Status>, NotifyState> stateMap = new ConcurrentHashMap<Enum<Status>, NotifyState>();


    static {
        stateMap.put(Status.system, new SystemState());
        stateMap.put(Status.machine, new MachineState());
        stateMap.put(Status.alarm, new AlarmState());
        stateMap.put(Status.cidinfo, new CidinfoState());
    }

    private static synchronized void init(Enum<Status> status) {
        if (!statusContainers.containsKey(status))
            statusContainers.put(status, status);
    }

    /**
     * 格式化JSON字符串，获取当前报警数据的类型，并返回已定义的数据类型
     *
     * @param szData
     * @return
     */
    public static synchronized Enum<Status> Format(String szData) {
        Status status = Status.nothing;
        try {
            //获取消息提示的类型
            JSONObject jsonObject = JSON.parseObject(szData);
            if (jsonObject != null) {
                String message = String.valueOf(jsonObject.get("message"));
                status = Enum.valueOf(Status.class, message);
                RxBusFactory.init(status);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 根据不同的数据类型，序列化
     * @param currentStatus
     * @param szData
     * @return
     */
    public static synchronized VoBase Serialize(Enum<Status> currentStatus, String szData) {
        if (stateMap.containsKey(currentStatus)) {
            return stateMap.get(currentStatus).Serialize(currentStatus, szData);
        } else {
            VoBase voBase = new VoBase();
            voBase.setMessage(currentStatus.name());
            return voBase;
        }

    }
}
