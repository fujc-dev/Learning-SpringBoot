package com.kggs.modbus4jgateway.service.impl;

import com.kggs.modbus4jgateway.service.IModbus4jWriteService;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.*;
import org.springframework.stereotype.Service;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 14:12
 */
@Service
public class Modbus4jWriteServiceImp implements IModbus4jWriteService {

    private ModbusMaster master;

    public Modbus4jWriteServiceImp(ModbusMaster master) {
        this.master = master;
    }

    @Override
    public Boolean WriteCoil(int slaveId, int writeOffset, boolean writeValue) throws ModbusTransportException {
        // 创建请求
        WriteCoilRequest request = null;
        request = new WriteCoilRequest(slaveId, writeOffset, writeValue);
        // 发送请求并获取响应对象
        WriteCoilResponse response = (WriteCoilResponse) this.master.send(request);
        if (response.isException()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean WriteCoils(int slaveId, int startOffset, boolean[] bdata) throws ModbusTransportException {
        // 创建请求
        WriteCoilsRequest request = new WriteCoilsRequest(slaveId, startOffset, bdata);
        // 发送请求并获取响应对象
        WriteCoilsResponse response = (WriteCoilsResponse) this.master.send(request);
        if (response.isException()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean WriteRegister(int slaveId, int writeOffset, short writeValue) throws ModbusTransportException {
        // 创建请求对象
        WriteRegisterRequest request = new WriteRegisterRequest(slaveId, writeOffset, writeValue);
        WriteRegisterResponse response = (WriteRegisterResponse) this.master.send(request);
        if (response.isException()) {
            // log.error(response.getExceptionMessage());
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean WriteRegisters(int slaveId, int startOffset, short[] sdata) throws ModbusTransportException {
        // 创建请求对象
        WriteRegistersRequest request = new WriteRegistersRequest(slaveId, startOffset, sdata);
        // 发送请求并获取响应对象
        ModbusResponse response = this.master.send(request);
        if (response.isException()) {
            //log.error(response.getExceptionMessage());
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void WriteHoldingRegister(int slaveId, int offset, Number value, int dataType) throws ErrorResponseException, ModbusTransportException {
        // 类型
        BaseLocator<Number> locator = BaseLocator.holdingRegister(slaveId, offset, dataType);
        this.master.setValue(locator, value);
    }
}
