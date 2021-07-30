package com.kggs.c9000sdk;

import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import com.kggs.c9000sdk.rxbus.RxBus;
import com.kggs.c9000sdk.rxbus.RxBusSubscriber;
import com.kggs.c9000sdk.rxbus.RxSubscriptions;
import com.kggs.c9000sdk.rxbus.event.AlarmNotifyEvent;
import com.kggs.c9000sdk.service.IntrusionAlarmService;
import com.kggs.c9000sdk.utils.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rx.Subscription;

@SpringBootApplication
public class C9000sdkApplication {


    public static void main(String[] args) {

        SpringApplication.run(C9000sdkApplication.class, args);
        IntrusionAlarmService intrusionService = SpringContextUtil.getBean(IntrusionAlarmService.class);
        try {
            System.out.println("CSST：----初始化SDK9000Client.dll");
            boolean _init_status = intrusionService.Init();
            if (_init_status) {
                System.out.println("CSST：----初始化平台Sdk成功，等待连接到平台");
            }
        } catch (CsstLHB9000Exception e) {
            System.out.println("CSST：----初始化平台Sdk失败");
        }
        RxSubscriptions.clear();
        Subscription mRxSub = RxBus.getDefault().toObservable(AlarmNotifyEvent.class).map(event -> event)
                .subscribe(new RxBusSubscriber<AlarmNotifyEvent>() {
                    @Override
                    protected void onEvent(AlarmNotifyEvent event) {
                        //过滤消息
                        System.out.println(event.getEvent().toString());
                    }
                });
        RxSubscriptions.add(mRxSub);
    }

}
