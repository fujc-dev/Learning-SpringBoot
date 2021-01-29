package com.zc58s.chapterone;

import java.util.concurrent.TimeUnit;

/**
 * 对ThreadLocal的运用，一个线程都包含一个ThreadLocal对象
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/29 14:39
 */
public class Profiler {

    // 第一次get()方法调用时会进行初始化（如果set方法没有调用），每个线程会调用一次
    private static final ThreadLocal<Long> TIME_ThreadLocal = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_ThreadLocal.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_ThreadLocal.get();
    }

    public static void main(String[] args) throws Exception {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end() + " mills");
    }
}
