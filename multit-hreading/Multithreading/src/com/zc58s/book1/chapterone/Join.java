package com.zc58s.book1.chapterone;

/**
 * join，字面意思就是等待，等待其他线程执行完毕，再回来执行当前线程。
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/29 14:22
 */
public class Join {


    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            //创建了一个线程。在线程内调用线程的join方法，阻塞当前线程，等待其他线程执行完毕
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            //thread.setDaemon(true);
            thread.start();
            previous = thread;
        }
        System.out.println("Main thread is finished");
    }


    static class Domino implements Runnable {

        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " terminate .");
            }
        }
    }
}
