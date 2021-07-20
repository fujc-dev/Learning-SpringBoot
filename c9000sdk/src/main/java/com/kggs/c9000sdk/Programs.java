package com.kggs.c9000sdk;

import com.kggs.c9000sdk.rxbus.RxBus;
import com.kggs.c9000sdk.rxbus.RxBusSubscriber;
import com.kggs.c9000sdk.rxbus.RxSubscriptions;
import com.kggs.c9000sdk.rxbus.event.Event;
import com.kggs.c9000sdk.vo.ConnectNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Subscription;
import rx.functions.Func1;

import java.util.UUID;

/**
 * 豪恩（红外、一键报警、声光报警）测试入口程序
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 9:07
 */
public class Programs {

    private static Subscription mRxSub, mRxSubSticky;

    /**
     * @param args
     */
    public static void main(String[] args) {
        //
        Logger logger = LoggerFactory.getLogger(Programs.class);
        logger.info("TestInfo 1");
        Bundle.csst_lhb9000_client_operate_place("127.0.0.1", 1, 1, 1);
        Bundle.csst_lhb9000_client_operate_remove("127.0.0.1", 1, 1, 1);
        RxSubscriptions.remove(mRxSub);
        mRxSub = RxBus.getDefault().toObservable(Event.class).map(event -> event)
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
    }


}
