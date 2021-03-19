package com.kggs.modbusgateway.service;


import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.kggs.modbusgateway.bean.Slave;
import com.kggs.modbusgateway.exception.ModbusConnectException;
import com.kggs.modbusgateway.exception.ModbusWriteException;

import java.util.List;

/**
 * 监控服务对象，用于维护设备Tcp连接
 *
 * <p>
 * 1、连接Modbus Salve；
 * 2、实时轮询最新的点位数据；
 * 3、消息入库（持久化）；
 * 4、消息转发，基于WebSocket消息协议；
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/10 15:01
 */
public interface IModbusSlaveService {

    /**
     * 初始化，调用该方法重置循环获取
     */
    void Reset();

    /**
     * 我们通过传入批量的从机地址
     *
     * @param slaves
     */
    void Start(List<Slave> slaves);

    /**
     * @param slaves
     * @param millis
     */
    void Start(List<Slave> slaves, long millis);

    /**
     * @param slave
     * @param flag
     * @throws ModbusIOException
     * @throws ModbusWriteException
     * @throws ModbusNumberException
     * @throws ModbusProtocolException
     */
    void Write(Slave slave, boolean flag) throws ModbusWriteException, ModbusConnectException;

    /**
     * @param slave
     * @param register
     * @throws ModbusIOException
     * @throws ModbusNumberException
     * @throws ModbusProtocolException
     * @throws ModbusWriteException
     */
    void Write(Slave slave, int register) throws ModbusWriteException, ModbusConnectException;
}
