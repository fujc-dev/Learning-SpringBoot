package com.kggs.c9000sdk.vo;

import com.kggs.c9000sdk.vo.base.Notify;
import com.kggs.c9000sdk.vo.base.NotifyBase;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/4 9:09
 */
public class ProtectionRemovalNotify extends Notify {

    private Status status;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        Success, Fail
    }

    @Override
    public String toString() {
        return "ProtectionRemovalNotify{" +
                "status=" + status +
                "} " + super.toString();
    }
}

