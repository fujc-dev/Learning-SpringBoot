package com.kggs.c9000sdk.service.impl;


import com.kggs.c9000sdk.annotations.ServiceImpl;
import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import com.kggs.c9000sdk.factory.ServiceFactory;
import com.kggs.c9000sdk.rxbus.RxBus;
import com.kggs.c9000sdk.rxbus.RxBusSubscriber;
import com.kggs.c9000sdk.rxbus.RxBusSubscriberBuilder;
import com.kggs.c9000sdk.rxbus.RxSubscriptions;
import com.kggs.c9000sdk.rxbus.event.Event;
import com.kggs.c9000sdk.service.ICsstLHB9000ClientService;
import com.kggs.c9000sdk.service.IntrusionAlarmService;
import com.kggs.c9000sdk.vo.MachineNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Subscription;

import java.util.concurrent.locks.LockSupport;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 9:25
 */
@ServiceImpl(classz = ICsstLHB9000ClientService.class)
public class CsstLHB9000ClientServiceImp implements ICsstLHB9000ClientService {

    private IntrusionAlarmService intrusionService = ServiceFactory.GetService(IntrusionAlarmService.class);
    private static final Logger logger = LoggerFactory.getLogger(CsstLHB9000ClientServiceImp.class);

    public boolean OperatePlace(String szIP, int nMachine, int nPlaceType, int nAreaNo) throws CsstLHB9000Exception {
        return OperatePlace(szIP, 6769, nMachine, nPlaceType, nAreaNo);
    }

    public boolean OperatePlace(String szIP, int nPort, int nMachine, int nPlaceType, int nAreaNo) throws CsstLHB9000Exception {
        try {
            Thread currentThread = Thread.currentThread();
            final boolean[] _execute_status = {false};
            //1、初始化
            boolean _init_status = intrusionService.Init();
            if (_init_status) {
                logger.info("CSST：----初始化平台Sdk成功，即将连接到平台");
                //2、连接平台
                boolean _connect_status = intrusionService.Connect(szIP, nPort, 5);
                //发起订阅，接收平台所管辖的报警主机状态
                RxSubscriptions.clear();
                Subscription mRxSub = RxBus.getDefault().toObservable().map(event -> event)
                        .subscribe(new RxBusSubscriberBuilder() {
                            @Override
                            protected void onEvent(Event event) throws CsstLHB9000Exception {
                                //此时就收到消息后，进行发送布防命令
                                if (event.getEvent().getClass().getGenericSuperclass() == MachineNotify.class) {
                                    System.out.println(event.getEvent().toString());
                                    if (_connect_status) {
                                        //3、设置布防
                                        _execute_status[0] = intrusionService.OperatePlace(nMachine, nPlaceType, nAreaNo);
                                        logger.info("CSST：----设置布防{}", _execute_status[0]);
                                        LockSupport.unpark(currentThread);
                                    }
                                }
                            }
                        });
                RxSubscriptions.add(mRxSub);
                //锁住当前线程，等待
                LockSupport.parkNanos(currentThread, 5000);
                RxSubscriptions.remove(mRxSub);
                logger.info("CSST：----连接平台失败");
                return _execute_status[0];
            }
            logger.info("CSST：----初始化平台Sdk失败");
            return false;
        } finally {
            //4、断开连接
            intrusionService.DisConnect();
            //5、释放SDK资源，在结束之前最后调用。
            intrusionService.UnInit();
        }
    }

    public boolean OperateRemove(String szIP, int nMachine, int nRemoveType, int nAreaNo) throws CsstLHB9000Exception {
        return OperateRemove(szIP, 6769, nMachine, nRemoveType, nAreaNo);
    }

    public boolean OperateRemove(String szIP, int nPort, int nMachine, int nRemoveType, int nAreaNo) throws CsstLHB9000Exception {
        try {
            Thread currentThread = Thread.currentThread();
            final boolean[] _execute_status = {false};
            //1、初始化
            boolean _init_status = intrusionService.Init();
            if (_init_status) {
                //2、连接平台
                boolean _connect_status = intrusionService.Connect(szIP, nPort, 5);
                //订阅回调的主机连接号
                RxSubscriptions.clear();
                Subscription mRxSub = RxBus.getDefault().toObservable().map(event -> event)
                        .subscribe(new RxBusSubscriberBuilder() {
                            @Override
                            protected void onEvent(Event event) throws CsstLHB9000Exception {
                                //此时就收到消息后，进行发送布防命令
                                if (event.getEvent().getClass().getGenericSuperclass() == MachineNotify.class) {
                                    System.out.println(event.getEvent().toString());
                                    if (_connect_status) {
                                        //3、设置布防
                                        _execute_status[0] = intrusionService.OperateRemove(nMachine, nRemoveType, nAreaNo);
                                        logger.info("CSST：----设置撤防{}", _execute_status[0]);
                                        LockSupport.unpark(currentThread);
                                    }
                                }
                            }
                        });
                RxSubscriptions.add(mRxSub);
                //锁住当前线程，等待
                LockSupport.parkNanos(currentThread, 5000);
                logger.info("CSST：----连接平台失败");
                RxSubscriptions.remove(mRxSub);
                return _execute_status[0];
            }
            return false;
        } finally {
            //4、断开连接
            intrusionService.DisConnect();
            //5、释放SDK资源，在结束之前最后调用。
            intrusionService.UnInit();
        }

    }
}
