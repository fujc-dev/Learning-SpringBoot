package com.kggs.modbusgateway.service.impl;

import com.kggs.modbusgateway.bean.Slave;
import com.kggs.modbusgateway.exception.ModbusConnectException;
import com.kggs.modbusgateway.exception.ModbusWriteException;
import com.kggs.modbusgateway.service.IModbusSlaveService;

import java.util.List;

/**
 * 基于modbus4j库实现的Modbus通信
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/18 14:09
 */
public class Modbus4jServiceImp implements IModbusSlaveService {
    @Override
    public void Reset() {

    }

    @Override
    public void Start(List<Slave> slaves) {

    }

    @Override
    public void Start(List<Slave> slaves, long millis) {

    }

    @Override
    public void Write(Slave slave, boolean flag) throws ModbusWriteException, ModbusConnectException {

    }

    @Override
    public void Write(Slave slave, int register) throws ModbusWriteException, ModbusConnectException {

    }
}
