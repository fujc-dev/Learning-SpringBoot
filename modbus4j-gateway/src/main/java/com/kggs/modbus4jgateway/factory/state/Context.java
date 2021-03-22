package com.kggs.modbus4jgateway.factory.state;

import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.state.impl.WriteCoilState;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 16:30
 */
public class Context {

    public Context() {
        //设置默认的第一个处理
        this.SetState(new WriteCoilState());
    }

    private WriteState state;

    public WriteState GetState() {
        return state;
    }

    public void SetState(WriteState state) {
        this.state = state;
        this.state.SetContext(this);
    }

    public <T> void Write(SlaveWrite<T> writeValue) throws ModbusTransportException, ErrorResponseException {
        this.state.Write(writeValue);
    }
}
