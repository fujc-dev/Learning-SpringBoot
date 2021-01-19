package com.zc58s.springbootdatabaselock.enums;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/19 16:01
 */
public enum Locked {
    LOCK("0"),
    UNLOCK("1");

    private String id;

    Locked(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Locked getEnumById(String id) {
        //这里应该有一个诊断，如果id为null或者空，需要抛出异常
        for (Locked sex : Locked.values()) {
            if (sex.id.equals(id)) {
                return sex;
            }
        }
        return null;
    }
}
