package com.zc58s.springbootdatabaselock.entity;

import com.zc58s.springbootdatabaselock.converter.LockedConverter;
import com.zc58s.springbootdatabaselock.enums.Locked;
import com.zc58s.springbootdatabaselock.keys.LockKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * (MstLock)实体类
 *
 * @author makejava
 * @since 2021-01-18 11:38:24
 */
@Entity
@Table(name = "mst_lock")
public class MstLock implements Serializable {
    private static final long serialVersionUID = -46564120974277618L;

    /**
     * 默认无参构造函数
     */
    public MstLock() {
    }

    /**
     * @param lockKey
     */
    public MstLock(LockKey lockKey) {
        this(lockKey.getType(), lockKey.getNode());
    }

    /**
     * @param type
     * @param node
     */
    public MstLock(String type, String node) {
        this.id = new LockKey(type, node);
        this.locked = Locked.LOCK;
        this.createTime = System.currentTimeMillis();
        this.expireTime = new Date(this.createTime + 10 * 1000).getTime();
    }

    @EmbeddedId
    private LockKey id;
    /**
     * 当前处理的数据唯一标识
     */
    @Column(name = "data_id")
    private String dataId;
    /**
     * 备注
     */
    @Column(name = "mark")
    private String mark;
    /**
     * 是否上锁（1-有锁，0-无锁）
     */
    @Convert(converter = LockedConverter.class)
    @Column(name = "locked")
    private Locked locked;

    /**
     * 过期时间
     */
    @Column(name = "expire_time")
    private long expireTime;
    /**
     * 创建时间(用于释放锁，避免死锁)
     */
    @Column(name = "create_time")
    private long createTime;


    public LockKey getId() {
        return id;
    }

    public void setId(LockKey id) {
        this.id = id;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Locked getLocked() {
        return locked;
    }

    public void setLocked(Locked locked) {
        this.locked = locked;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}