package com.kggs.c9000sdk.rxbus;

import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import com.kggs.c9000sdk.rxbus.event.Event;
import rx.Subscriber;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/28 11:48
 */
public abstract class RxBusSubscriberBuilder  extends Subscriber<Event> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }
    protected abstract void onEvent(Event  t) throws CsstLHB9000Exception;

    @Override
    public void onNext(Event event) {
        try {
            onEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
