package com.kggs.c9000sdk.rxbus;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 管理 CompositeSubscription
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 11:25
 */
public class RxSubscriptions {
    private static CompositeSubscription mSubscriptions = new CompositeSubscription();

    public static boolean isUnsubscribed() {
        return mSubscriptions.isUnsubscribed();
    }

    public static void add(Subscription s) {
        if (s != null) {
            mSubscriptions.add(s);
        }
    }

    public static void remove(Subscription s) {
        if (s != null) {
            mSubscriptions.remove(s);
        }
    }

    public static void clear() {
        mSubscriptions.clear();
    }

    public static void unsubscribe() {
        mSubscriptions.unsubscribe();
    }

    public static boolean hasSubscriptions() {
        return mSubscriptions.hasSubscriptions();
    }
}
