package com.zc58s.chapterone;

/**
 * Daemon线程是一直支持型线程，因为它主要被用作程序中后台调度，以及支持性工作。
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/29 12:38
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        // thread.setDaemon(true);  //设置了Daemon属性后，线程就变成后台线程，程序执行完毕后，不会等待该线程执行完毕，线程自动退出。
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            } finally {
                System.out.println("DaemonThread finally run.");  //设置了Daemon属性的线程，这个finally里面的代码不会被执行。
            }
        }
    }


}