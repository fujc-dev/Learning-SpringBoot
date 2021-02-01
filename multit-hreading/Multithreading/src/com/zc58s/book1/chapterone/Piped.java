package com.zc58s.book1.chapterone;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 管道，这个有点意思，两个线程之间的通信，ThreadA发一个消息，ThreadB能直接收到。
 *
 * <p>
 * 这个应用场景估计有点意思。暂时还不知道用到什么地方，先将例子写在这里。
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/29 14:16
 */
public class Piped {

    public static void main(String[] args) throws Exception {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        // 将输出流和输入流进行连接，否则在使用时会抛出IOException
        out.connect(in);
        Thread printThread = new Thread(new Print(in), "PrintThread");
        printThread.start();
        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        } finally {
            out.close();
        }
    }

    static class Print implements Runnable {
        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException ex) {

            }
        }
    }
}
