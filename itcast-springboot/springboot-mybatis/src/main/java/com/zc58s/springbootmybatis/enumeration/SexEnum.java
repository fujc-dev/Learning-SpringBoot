package com.zc58s.springbootmybatis.enumeration;

/*
 * springboot-database
 * com.zc58s.springbootdatabase.enumeration
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/29 12:46
 */
public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女");

    private int id;
    private String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SexEnum getEnumById(int id) {
        for (SexEnum sex : SexEnum.values()) {
            if (sex.id == id) {
                return sex;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
