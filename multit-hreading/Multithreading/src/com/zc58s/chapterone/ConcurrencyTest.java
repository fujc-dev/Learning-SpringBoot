package com.zc58s.chapterone;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/28 14:11
 */
public class ConcurrencyTest {
    private static final long count = 1000000001;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();

        /**
         *  总结：随着count数据量增加，多线程优势iju越明显。
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
