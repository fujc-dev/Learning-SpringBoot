package com.kggs.modbus4jgateway.factory.service.impl;

import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.service.WriteSalveFactory;
import com.kggs.modbus4jgateway.service.IModbus4jWriteService;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 14:40
 */
@Service
public class WriteModbusSlaveResolver extends WriteSalveFactory {

    private IModbus4jWriteService writeService = null;

    @Autowired
    public WriteModbusSlaveResolver(IModbus4jWriteService writeService) {
        this.writeService = writeService;
    }

    @Override
    public <T> void Write(SlaveWrite<T> writeValue) {
        //根据T的类型
        try {
            String simpleName = writeValue.getVal().getClass().getSimpleName();
            //写单个
            if ("boolean".equals(simpleName.toLowerCase())) {
                boolean val = (Boolean) writeValue.getVal();
                this.writeService.WriteCoil(writeValue.getSlaveId(), writeValue.getOffset(), val);
            }
            //写多个线圈
            else if ("boolean[]".equals(simpleName.toLowerCase())) {
                boolean[] val = (boolean[]) writeValue.getVal();
                this.writeService.WriteCoils(writeValue.getSlaveId(), writeValue.getOffset(), val);
            } else {
                if (writeValue.getDataType() > -1) {
                    //写入数字类型的模拟量
                    //Short、Int、Long、Double等父类都是Number类型
                    Number number = (Number) writeValue.getVal();
                    this.writeService.WriteHoldingRegister(writeValue.getSlaveId(), writeValue.getOffset(), number, writeValue.getDataType());
                } else {
                    //写单个保持寄存器
                    if ("short".equals(simpleName.toLowerCase())) {
                        short val = (Short) writeValue.getVal();
                        this.writeService.WriteRegister(writeValue.getSlaveId(), writeValue.getOffset(), val);
                    }
                    //写多个保持寄存器
                    if ("short[]".equals(simpleName.toLowerCase())) {
                        short[] val = (short[]) writeValue.getVal();
                        this.writeService.WriteRegisters(writeValue.getSlaveId(), writeValue.getOffset(), val);
                    }
                }
            }


        } catch (ModbusTransportException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        }
    }
}
