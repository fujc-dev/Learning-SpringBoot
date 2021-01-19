package com.zc58s.springbootdatabaselock.service.impl;

import com.zc58s.springbootdatabaselock.cache.Cache;
import com.zc58s.springbootdatabaselock.dao.DatabaseLockRepository;
import com.zc58s.springbootdatabaselock.entity.MstLock;
import com.zc58s.springbootdatabaselock.keys.LockKey;
import com.zc58s.springbootdatabaselock.service.ClearDistributedLockService;
import com.zc58s.springbootdatabaselock.service.DistributedLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/18 11:58
 */
@Service
public class DistributedLockServiceImpl implements DistributedLockService, ClearDistributedLockService {

    private DatabaseLockRepository repository;

    @Autowired
    public DistributedLockServiceImpl(DatabaseLockRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean tryLock(LockKey lockKey) {
        //获取锁之前，要检测锁是否存在，如果存在只需要修改锁状态，如果当前锁不存在，创建新的所记录
        try {
            MstLock lock = null;
            if (!repository.existsById(lockKey)) {    //如果没有找到该Key的锁，创建锁
                lock = new MstLock(lockKey);
            } else { //如果已找到，检测其锁的状态，
                lock = repository.getOne(lockKey);
                if (lock.getLocked().equals("0")) { //如果为0，为已获取锁，
                    return false;
                }
                //为1，为未获取锁，可以拿到锁，修改锁状态，并保存
                lock.setLocked("0");
            }
            lock = repository.save(lock);
            if (lock != null) {
               Cache.put(lockKey, lock);
            }
            return lock != null ? true : false;
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public synchronized boolean tryLock(LockKey lockKey, long millisecond) {
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
    public synchronized boolean lock(LockKey lockKey) {
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
        Cache.remove(lockKey);
        return repository.save(lock) != null ? true : false;
    }

    @Override
    public void clear() {
        List<MstLock> lockList = repository.findAll();
        for (MstLock lock : lockList) {
            MstLock val = Cache.get(lock.getId());
            if (lock.getExpireTime() <= System.currentTimeMillis() && val == null && lock.getLocked() == "0") {  //检测是否已经过期
                lock.setLocked("1");
            } else {
                //为当前对象自动续期10s
                lock.setExpireTime(new Date(System.currentTimeMillis() + 10 * 1000).getTime());
            }
        }
        repository.saveAll(lockList);
    }

}
