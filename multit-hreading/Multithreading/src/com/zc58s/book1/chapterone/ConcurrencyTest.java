package com.zc58s.book1.chapterone;

/**
 * 看一下多线程程序的优势与劣势。
 * <p>
 * 随机计算的量增加，多线程的优势逐渐显现出来。
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/28 14:11
 */
public class ConcurrencyTest {
    public static long count = 1000000001;
    public static long count1 = 1000000001;
    public static long count2 = 1000000001;
    public static long count3 = 1000000001;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();

        /**
         *  总结：随着count数据量增加，多线程优势就越明显。
         */
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency：" + time + "ms，b= " + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency：" + time + "ms，b= " + b + "，a=" + a);
    }


}
