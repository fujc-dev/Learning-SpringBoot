package com.kggs.c9000sdk.sdk;

import java.util.HashMap;
import java.util.Map;

/**
 * 入侵暴击平台连接管理对象
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/3 11:11
 */
public class ConnectManager {
    /**
     * 维护
     */
    private static Map<String, Integer> containers = new HashMap<>();

    private static boolean connect_status = false;

    public static Integer GetConnectId(String ip) {
        if (containers.containsKey(ip)) {
            return containers.get(ip);
        }
        return -1;
    }

    public static void SetContainers(String ip, Integer connectId) {
        if (!containers.containsKey(ip)) {
            containers.put(ip, connectId);
        }
    }

    public static void SetConnectStatus(boolean status) {
        connect_status = status;
    }

    public static boolean GetConnectStatus() {
        return connect_status;
    }
}
