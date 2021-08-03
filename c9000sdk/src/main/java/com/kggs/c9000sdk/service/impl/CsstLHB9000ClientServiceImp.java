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
        try {
            if (ConnectManager.GetConnectStatus()) {
                //获取缓存中的获取的主机连接Id
                int connect_id = ConnectManager.GetConnectId(subControlIp);
                System.out.println("CSST：----连接平台成功，获取到指定的主机连接ID为：" + connect_id);
                if (connect_id == -1) {
                    System.out.println("CSST：----获取到指定的主机连接ID失败");
                    return false;
                }
                Subscription mAlarmRxSub = RxBus.getDefault().toObservable(ProtectionRemovalNotifyEvent.class).map(event -> event)
                        .subscribe(new RxBusSubscriber<ProtectionRemovalNotifyEvent>() {
                            @Override
                            protected void onEvent(ProtectionRemovalNotifyEvent event) {

                            }
                        });
                RxSubscriptions.add(mAlarmRxSub);
                //执行布控
                boolean place_status = intrusionService.OperatePlace(connect_id, nPlaceType, nAreaNo);
                if (place_status) {
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
        } catch (
                Exception e) {
            System.out.println("xxxxxxxxxxxx");
        } finally {
            //4、断开连接
            //intrusionService.DisConnect();
        }
        return false;
    }

    public boolean OperateRemove(String szIP, int nRemoveType, int nAreaNo, String subControlIp) throws CsstLHB9000Exception {
        return OperateRemove(szIP, 6769, nRemoveType, nAreaNo, subControlIp);
    }

    public boolean OperateRemove(String szIP, int nPort, int nRemoveType, int nAreaNo, String subControlIp) throws CsstLHB9000Exception {
        try {
            final boolean[] _connect_status_callback = {false};
            final int[] _machine_connect_id = {-1};
            Subscription mConnectRxSub = Bundle.AddSubscription(ConnectNotifyEvent.class, new RxBusSubscriber<ConnectNotifyEvent>() {
                @Override
                protected void onEvent(ConnectNotifyEvent event) throws CsstLHB9000Exception {
                    _connect_status_callback[0] = event.getEvent().getStatus() == 1 ? true : false;
                }
            });

            //1、发起订阅，接收平台所管辖的报警主机状态
            Subscription mRxSub = Bundle.AddSubscription(MachineNotifyEvent.class, new RxBusSubscriber<MachineNotifyEvent>() {
                @Override
                protected void onEvent(MachineNotifyEvent event) throws CsstLHB9000Exception {
                    if (subControlIp.equals(event.getEvent().getIp())) {
                        //获取与分控一致的主机连接ID
                        _machine_connect_id[0] = event.getEvent().getConnect();
                    }
                }
            });
            RxSubscriptions.add(mConnectRxSub);
            RxSubscriptions.add(mRxSub);
            //2、连接平台
            boolean _connect_status = intrusionService.Connect(szIP, nPort, 5);
            //锁住当前线程，等待
            if (_connect_status) {
                //等待回调，直接定义一个固定超时时间就好了,如果2s没有收到连接成功与当前平台所管辖的报警主机状态数据
                Thread.sleep(2000);
                //LockSupport.park(currentThread);
                if (_connect_status_callback[0]) {
                    System.out.println("CSST：----连接平台成功，获取到指定的主机连接ID为：" + _machine_connect_id[0]);
                    RxSubscriptions.remove(mConnectRxSub);
                    RxSubscriptions.remove(mRxSub);
                    if (_machine_connect_id[0] == -1) {
                        System.out.println("CSST：----获取到指定的主机连接ID失败");
                        return false;
                    }
                    //执行布控
                    boolean place_status = intrusionService.OperatePlace(_machine_connect_id[0], nRemoveType, nAreaNo);
                    if (place_status) {
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
            }
            return false;
        } catch (Exception e) {
            System.out.println("xxxxxxxxxxxx");
        } finally {
            //4、断开连接
            intrusionService.DisConnect();
        }
        return false;

    }

}
