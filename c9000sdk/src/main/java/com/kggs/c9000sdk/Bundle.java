package com.kggs.c9000sdk;


import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import com.kggs.c9000sdk.factory.ServiceFactory;
import com.kggs.c9000sdk.service.ICsstLHB9000ClientService;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 10:18
 */
public class Bundle {

    /**
     * 主机分区、防区布防
     *
     * @param szIP       平台地址，用于链接平台
     * @param nMachine   主机连接ID
     * @param nPlaceType 分区或防区号（注意：如果该参数nAreaNo作为分区，取值范围0~15，如果该参数nAreaNo作为防区，取值范围0~247）
     * @param nAreaNo    分区、防区类别（
     *                   分区布防          1
     *                   分区周界布防      2
     *                   分区强制布防      3
     *                   分区强制周界布防  4
     *                   防区布防          5
     *                   ）
     */
    public static void csst_lhb9000_client_operate_place(String szIP, int nMachine, int nPlaceType, int nAreaNo) {
        try {
            ICsstLHB9000ClientService csstLHB9000ClientService = ServiceFactory.GetService(ICsstLHB9000ClientService.class);
            if (csstLHB9000ClientService != null) {
                csstLHB9000ClientService.OperatePlace(szIP, nMachine, nPlaceType, nAreaNo);
            }
        } catch (CsstLHB9000Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 主机分区、防区撤防
     *
     * @param szIP
     * @param nMachine    主机连接ID
     * @param nRemoveType 分区防区类别（分区撤防   1 防区撤防   2 ）
     * @param nAreaNo     分区或防区号（注意：如果该参数nAreaNo作为分区，取值范围0~15，如果该参数nAreaNo作为防区，取值范围0~247）
     */
    public static void csst_lhb9000_client_operate_remove(String szIP, int nMachine, int nRemoveType, int nAreaNo) {
        try {
            ICsstLHB9000ClientService csstLHB9000ClientService = ServiceFactory.GetService(ICsstLHB9000ClientService.class);
            if (csstLHB9000ClientService != null) {

                csstLHB9000ClientService.OperateRemove(szIP, nMachine, nRemoveType, nAreaNo);
            }
        } catch (CsstLHB9000Exception e) {
            e.printStackTrace();
        }
    }


}
