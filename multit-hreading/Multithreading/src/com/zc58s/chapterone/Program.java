package com.zc58s.chapterone;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;


/**
 * 查看java程序默认包含的线程，Web程序或者还更多吧
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/28 13:43
 */
public class Program {

    /**
     * 不做任何事情，就只是启动一个程序，程序不包含任何业务逻辑代码，我们的java程序就包含了这么多的线程。
     *
     * @param args
     */
    public static void main(String[] args) {
        // 获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }

        /**
         * [6] Monitor Ctrl-Break
         * [5] Attach Listener
         * [4] Signal Dispatcher 调用对象finalize方法的线程
         * [3] Finalizer 调用对象finalize方法的线程
         * [2] Reference Handler  清除Reference的线程
         * [1] main main线程，用户程序入口
         */
    }
}
