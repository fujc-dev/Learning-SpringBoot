package com.kggs.modbus4jgateway.factory.state.impl;

import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.state.WriteState;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 17:08
 */
public class WriteRegisterState extends WriteState {

    @Override
    public <T> void Write(SlaveWrite<T> writeValue) throws ModbusTransportException, ErrorResponseException {
        //
        if (writeValue.getDataType() > -1) {
            //写入数字类型的模拟量
            //Short、Int、Long、Double等父类都是Number类型
            Number number = (Number) writeValue.getVal();
            this.writeService.WriteHoldingRegister(writeValue.getSlaveId(), writeValue.getOffset(), number, writeValue.getDataType());
        } else {
            //该数据未包含处理状态，无法被写入
        }
    }
}
