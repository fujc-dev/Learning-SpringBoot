package com.kggs.c9000sdk.service;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 9:25
 */
public interface ICsstLHB9000ClientService {

    /**
     * 主机分区、防区布防
     *
     * @param szIP
     * @param nMachine
     * @param nPlaceType
     * @param nAreaNo
     * @return
     */
    boolean OperatePlace(String szIP, int nMachine, int nPlaceType, int nAreaNo);

    /**
     * 主机分区、防区布防
     *
     * @param szIP
     * @param nPort
     * @param nMachine
     * @param nPlaceType
     * @param nAreaNo
     * @return
     */
    boolean OperatePlace(String szIP, int nPort, int nMachine, int nPlaceType, int nAreaNo);

    /**
     * 主机分区、防区撤防
     *
     * @param szIP
     * @param nMachine
     * @param nRemoveType
     * @param nAreaNo
     * @return
     */
    boolean OperateRemove(String szIP, int nMachine, int nRemoveType, int nAreaNo);

    /**
     * 主机分区、防区撤防
     *
     * @param szIP
     * @param nPort
     * @param nMachine
     * @param nRemoveType
     * @param nAreaNo
     * @return
     */
    boolean OperateRemove(String szIP, int nPort, int nMachine, int nRemoveType, int nAreaNo);
}
