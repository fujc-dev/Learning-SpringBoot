package com.zc58s.springbootdatabaselock.service.impl;

import com.zc58s.springbootdatabaselock.dao.DatabaseLockRepository;
import com.zc58s.springbootdatabaselock.entity.MstLock;
import com.zc58s.springbootdatabaselock.keys.LockKey;
import com.zc58s.springbootdatabaselock.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/18 11:58
 */
@Service
public class LockServiceImpl implements LockService {

    private final DatabaseLockRepository repository;

    @Autowired
    public LockServiceImpl(DatabaseLockRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean tryLock(LockKey lockKey) {
        //获取锁之前，要检测锁是否存在，如果存在只需要修改锁状态，如果当前锁不存在，创建新的所记录
        MstLock lock = repository.getOne(lockKey);
        if (lock == null) {    //如果没有找到该Key的锁，创建锁
            lock = new MstLock(lockKey);
        } else { //如果已找到，检测其锁的状态，
            if (lock.getLocked().equals("0")) { //如果为0，为已获取锁，
                return false;
            }
            //为1，为未获取锁，可以拿到锁，修改锁状态，并保存
            lock.setLocked("0");
        }
        return repository.save(lock) != null ? true : false;
    }

    @Override
    public synchronized boolean tryLock(LockKey lockKey, long millisecond) throws InterruptedException {
        //声明获取锁结束时间
        long over_time = System.currentTimeMillis() + millisecond;
        boolean state = false;
        do {
            long current_time = System.currentTimeMillis();
            //如果当前时间大于结束时间，默认获取锁失败
            if (over_time < current_time) return false;
            state = this.tryLock(lockKey);
        }
        while (!state);
        return true;
    }

    @Override
    public synchronized boolean lock(LockKey lockKey) throws InterruptedException {
        boolean state = false;
        do {
            state = this.tryLock(lockKey);
        }
        while (!state);
        return true;
    }

    @Override
    public boolean unLock(LockKey lockKey) {
        MstLock lock = repository.getOne(lockKey);
        lock.setLocked("1");
        return repository.save(lock) != null ? true : false;
    }
}
