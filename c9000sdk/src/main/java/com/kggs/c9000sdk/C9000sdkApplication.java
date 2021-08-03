package com.kggs.c9000sdk;

import com.kggs.c9000sdk.config.C9000SdkConfig;
import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import com.kggs.c9000sdk.rxbus.Bundle;
import com.kggs.c9000sdk.rxbus.RxBus;
import com.kggs.c9000sdk.rxbus.RxBusSubscriber;
import com.kggs.c9000sdk.rxbus.RxSubscriptions;
import com.kggs.c9000sdk.rxbus.event.base.AlarmNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.ConnectNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.MachineNotifyEvent;
import com.kggs.c9000sdk.sdk.ConnectManager;
import com.kggs.c9000sdk.service.IntrusionAlarmService;
import com.kggs.c9000sdk.utils.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rx.Subscription;

@SpringBootApplication
public class C9000sdkApplication {


    public static void main(String[] args) {

        SpringApplication.run(C9000sdkApplication.class, args);

        RxSubscriptions.clear();
        //SDK和平台连接状态通知
        Subscription mConnectRxSub = Bundle.AddSubscription(ConnectNotifyEvent.class, new RxBusSubscriber<ConnectNotifyEvent>() {
            @Override
            protected void onEvent(ConnectNotifyEvent event) throws CsstLHB9000Exception {
                if (event.getEvent().getStatus() == 1) {
                    ConnectManager.SetConnectStatus(true);
                } else {
                    ConnectManager.SetConnectStatus(false);
                }
            }
        });

        //订阅接收平台所管辖的报警主机状态
        Subscription mRxSub = Bundle.AddSubscription(MachineNotifyEvent.class, new RxBusSubscriber<MachineNotifyEvent>() {
            @Override
            protected void onEvent(MachineNotifyEvent event) throws CsstLHB9000Exception {
                ConnectManager.SetContainers(event.getEvent().getIp(), event.getEvent().getConnect());
            }
        });

        Subscription mAlarmRxSub = RxBus.getDefault().toObservable(AlarmNotifyEvent.class).map(event -> event)
                .subscribe(new RxBusSubscriber<AlarmNotifyEvent>() {
                    @Override
                    protected void onEvent(AlarmNotifyEvent event) {
                        //过滤消息
                        System.out.println("application：" + event.getEvent().toString());
                    }
                });
        RxSubscriptions.add(mRxSub);
        RxSubscriptions.add(mConnectRxSub);
        RxSubscriptions.add(mAlarmRxSub);

        //连接到入侵报警平台
        IntrusionAlarmService intrusionService = SpringContextUtil.getBean(IntrusionAlarmService.class);
        C9000SdkConfig config = SpringContextUtil.getBean(C9000SdkConfig.class);
        try {
            System.out.println("CSST：----初始化SDK9000Client.dll");
            boolean _init_status = intrusionService.Init();
            if (_init_status) {
                System.out.println("CSST：----初始化平台Sdk成功，等待连接到平台");
                boolean _connect_status = intrusionService.Connect(config.getServer(), config.getPort(), 5);
                if(_connect_status){

                }
            }
        } catch (CsstLHB9000Exception e) {
            System.out.println("CSST：----初始化平台Sdk失败");
        }
    }

}
