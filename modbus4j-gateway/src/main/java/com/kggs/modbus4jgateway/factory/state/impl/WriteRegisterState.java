package com.kggs.modbus4jgateway.factory.state.impl;

import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.state.WriteState;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

/**
 * 写指定类型的寄存器 Short
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 17:08
 */
public class WriteRegisterState extends WriteState {

    @Override
    public <T> void Write(SlaveWrite<T> writeValue) throws ModbusTransportException, ErrorResponseException {
        //
        String simpleName = super.GetWriteParamTypeName(writeValue);
        if ("short".equals(simpleName.toLowerCase())) {
            short val = (Short) writeValue.getVal();
            this.writeService.WriteRegister(writeValue.getSlaveId(), writeValue.getOffset(), val);
        }
        //写多个保持寄存器
        else if ("short[]".equals(simpleName.toLowerCase())) {
            short[] val = (short[]) writeValue.getVal();
            this.writeService.WriteRegisters(writeValue.getSlaveId(), writeValue.getOffset(), val);
        } else {
            this.context.SetState(new WriteHoldingRegisterState());
            this.context.Write(writeValue);
        }
    }
}
