package com.zc58s.springbootinfdemo.jna.sdk.callback;

import com.sun.jna.Pointer;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/8 14:58
 */
public class Message {

    /**
     * 句柄
     */
    private Integer szHandle;
    /**
     * 请求Action地址
     */
    private String szAction;
    /**
     * 结果
     */
    private String szResult;

    public  Message(){}

    public Message(Integer szHandle, String szAction, String szResult) {
        this.szHandle = szHandle;
        this.szAction = szAction;
        this.szResult = szResult;
    }

    public Integer getSzHandle() {
        return szHandle;
    }

    public void setSzHandle(Integer szHandle) {
        this.szHandle = szHandle;
    }

    public String getSzAction() {
        return szAction;
    }

    public void setSzAction(String szAction) {
        this.szAction = szAction;
    }

    public String getSzResult() {
        return szResult;
    }

    public void setSzResult(String szResult) {
        this.szResult = szResult;
    }

    @Override
    public String toString() {
        return "Message{" +
                "nHandle=" + szHandle +
                ", szAction='" + szAction + '\'' +
                ", szResult='" + szResult + '\'' +
                '}';
    }
}
