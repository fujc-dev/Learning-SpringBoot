package com.kggs.c9000sdk.rxbus;

import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import com.kggs.c9000sdk.rxbus.event.ConnectNotifyEvent;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

import java.util.concurrent.locks.LockSupport;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/30 15:29
 */
public class Bundle {

    /**
     * 添加订阅
     *
     * @param eventType
     * @param subscriber
     * @param <T>
     */
    public static <T> Subscription AddSubscription(Class<T> eventType, Subscriber<? super T> subscriber) {
        return RxBus.getDefault().toObservable(eventType).map(event -> event).subscribe(subscriber);
    }

}
