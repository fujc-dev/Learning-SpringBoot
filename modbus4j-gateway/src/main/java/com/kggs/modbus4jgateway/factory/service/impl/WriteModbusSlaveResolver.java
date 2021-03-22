package com.kggs.modbus4jgateway.factory.service.impl;

import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.service.WriteSalveFactory;
import com.kggs.modbus4jgateway.factory.state.Context;
import com.kggs.modbus4jgateway.service.IModbus4jWriteService;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public <T> void Write(SlaveWrite<T> writeValue) throws ErrorResponseException, ModbusTransportException {
        Context context  = new Context();
        context.Write(writeValue);
    }
}
