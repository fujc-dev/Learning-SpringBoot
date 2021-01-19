package com.zc58s.springbootdatabaselock.service;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/19 11:24
 */
public interface ClearDistributedLockService {
    /**
     * 清理过期分布式锁
     */
    void clear();
}
