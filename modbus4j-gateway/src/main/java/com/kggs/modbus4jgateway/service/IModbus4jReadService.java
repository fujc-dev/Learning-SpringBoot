package com.kggs.modbus4jgateway.service;


import com.kggs.modbus4jgateway.bean.Slave;

import java.util.List;

/**
 * 监控服务对象，用于维护设备Tcp连接
 *
 * <p>
 * 1、连接Modbus Salve；
 * 2、实时轮询最新的点位数据；
 * 3、消息入库（持久化）；
 * 4、消息转发，基于WebSocket消息协议；
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/10 15:01
 */
public interface IModbus4jReadService {

    /**
     * 初始化，调用该方法重置循环获取
     */
    void Reset();

    /**
     * 启动获取Modbus Slave数据
     *
     * @param slaves
     */
    void Start(List<Slave> slaves);

    /**
     * 启动获取Modbus Slave数据
     *
     * @param slaves
     * @param millis
     */
    void Start(List<Slave> slaves, long millis);
}
