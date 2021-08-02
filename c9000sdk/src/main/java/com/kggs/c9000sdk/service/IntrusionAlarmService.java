package com.kggs.c9000sdk.service;

import com.kggs.c9000sdk.exception.CsstLHB9000Exception;

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
    boolean Init() throws CsstLHB9000Exception;

    /**
     * 释放SDK资源，在结束之前最后调用。
     */
    boolean UnInit() throws CsstLHB9000Exception;

    /**
     * 连接豪恩管理平台。
     */
    boolean Connect(String szIP, int nPort, int nTimeoutSecond) throws CsstLHB9000Exception;

    /**
     *  连接豪恩管理平台
     * @param szIP
     * @param nTimeoutSecond
     * @return
     * @throws CsstLHB9000Exception
     */
    boolean Connect(String szIP, int nTimeoutSecond) throws CsstLHB9000Exception;

    /**
     *  查询报警主机一览表（包括在线、离线主机）
     * @return
     */
    boolean QueryMachinelist() throws CsstLHB9000Exception;


    /**
     * 查询某一台主机分区、防区详细信息
     * @param nMachine 与主机的连接ID号
     * @return
     */
    boolean QueryMachineAreainfo(int nMachine) throws CsstLHB9000Exception;

    /**
     * 断开与管理平台的连接。
     */
    boolean DisConnect() throws CsstLHB9000Exception;

    /**
     * 主机分区、防区布防
     */
    boolean OperatePlace(int nMachine, int nPlaceType, int nAreaNo) throws CsstLHB9000Exception;

    /**
     * 主机分区、防区撤防
     */
    boolean OperateRemove(int nMachine, int nRemoveType, int nAreaNo) throws CsstLHB9000Exception;
}
