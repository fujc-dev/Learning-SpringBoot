package com.kggs.c9000sdk;

import com.kggs.c9000sdk.factory.StateFactory;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.rxbus.RxBus;
import com.kggs.c9000sdk.rxbus.RxBusSubscriber;
import com.kggs.c9000sdk.rxbus.RxSubscriptions;
import com.kggs.c9000sdk.rxbus.event.ConnectNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.ProtectionRemovalNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.base.AlarmNotifyEvent;
import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.vo.ConnectNotify;
import com.kggs.c9000sdk.vo.ProtectionRemovalNotify;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import rx.Subscription;

@SpringBootTest
class C9000sdkApplicationTests {

    @Test
    void contextLoads() {
        Subscription mRxSub = RxBus.getDefault().toObservable(ConnectNotifyEvent.class).map(event -> event)
                .subscribe(new RxBusSubscriber<ConnectNotifyEvent>() {
                    @Override
                    protected void onEvent(ConnectNotifyEvent event) {
                        System.out.println(event.getEvent().toString());
                    }
                });
        RxSubscriptions.add(mRxSub);
        Subscription mAlarmRxSub = RxBus.getDefault().toObservable(ProtectionRemovalNotifyEvent.class).map(event -> event)
                .subscribe(new RxBusSubscriber<ProtectionRemovalNotifyEvent>() {
                    @Override
                    protected void onEvent(ProtectionRemovalNotifyEvent event) {
                        //过滤消息
                        System.out.println("application：" + event.getEvent().toString());
                    }
                });
        RxSubscriptions.add(mAlarmRxSub);
        ConnectNotify connectNotify = new ConnectNotify();
        connectNotify.setInfo("1");
        RxBus.getDefault().post(new Event(connectNotify));
        connectNotify.setInfo("2");
        RxBus.getDefault().post(new Event(connectNotify));
        connectNotify.setInfo("3");
        RxBus.getDefault().post(new Event(connectNotify));

        Logger logger = LoggerFactory.getLogger(Programs.class);
        logger.info("TestInfo 1");
        String szData = "{\"message\":\"system\",  \"status\":1, \"info\":\"已经连接管理平台\" }";
        Enum<Status> status = StateFactory.Format(szData);
        Event vo = StateFactory.Serialize(status, szData);
        RxBus.getDefault().post(vo);

        szData = "{\"message\":\"system\",  \"status\":1, \"info\":\"已经连接管理平台\" }";
        status = StateFactory.Format(szData);
        vo = StateFactory.Serialize(status, szData);
        RxBus.getDefault().post(vo);

        szData = "{\"message\":\"machine\",\"commtype\":1,\"connect\":1896,\"ip\":\"127.0.0.1\",\"port\":6003,\"status\":1}";
        status = StateFactory.Format(szData);
        vo = StateFactory.Serialize(status, szData);
        RxBus.getDefault().post(vo);

        szData = "{\"message\":\"alarm\",\"commtype\":1,\"connect\":2724,\"ip\":\"192.168.4.7\",\"port\":6000,\"eventflag\":0,\"eventtriger\":1,\"cidcode\":\"466\",\"cidlevel\":2,\"cidtype\":\"\n" +
                "撤布防\",\"cidmemo\":\"电脑\",\"partcode\":1,\"guardcode\":32}";
        status = StateFactory.Format(szData);
        vo = StateFactory.Serialize(status, szData);
        RxBus.getDefault().post(vo);


    }

}
