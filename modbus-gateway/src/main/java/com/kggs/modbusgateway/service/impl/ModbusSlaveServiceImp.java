package com.kggs.modbusgateway.service.impl;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import com.kggs.modbusgateway.bean.Slave;
import com.kggs.modbusgateway.exception.ModbusConnectException;
import com.kggs.modbusgateway.exception.ModbusWriteException;
import com.kggs.modbusgateway.service.IModbusSlaveService;
import com.kggs.modbusgateway.strategy.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * 基于jlibmodbus库实现的Modbus通信
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/10 15:25
 */
@Service
public class ModbusSlaveServiceImp implements IModbusSlaveService {

    protected ModbusMaster master = null;


    private PullModbusSlaveThread slaveThread = null;

    @Autowired
    public ModbusSlaveServiceImp(ModbusMaster master) {

        this.master = master;
    }

    @Override
    public void Initialization() {
        if (this.slaveThread != null) {
            this.slaveThread.interrupt();
        }
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

    @Override
    public void Write(Slave slave, boolean flag) throws ModbusWriteException, ModbusConnectException {
        if (this.master != null) {
            if (!this.master.isConnected()) {
                try {
                    this.master.connect();
                } catch (ModbusIOException e) {
                    throw new ModbusConnectException("Modbus Slave 连接错误");
                }
            }
            try {
                this.master.writeSingleCoil(slave.getSlaveId(), slave.getOffset(), flag);
            } catch (ModbusProtocolException e) {
                throw new ModbusWriteException("参数错误，设备与功能码不匹配，不支持写入", e);
            } catch (ModbusNumberException e) {
                throw new ModbusWriteException("寄存器值超出范围", e);
            } catch (ModbusIOException e) {
                throw new ModbusWriteException("Modbus Slave 连接错误", e);
            }
        }
    }

    @Override
    public void Write(Slave slave, int register) throws ModbusWriteException, ModbusConnectException {
        if (this.master != null) {
            if (!this.master.isConnected()) {
                try {
                    this.master.connect();
                } catch (ModbusIOException e) {
                    throw new ModbusConnectException("Modbus Slave 连接失败");
                }
            }
            try {
                this.master.writeSingleRegister(slave.getSlaveId(), slave.getOffset(), register);
            } catch (ModbusProtocolException e) {
                throw new ModbusWriteException("参数错误，设备与功能码不匹配，不支持写入");
            } catch (ModbusNumberException e) {
                throw new ModbusWriteException("寄存器值超出范围", e);
            } catch (ModbusIOException e) {
                throw new ModbusWriteException("Modbus Slave 连接错误");
            }
        }
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
                try {
                    if (this.slaves != null) {
                        for (Slave slave : slaves) {
                            Context.Read(slave);
                        }
                    }
                } catch (ModbusIOException e) {
                    e.printStackTrace();
                } catch (ModbusNumberException e) {
                    e.printStackTrace();
                } catch (ModbusProtocolException e) {
                    e.printStackTrace();
                }
                //
                try {
                    Thread.sleep(this.millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
