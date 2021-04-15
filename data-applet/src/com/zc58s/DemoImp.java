package com.zc58s;

import java.util.UUID;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/15 16:58
 */
public class DemoImp {

    private MessageCallback callback;


    public void Action(MessageCallback callback) {
        this.callback = callback;
        this.callback.invoke("callback message");
    }

    public void SetMessage() {
        if (this.callback != null) {
            this.callback.invoke(UUID.randomUUID().toString());
        }
    }



}
