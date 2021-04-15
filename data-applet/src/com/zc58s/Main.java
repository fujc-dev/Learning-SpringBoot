package com.zc58s;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        DemoImp demoImp = new DemoImp();
        demoImp.Action(new MessageCallbackImp());
        while (true) {
            demoImp.SetMessage();
            Thread.sleep(1000);
        }
    }

    public static class MessageCallbackImp implements MessageCallback {

        @Override
        public void invoke(String message) {
            System.out.println(message);
        }
    }
}
