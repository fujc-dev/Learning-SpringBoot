package com.kggs.c9000sdk.service;

import com.kggs.c9000sdk.exception.CsstLHB9000Exception;

/**
 * 入侵报警服务
 * <p>
 * 参考资料：CSST-R&D-豪恩9000主机系统网络版V1.0_SDK规范说明书
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 9:25
 */
public interface ICsstLHB9000ClientService {

    /**
     * 主机分区、防区布防
     *
     * @param szIP         平台服务器Ip地址
     * @param nPlaceType   分区防区类别
     * @param nAreaNo
     * @param subControlIp 分控Ip地址，现场部署的入侵报警部署在不通的机器上，该参数用于判断控制那台分控服务器
     * @return
     */
    boolean OperatePlace(String szIP, int nPlaceType, int nAreaNo, String subControlIp) throws CsstLHB9000Exception;

    /**
     * 主机分区、防区布防
     *
     * @param szIP         平台服务器Ip地址
     * @param nPort        平台服务器端口
     * @param nPlaceType   分区防区类别
     * @param nAreaNo      分区或防区号
     * @param subControlIp 分控Ip地址，现场部署的入侵报警部署在不通的机器上，该参数用于判断控制那台分控服务器
     * @return
     */
    boolean OperatePlace(String szIP, int nPort, int nPlaceType, int nAreaNo, String subControlIp) throws CsstLHB9000Exception;

    /**
     * 主机分区、防区撤防
     *
     * @param szIP         平台服务器Ip地址
     * @param nRemoveType  分区防区类别
     * @param nAreaNo      分区或防区号
     * @param subControlIp 分控Ip地址，现场部署的入侵报警部署在不通的机器上，该参数用于判断控制那台分控服务器
     * @return
     */
    boolean OperateRemove(String szIP, int nRemoveType, int nAreaNo, String subControlIp) throws CsstLHB9000Exception;

    /**
     * 主机分区、防区撤防
     *
     * @param szIP         平台服务器Ip地址
     * @param nPort        平台服务器端口
     * @param nRemoveType  分区防区类别
     * @param nAreaNo      分区或防区号
     * @param subControlIp 分控Ip地址，现场部署的入侵报警部署在不通的机器上，该参数用于判断控制那台分控服务器
     * @return
     */
    boolean OperateRemove(String szIP, int nPort, int nRemoveType, int nAreaNo, String subControlIp) throws CsstLHB9000Exception;
}
