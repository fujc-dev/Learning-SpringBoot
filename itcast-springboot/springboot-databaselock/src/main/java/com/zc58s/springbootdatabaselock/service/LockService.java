package com.zc58s.springbootdatabaselock.service;

import com.zc58s.springbootdatabaselock.keys.LockKey;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/18 11:58
 */
public interface LockService {

    /**
     * 非阻塞尝试获取锁。
     *
     * @param lockKey
     * @return 调用立即返回。成功返回true，反之返回false
     */
    boolean tryLock(LockKey lockKey);

    /**
     * 阻塞尝试获取锁。在指定时间内，直到锁获取成功
     *
     * @param lockKey
     * @param millisecond 获取锁最多等待多久（单位：毫秒）
     * @return 调用立即返回。成功返回true，反之返回false
     * @throws InterruptedException
     */
    boolean tryLock(LockKey lockKey, long millisecond) throws InterruptedException;

    /**
     * 阻塞获取锁，直到锁获取成功，这个是不可取
     *
     * @param eLock
     * @return
     * @throws InterruptedException
     */
    boolean lock(LockKey eLock) throws InterruptedException;

    /**
     * 释放锁
     *
     * @param eLock
     * @return
     */
    boolean unLock(LockKey eLock);
}
