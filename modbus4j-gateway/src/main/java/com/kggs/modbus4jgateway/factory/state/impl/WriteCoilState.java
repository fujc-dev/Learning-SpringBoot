package com.kggs.modbus4jgateway.factory.state.impl;

import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.MasterFactory;
import com.kggs.modbus4jgateway.factory.state.WriteState;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

/**
 * 写开关量
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 17:01
 */
public class WriteCoilState extends WriteState {

    @Override
    public <T> void Write(SlaveWrite<T> writeValue) throws ModbusTransportException, ErrorResponseException {
        //检测参数类型，如果是Boolean类型，
        String simpleName = super.GetWriteParamTypeName(writeValue);
        if ("boolean".equals(simpleName.toLowerCase())) {
            boolean val = (Boolean) writeValue.getVal();
            ModbusMaster master = MasterFactory.getInstance().GetModbusMaster(writeValue.getMaster());
            boolean status = this.writeService.WriteCoil(master, writeValue.getSlaveId(), writeValue.getOffset(), val);
            System.out.println(status);
        }
        //写多个线圈
        else if ("boolean[]".equals(simpleName.toLowerCase())) {
            boolean[] val = (boolean[]) writeValue.getVal();
            ModbusMaster master = MasterFactory.getInstance().GetModbusMaster(writeValue.getMaster());
            this.writeService.WriteCoils(master, writeValue.getSlaveId(), writeValue.getOffset(), val);
        } else {
            this.context.SetState(new WriteRegisterState());
            this.context.Write(writeValue);
        }
    }
}
