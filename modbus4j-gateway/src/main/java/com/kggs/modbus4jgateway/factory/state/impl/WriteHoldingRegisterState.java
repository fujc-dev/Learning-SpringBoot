package com.kggs.modbus4jgateway.factory.state.impl;

import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.MasterFactory;
import com.kggs.modbus4jgateway.factory.state.Context;
import com.kggs.modbus4jgateway.factory.state.WriteState;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

/**
 * 写入数字类型的模拟量（如:写入Float类型的模拟量、Double类型模拟量、整数类型Short、Integer、Long）
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 17:09
 */
public class WriteHoldingRegisterState extends WriteState {

    @Override
    public <T> void Write(SlaveWrite<T> writeValue) throws ModbusTransportException, ErrorResponseException {

        if (writeValue.getDataType() > -1) {
            //写入数字类型的模拟量
            //Short、Int、Long、Double等父类都是Number类型
            Number number = (Number) writeValue.getVal();
            ModbusMaster master = MasterFactory.getInstance().GetModbusMaster(writeValue.getMaster());
            this.writeService.WriteHoldingRegister(master,writeValue.getSlaveId(), writeValue.getOffset(), number, writeValue.getDataType());
        } else {
            //该数据未包含处理状态，无法被写入
            System.out.println("该数据未包含处理状态，无法被写入");
        }
    }
}
