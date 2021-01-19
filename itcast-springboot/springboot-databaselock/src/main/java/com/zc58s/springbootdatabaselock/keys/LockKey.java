package com.zc58s.springbootdatabaselock.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * JPA 声明一个联合主键
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/18 11:49
 */
@Embeddable
public class LockKey implements Serializable {


    /**
     * 默认无参构造函数
     */
    public LockKey() {
    }

    /**
     * @param type
     * @param node
     */
    public LockKey(String type, String node) {
        if (type == null || node == null) {
            throw new IllegalArgumentException("type与node都不能为空");
        }
        this.type = type;
        this.node = node;
    }


    /**
     * 类型（如：某个类标识，某个业务标识）
     */
    private String type;
    /**
     * 节点（与type为联合主键，如：某个方法名称，某个业务流程标识）
     */
    private String node;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "LockKey{" +
                "type='" + type + '\'' +
                ", node='" + node + '\'' +
                '}';
    }
}
