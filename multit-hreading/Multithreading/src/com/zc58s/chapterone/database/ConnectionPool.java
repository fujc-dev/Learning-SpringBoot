package com.zc58s.chapterone.database;

import java.security.Policy;
import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/29 14:58
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();


    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    /**
     * 释放连接
     *
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                //当有线程锁住pool时，等待，让调用了pool.wait时，将连接池添加进去，并执行  pool.notifyAll()通知
                //然后，当线程释放锁之后，等待获取连接池的线程，就可用继续获取锁，并执行后续下一步
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 取出连接
     *
     * @param mills 时间，在这个时间段内获取连接
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {// 锁住了我们的pool对象，在多线程模式下，我们的连接对象，排队取
            if (mills <= 0) {  //假如，我们传递是时间为小于等于0，那么
                while (pool.isEmpty()) {
                    //假如，某一个线程来取连接的时候，连接池里面空了。那么这个线程，就在这里等待，此时线程的状态是WAITING；
                    //只有，pool调用了notify、notifyAll方法，才会被修改状态为BLOCKED，阻塞，等待别的线程释放锁，然后继续下一步；
                    //下一步，继续循环呗，如果有连接了，跳出循环，返回连接。否则，继续等待，继续等待别的线程调用notify、notifyAll方法；
                    pool.wait();
                }
                return pool.removeFirst();
            } else { //有设置超时间的，就是我们的mills>0
                long future = System.currentTimeMillis() + mills;  //我们将结束时间计算出来
                long remaining = mills; //此时，remaining肯定是有值的
                while (pool.isEmpty() && remaining > 0) {  //remaining有值，且连接池里面没有有连接时，逻辑跟上面的部分差不多
                    //唯一，有区别的是，有一个超时时间
                    //在指定时间内，我们循环的在pool连接池中去取。
                    //
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
