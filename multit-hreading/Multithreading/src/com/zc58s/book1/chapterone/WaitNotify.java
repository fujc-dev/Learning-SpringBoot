package com.zc58s.book1.chapterone;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/29 13:31
 */
public class WaitNotify {

    private static boolean flag = true;
    /**
     * 使用wait、notify、notifyAll需要先对调用对象加锁
     */
    private static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
        //两个线程被启动，为了保证两个线程的先后顺序，我们再两个线程启动之间，增加了1s的延迟
    }

    /**
     * 模拟的等待线程
     */
    static class Wait implements Runnable {
        public void run() {
            // 加锁，拥有lock的Monitor，此时，我们的Wait线程优先拿到了lock的使用权
            synchronized (lock) {
                // 当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wai @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();    // 此时，是挂起当前线程，并且锁被释放，由其他线程去竞争我们的lock锁
                        System.out.println("WaitThread重新获取到了锁。");
                    } catch (InterruptedException e) {

                    }
                }
                // 条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running                @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        public void run() {
            // 加锁，拥有lock的Monitor，这个线程由于是间隔1s执行，所有在启动之后，我们运行到此处时，是等待waitThread释放锁
            //由于，我们在waitThread线程中调用了 lock.wait(); 挂起等待。
            //此时，notifyThread就竞争了到lock锁，因为挂起等待，相当于取消了对锁的竞争。
            synchronized (lock) {
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁，
                // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                //此时，执行lock.notifyAll(); 就是告诉我们的waitThread线程，你的状态变为了阻塞，当我释放锁之后，你就可以去竞争这个lock锁。
                //通知 waitThread，我执行完毕之后，你就可以执行了，此时还没有释放锁，释放锁之后，等待线程才会继续执行。
                //但是，不阻塞当前线程，继续执行。
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}
