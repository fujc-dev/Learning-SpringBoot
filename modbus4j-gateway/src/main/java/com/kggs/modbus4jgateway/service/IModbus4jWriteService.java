package com.kggs.modbus4jgateway.service;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

/**
 * 基于Modbus4j实现的写Modbus Slave服务
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 14:02
 */
public interface IModbus4jWriteService {

    /**
     * 写 [01 Coil Status(0x)]写一个 function ID = 5
     *
     * @param master
     * @param slaveId     slave的ID
     * @param writeOffset 位置
     * @param writeValue  值
     * @return 是否写入成功
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    Boolean WriteCoil(ModbusMaster master, int slaveId, int writeOffset, boolean writeValue) throws ModbusTransportException;

    /**
     * 写[01 Coil Status(0x)] 写多个 function ID = 15
     *
     * @param master
     * @param slaveId     slaveId
     * @param startOffset 开始位置
     * @param bdata       写入的数据
     * @return 是否写入成功
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    Boolean WriteCoils(ModbusMaster master, int slaveId, int startOffset, boolean[] bdata) throws ModbusTransportException;

    /***
     * 写[03 Holding Register(4x)] 写一个 function ID = 6
     * @param master
     * @param slaveId
     * @param writeOffset
     * @param writeValue
     * @return
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    Boolean WriteRegister(ModbusMaster master, int slaveId, int writeOffset, short writeValue) throws ModbusTransportException;

    /**
     * 写入[03 Holding Register(4x)]写多个 function ID=16
     *
     * @param master
     * @param slaveId     modbus的slaveID
     * @param startOffset 起始位置偏移量值
     * @param sdata       写入的数据
     * @return 返回是否写入成功
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    Boolean WriteRegisters(ModbusMaster master, int slaveId, int startOffset, short[] sdata) throws ModbusTransportException;

    /**
     * 写入数字类型的模拟量（如:写入Float类型的模拟量、Double类型模拟量、整数类型Short、Integer、Long）
     *
     * @param master
     * @param slaveId
     * @param offset
     * @param value    写入值,Number的子类,例如写入Float浮点类型,Double双精度类型,以及整型short,int,long
     * @param dataType ,com.serotonin.modbus4j.code.DataType
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    void WriteHoldingRegister(ModbusMaster master, int slaveId, int offset, Number value, int dataType) throws ErrorResponseException, ModbusTransportException;
}
