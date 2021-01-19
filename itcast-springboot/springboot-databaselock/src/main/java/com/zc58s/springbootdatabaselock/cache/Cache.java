package com.zc58s.springbootdatabaselock.cache;

import com.zc58s.springbootdatabaselock.entity.MstLock;
import com.zc58s.springbootdatabaselock.keys.LockKey;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 缓存管理器；
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/19 14:49
 */
public class Cache {

    /**
     * 维护锁缓存，用于检测当前是否已经
     */
    private static Map<LockKey, MstLock> containers = new HashMap<>(); //用于维护提交的数据库锁

    /**
     * 单实例构造方法
     */
    private Cache() {
        super();
    }

    /**
     * 得到缓存。同步静态方法
     *
     * @param key
     * @return
     */
    public synchronized static MstLock get(LockKey key) {
        return containers.get(key);
    }

    /**
     * 判断是否存在一个缓存
     *
     * @param key
     * @return
     */
    public synchronized static boolean containsKey(LockKey key) {
        return containers.containsKey(key);
    }

    /**
     * 清除所有缓存
     */
    public synchronized static void clear() {
        containers.clear();
    }

    /**
     * 清除指定的缓存
     *
     * @param key
     */
    public synchronized static void remove(LockKey key) {
        containers.remove(key);
    }

    /**
     * 载入缓存
     *
     * @param key
     * @param obj
     */
    public synchronized static void put(LockKey key, MstLock obj) {
        containers.put(key, obj);
    }
}