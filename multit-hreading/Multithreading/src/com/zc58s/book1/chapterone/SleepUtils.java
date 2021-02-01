package com.zc58s.book1.chapterone;

import java.util.concurrent.TimeUnit;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/29 12:56
 */
public class SleepUtils {
    public static void second(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
