package com.zc58s.book1.chapterone.database;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/29 15:11
 */
public class ConnectionPoolTest {

    //==================================================
    //初期的准备工作，线程池初始出来
    static ConnectionPool pool = new ConnectionPool(10);
    //计数器
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;
    //==================================================

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        int count = 20;
        //AtomicInteger，基于CAS的算法的原子操作，自旋锁
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        //开启10个线程去获取连接
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown(); //将count值减1。这是控制start.await()运行的，直到count减到为0时，start.await()后面的代码才会执行。
        System.out.println(threadCount);  //此时是0，
        end.await();  //此时也是同理，等待end里面的count为0时，后面的代码才会被执行。
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection " + notGot);
    }

    static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        public void run() {
            try {
                start.await();  //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
                //我们声明的时候，将start的count设置为1，然后，在main方法中，调用了一个countDown，那此时
                //我们的start的count就是为0，那么会执行下一步
                System.out.println("1111");
            } catch (Exception ex) {

            }
            while (count > 0) {
                try {
                    // 从线程池中获取连接，如果1000ms内无法获取到，将会返回null
                    // 分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception ex) {
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
