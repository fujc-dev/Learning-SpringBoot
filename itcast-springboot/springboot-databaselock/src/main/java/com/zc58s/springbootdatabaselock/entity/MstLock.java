package com.zc58s.springbootdatabaselock.entity;

import com.zc58s.springbootdatabaselock.keys.LockKey;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
    private static final String DATE_FORMAT = "yyyyMMddHHmmss";

    /**
     * 默认无参构造函数
     */
    public MstLock() {
    }

    /**
     * @param eLock
     */
    public MstLock(LockKey eLock) {
        this(eLock.getType(), eLock.getNode());
    }

    /**
     * @param type
     * @param node
     */
    public MstLock(String type, String node) {
        this.id = new LockKey(type, node);
        this.locked = "0";
        this.createTime = new SimpleDateFormat(DATE_FORMAT).format(new Date());
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
    @Column(name = "locked")
    private String locked;
    /**
     * 创建时间(用于释放锁，避免死锁)
     */
    @Column(name = "create_time")
    private String createTime;


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

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}