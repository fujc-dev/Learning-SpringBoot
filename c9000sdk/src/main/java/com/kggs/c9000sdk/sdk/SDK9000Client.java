package com.kggs.c9000sdk.sdk;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

import java.io.UnsupportedEncodingException;

/**
 * SDK接口规范均基于LHB9000约束：此接口规范只适用于LHB9000及键盘设备，通信协议是豪恩自定义协议。
 *
 * <p>
 * 参考资料：<br/>
 * <u>*《C9000软件通讯协议_V1.9.doc》</u> <br/>
 * <u>*《CSST-R&D-豪恩9000主机系统V1.0_SDK概要设计》</u> <br/>
 * <u>*《CSST-R&D-豪恩9000主机系统V1.1_SDK规范说明书》</u> <br/>
 * <u>*《CSST-R&D-豪恩9000主机系统网络版V1.0_SDK规范说明书》</u> <br/>
 * </p>
 *
 * <p>
 * 依赖jna对C++的dll文件进行简单封装。
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/15 17:23
 */
public interface SDK9000Client extends StdCallLibrary {

    String filePath = "D:\\SDK9000NET_v3.0.3\\SDK9000Client";
    SDK9000Client INSTANCE = Native.load(filePath, SDK9000Client.class);


    /**
     *  回调接口
     */
    interface SDK9000ClientCallBack extends StdCallLibrary.StdCallCallback {

        /**
         * 回调函数原型csst_lhb9000_callback
         *
         * @param szData      接收数据(详见json数据格式说明.txt)
         * @param nDataLength 接收数据长度
         * @throws UnsupportedEncodingException
         */
        default void invoke(String szData, int nDataLength) throws UnsupportedEncodingException {

        }
    }
    /**
     * 初始化客户端，调用其他SDK函数的前提。
     *
     * @param callback       回调函数
     * @param bAutoReConnect 连接管理平台断线后是否自动重连
     * @return
     */
    boolean csst_lhb9000_client_init(SDK9000ClientCallBack callback, boolean bAutoReConnect);

    /**
     * 释放SDK资源，在结束之前最后调用。
     *
     * @return
     */
    boolean csst_lhb9000_client_uninit();

    /**
     * 连接豪恩管理平台。
     *
     * @param szIP           管理平台IP;
     * @param nPort          端口 6769
     * @param nTimeoutSecond 连接超时3~5秒
     * @return
     */
    boolean csst_lhb9000_client_connect(String szIP, int nPort, int nTimeoutSecond);

    /**
     * 断开与管理平台的连接。
     *
     * @return
     */
    boolean csst_lhb9000_client_disconnect();

    /**
     * 主机分区、防区布防
     *
     * @param nMachine   主机连接ID
     * @param nPlaceType 分区、防区类别（
     *                   分区布防          1
     *                   分区周界布防      2
     *                   分区强制布防      3
     *                   分区强制周界布防  4
     *                   防区布防          5
     *                   ）
     * @param nAreaNo    分区或防区号 （注意：如果该参数nAreaNo作为分区，取值范围0~15
     *                   如果该参数nAreaNo作为防区，取值范围0~247）
     * @return
     */
    boolean csst_lhb9000_client_operate_place(int nMachine, int nPlaceType, int nAreaNo);

    /**
     * 主机分区、防区撤防
     *
     * @param nMachine    主机连接ID
     * @param nRemoveType 分区防区类别（
     *                    分区撤防   1
     *                    防区撤防   2
     *                    ）
     * @param nAreaNo     分区或防区号
     *                    （注意：如果该参数nAreaNo作为分区，取值范围0~15
     *                    如果该参数nAreaNo作为防区，取值范围0~247）
     * @return
     */
    boolean csst_lhb9000_client_operate_remove(int nMachine, int nRemoveType, int nAreaNo);


    /**
     * 主机旁路、恢复旁路
     *
     * @param nMachine    与主机连接ID号
     * @param nZoneNo     防区号（取值范围0~247）
     * @param operateType 操作类型（ 旁路恢复 0 旁路 1   ）
     * @return
     */
    boolean csst_lhb9000_client_operate_bypass(int nMachine, int nZoneNo, int operateType);


}
