package com.kggs.modbus4jgateway.service.impl;

import com.kggs.modbus4jgateway.bean.Slave;
import com.kggs.modbus4jgateway.service.IModbus4jReadService;
import com.kggs.modbus4jgateway.strategy.Context;
import com.serotonin.modbus4j.ModbusMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 基于modbus4j库实现的Modbus通信
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/18 14:09
 */
@Service
public class Modbus4jServiceImp implements IModbus4jReadService {
    private PullModbusSlaveThread slaveThread = null;
    private ModbusMaster master = null;

    @Autowired
    public Modbus4jServiceImp(ModbusMaster master) {
        this.master = master;
    }

    @Override
    public void Reset() {
        if (this.slaveThread != null) {
            this.slaveThread.interrupt();
        }
        //这里是不是应该调用一次Start方法
    }

    @Override
    public void Start(List<Slave> slaves) {
        this.Start(slaves, 1000);
    }

    @Override
    public void Start(List<Slave> slaves, long millis) {
        this.slaveThread = new PullModbusSlaveThread(slaves, millis);
        this.slaveThread.run();
    }

    /**
     * 拉取ModbusSalve数据的线程
     * <p>
     * 1、循环拉取；
     * 2、消息入队（采集Redis缓存，可能会出现用户需要查询第一次值的情况，那么此时就直接取Redis里面拿最新数据）。推送到前端
     * 3、入库
     * </p>
     */
    public class PullModbusSlaveThread extends Thread {
        private List<Slave> slaves = null;
        private long millis = 0L;

        public PullModbusSlaveThread(List<Slave> slaves, long millis) {
            this.slaves = slaves;
            this.millis = millis;
        }

        @Override
        public void run() {
            while (true) {
                Context.Read(slaves);
                try {
                    Thread.sleep(this.millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
