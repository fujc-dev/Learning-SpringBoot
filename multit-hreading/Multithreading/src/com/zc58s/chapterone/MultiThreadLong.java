package com.zc58s.chapterone;

/**
 * 按照书上说的，在32位的程序会出现值不一致的情况，但是我在测试过程中好像没有发现，未深入了解
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/1 11:46
 */
public class MultiThreadLong {
    public static long t = 0;

    public static class ChangeT implements Runnable {
        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true) {
                MultiThreadLong.t = to;
                Thread.yield();
            }

        }
    }

    public static class ReadT implements Runnable {


        @Override
        public void run() {
            long tmp = MultiThreadLong.t;
            if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L) {
                System.out.println(tmp);
            }
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();
    }
}
