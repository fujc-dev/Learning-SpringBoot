package com.kggs.c9000sdk.vo.base;

/**
 * 报警
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 10:00
 */
public class VoBase {
    /**
     * 消息类型
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "VoBase{" +
                "message='" + message + '\'' +
                '}';
    }
}
