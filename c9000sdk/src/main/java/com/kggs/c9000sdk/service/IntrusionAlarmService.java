package com.kggs.c9000sdk.service;

/**
 * 入侵报警服务
 *
 * <p>
 * 参考资料：CSST-R&D-豪恩9000主机系统网络版V1.0_SDK规范说明书
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/15 17:14
 */
public interface IntrusionAlarmService {

    /**
     * 初始化客户端，调用其他SDK函数的前提。
     */
    boolean Init();

    /**
     * 释放SDK资源，在结束之前最后调用。
     */
    boolean UnInit();

    /**
     * 连接豪恩管理平台。
     */
    boolean Connect(String szIP, int nPort, int nTimeoutSecond);

    boolean Connect(String szIP, int nTimeoutSecond);

    /**
     * 断开与管理平台的连接。
     */
    boolean DisConnect();

    /**
     * 主机分区、防区布防
     */
    boolean OperatePlace(int nMachine, int nPlaceType, int nAreaNo);

    /**
     * 主机分区、防区撤防
     */
    boolean OperateRemove(int nMachine, int nRemoveType, int nAreaNo);
}
