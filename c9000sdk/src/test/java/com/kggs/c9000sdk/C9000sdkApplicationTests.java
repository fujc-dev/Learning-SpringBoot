package com.kggs.c9000sdk;

import com.kggs.c9000sdk.factory.StateFactory;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.rxbus.RxBus;
import com.kggs.c9000sdk.rxbus.RxBusSubscriber;
import com.kggs.c9000sdk.rxbus.RxSubscriptions;
import com.kggs.c9000sdk.rxbus.event.Event;
import com.kggs.c9000sdk.vo.ConnectNotify;
import com.kggs.c9000sdk.vo.base.NotifyBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import rx.Subscription;

@SpringBootTest
class C9000sdkApplicationTests {

    @Test
    void contextLoads() {
        Subscription mRxSub = RxBus.getDefault().toObservable(Event.class).map(event -> event)
                .subscribe(new RxBusSubscriber<Event>() {
                    @Override
                    protected void onEvent(Event event) {
                        System.out.println(event.getEvent().toString());
                    }
                });
        RxSubscriptions.add(mRxSub);
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
        NotifyBase vo = StateFactory.Serialize(status, szData);
        RxBus.getDefault().post(new Event(vo));

        szData = "{\"message\":\"system\",  \"status\":1, \"info\":\"已经连接管理平台\" }";
        status = StateFactory.Format(szData);
        vo = StateFactory.Serialize(status, szData);
        RxBus.getDefault().post(new Event(vo));

        szData = "{\"message\":\"machine\",\"commtype\":1,\"connect\":1896,\"ip\":\"127.0.0.1\",\"port\":6003,\"status\":1}";
        status = StateFactory.Format(szData);
        vo = StateFactory.Serialize(status, szData);
        RxBus.getDefault().post(new Event(vo));

        szData = "{\"message\":\"alarm\",\"commtype\":1,\"connect\":2408,\"ip\":\"127.0.0.1\",\"port\":6016,\"eventflag\":1,\"EventTriger\":1,\"cidcode\":\"133\",\"cidlevel\":1,\"cidtype\":\"窃盗\",\"cidmemo\":\"24小时防区\",\"partcode\":1,\"guardcode\":4}";
        status = StateFactory.Format(szData);
        vo = StateFactory.Serialize(status, szData);
        RxBus.getDefault().post(new Event(vo));
        szData = " {\"message\":\"cidinfo\",\"cidcode\":\"000\",\"cidlevel\":0,\"cidtype\":\"错误\",\"cidmemo\":\"未明事件\"} ";
        status = StateFactory.Format(szData);
        vo = StateFactory.Serialize(status, szData);
        RxBus.getDefault().post(new Event(vo));


    }

}
