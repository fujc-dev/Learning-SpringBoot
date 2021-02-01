package com.zc58s.book1.chapterone.locked;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/29 16:22
 */
public class LockUseCase {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
        } finally {
            lock.unlock();
        }
    }
}
