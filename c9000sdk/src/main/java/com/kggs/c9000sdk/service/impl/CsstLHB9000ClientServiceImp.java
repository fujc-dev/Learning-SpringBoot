package com.kggs.c9000sdk.service.impl;


import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import com.kggs.c9000sdk.rxbus.RxBus;
import com.kggs.c9000sdk.rxbus.RxBusSubscriber;
import com.kggs.c9000sdk.rxbus.RxSubscriptions;
import com.kggs.c9000sdk.rxbus.event.ConnectNotifyEvent;
import com.kggs.c9000sdk.service.ICsstLHB9000ClientService;
import com.kggs.c9000sdk.service.IntrusionAlarmService;
import com.kggs.c9000sdk.vo.MachineNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Subscription;

import java.util.concurrent.locks.LockSupport;

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

    public boolean OperatePlace(String szIP, int nPlaceType, int nAreaNo) throws CsstLHB9000Exception {
        return OperatePlace(szIP, 6000, nPlaceType, nAreaNo);
    }

    public boolean OperatePlace(String szIP, int nPort, int nPlaceType, int nAreaNo) throws CsstLHB9000Exception {
        try {
            Thread currentThread = Thread.currentThread();
            final boolean[] _execute_status = {false};
            //发起订阅，接收平台所管辖的报警主机状态
            Subscription mRxSub = RxBus.getDefault().toObservable(ConnectNotifyEvent.class).map(event -> event)
                    .subscribe(new RxBusSubscriber<ConnectNotifyEvent>() {
                        @Override
                        protected void onEvent(ConnectNotifyEvent event) throws CsstLHB9000Exception {
                            //此时就收到消息后，进行发送布防命令
                            System.out.println(event.getEvent().toString());
                            LockSupport.unpark(currentThread);
                        }
                    });
            RxSubscriptions.add(mRxSub);
            //2、连接平台
            boolean _connect_status = intrusionService.Connect(szIP, nPort, 5);
            if (_connect_status) {
                System.out.println("CSST：----连接平台成功");
                //锁住当前线程，等待
                LockSupport.park(currentThread);
                RxSubscriptions.remove(mRxSub);
            } else {
                System.out.println("CSST：----连接平台失败");
            }
            System.out.println("CSST：----执行布防操作失败");
            return _execute_status[0];
        } catch (Exception e) {
            System.out.println("xxxxxxxxxxxx");
        } finally {
            //4、断开连接
            intrusionService.DisConnect();
            //5、释放SDK资源，在结束之前最后调用。
            //intrusionService.UnInit();
        }
        return false;
    }

    public boolean OperateRemove(String szIP, int nRemoveType, int nAreaNo) throws CsstLHB9000Exception {
        return OperateRemove(szIP, 6000, nRemoveType, nAreaNo);
    }

    public boolean OperateRemove(String szIP, int nPort, int nRemoveType, int nAreaNo) throws CsstLHB9000Exception {
        try {
            Thread currentThread = Thread.currentThread();
            final boolean[] _execute_status = {false};
            final MachineNotify[] machineNotify = {null};
            //订阅回调的主机连接号
            Subscription mRxSub = RxBus.getDefault().toObservable(ConnectNotifyEvent.class).map(event -> event)
                    .subscribe(new RxBusSubscriber<ConnectNotifyEvent>() {
                        @Override
                        protected void onEvent(ConnectNotifyEvent event) throws CsstLHB9000Exception {
                            //此时就收到消息后，进行发送布防命令
                            System.out.println(event.getEvent().toString());
                            LockSupport.unpark(currentThread);
                        }
                    });
            RxSubscriptions.add(mRxSub);
            //2、连接平台
            boolean _connect_status = intrusionService.Connect(szIP, nPort, 5);
            if (_connect_status) {
                System.out.println("CSST：----连接平台成功");
                //锁住当前线程，等待
                LockSupport.park(currentThread);
                RxSubscriptions.remove(mRxSub);
            } else {
                System.out.println("CSST：----连接平台失败");
            }
            //锁住当前线程，等待
            LockSupport.parkNanos(currentThread, 5000);
            System.out.println("CSST：----连接平台失败");
            RxSubscriptions.remove(mRxSub);
            return _execute_status[0];
        } finally {
            //4、断开连接
            intrusionService.DisConnect();
            //5、释放SDK资源，在结束之前最后调用。
            //intrusionService.UnInit();
        }

    }
}
