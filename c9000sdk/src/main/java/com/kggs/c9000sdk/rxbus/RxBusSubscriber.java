package com.kggs.c9000sdk.rxbus;

import rx.Subscriber;

/**
 * 为RxBus使用的Subscriber, 主要提供next事件的try,catch
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 11:16
 */
public abstract class RxBusSubscriber<T> extends Subscriber<T> {
    @Override
    public void onNext(T t) {
        try {
            onEvent(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    protected abstract void onEvent(T t);
}
