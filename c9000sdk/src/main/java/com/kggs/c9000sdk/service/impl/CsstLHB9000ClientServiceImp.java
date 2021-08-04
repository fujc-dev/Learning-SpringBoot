package com.kggs.c9000sdk.service.impl;


import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import com.kggs.c9000sdk.rxbus.Bundle;
import com.kggs.c9000sdk.rxbus.RxBus;
import com.kggs.c9000sdk.rxbus.RxBusSubscriber;
import com.kggs.c9000sdk.rxbus.RxSubscriptions;
import com.kggs.c9000sdk.rxbus.event.ProtectionRemovalNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.base.AlarmNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.ConnectNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.MachineNotifyEvent;
import com.kggs.c9000sdk.sdk.ConnectManager;
import com.kggs.c9000sdk.service.ICsstLHB9000ClientService;
import com.kggs.c9000sdk.service.IntrusionAlarmService;
import com.kggs.c9000sdk.vo.ProtectionRemovalNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Subscription;


/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 9:25
 */
@Service
public class CsstLHB9000ClientServiceImp implements ICsstLHB9000ClientService {


    private final IntrusionAlarmService intrusionService;

    @Autowired
    public CsstLHB9000ClientServiceImp(IntrusionAlarmService intrusionService) {
        this.intrusionService = intrusionService;
    }
    //private static final Logger logger = LoggerFactory.getLogger(CsstLHB9000ClientServiceImp.class);

    public boolean OperatePlace(String szIP, int nPlaceType, int nAreaNo, String subControlIp) throws CsstLHB9000Exception {
        return OperatePlace(szIP, 6769, nPlaceType, nAreaNo, subControlIp);
    }

    public boolean OperatePlace(String szIP, int nPort, int nPlaceType, int nAreaNo, String subControlIp) throws CsstLHB9000Exception {
        final boolean[] _execute_status = {false};
        Subscription mAlarmRxSub = RxBus.getDefault().toObservable(ProtectionRemovalNotifyEvent.class).map(event -> event)
                .subscribe(new RxBusSubscriber<ProtectionRemovalNotifyEvent>() {
                    @Override
                    protected void onEvent(ProtectionRemovalNotifyEvent event) {
                        if (event.getEvent().getStatus() == ProtectionRemovalNotify.Status.Success) {
                            _execute_status[0] = true;
                        }
                    }
                });
        RxSubscriptions.add(mAlarmRxSub);
        try {
            if (ConnectManager.GetConnectStatus()) {
                //获取缓存中的获取的主机连接Id
                int connect_id = ConnectManager.GetConnectId(subControlIp);
                System.out.println("CSST：----连接平台成功，获取到指定的主机连接ID为：" + connect_id);
                if (connect_id == -1) {
                    System.out.println("CSST：----获取到指定的主机连接ID失败");
                    return false;
                }
                //执行布控
                boolean place_status = intrusionService.OperatePlace(connect_id, nPlaceType, nAreaNo);
                Thread.sleep(2000);
                if (place_status && _execute_status[0]) {
                    System.out.println("CSST：----执行布防操作成功");
                    return true;
                } else {
                    System.out.println("CSST：----执行布防操作失败");
                    return false;
                }
            } else {
                System.out.println("CSST：----连接平台失败");
            }
            return false;
        } catch (Exception e) {
            System.out.println("xxxxxxxxxxxx");
        } finally {
            RxSubscriptions.remove(mAlarmRxSub);
        }
        return false;
    }

    public boolean OperateRemove(String szIP, int nRemoveType, int nAreaNo, String subControlIp) throws CsstLHB9000Exception {
        return OperateRemove(szIP, 6769, nRemoveType, nAreaNo, subControlIp);
    }

    public boolean OperateRemove(String szIP, int nPort, int nRemoveType, int nAreaNo, String subControlIp) throws CsstLHB9000Exception {
        final boolean[] _execute_status = {false};
        Subscription mAlarmRxSub = RxBus.getDefault().toObservable(ProtectionRemovalNotifyEvent.class).map(event -> event)
                .subscribe(new RxBusSubscriber<ProtectionRemovalNotifyEvent>() {
                    @Override
                    protected void onEvent(ProtectionRemovalNotifyEvent event) {
                        if (event.getEvent().getStatus() == ProtectionRemovalNotify.Status.Success) {
                            _execute_status[0] = true;
                        }
                    }
                });
        RxSubscriptions.add(mAlarmRxSub);
        try {
            if (ConnectManager.GetConnectStatus()) {
                //获取缓存中的获取的主机连接Id
                int connect_id = ConnectManager.GetConnectId(subControlIp);
                System.out.println("CSST：----连接平台成功，获取到指定的主机连接ID为：" + connect_id);
                if (connect_id == -1) {
                    System.out.println("CSST：----获取到指定的主机连接ID失败");
                    return false;
                }
                //执行布控
                boolean place_status = intrusionService.OperateRemove(connect_id, nRemoveType, nAreaNo);
                Thread.sleep(2000);
                if (place_status && _execute_status[0]) {
                    System.out.println("CSST：----执行撤防操作成功");
                    return true;
                } else {
                    System.out.println("CSST：----执行撤防操作失败");
                    return false;
                }
            } else {
                System.out.println("CSST：----连接平台失败");
            }
            return false;
        } catch (Exception e) {
            System.out.println("xxxxxxxxxxxx");
        } finally {
            RxSubscriptions.remove(mAlarmRxSub);
        }
        return false;
    }

}
