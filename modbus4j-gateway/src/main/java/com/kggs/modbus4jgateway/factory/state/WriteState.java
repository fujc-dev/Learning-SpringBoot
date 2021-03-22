package com.kggs.modbus4jgateway.factory.state;

import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.service.IModbus4jWriteService;
import com.kggs.modbus4jgateway.utils.SpringContextUtil;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 16:29
 */
public abstract class WriteState {
    protected IModbus4jWriteService writeService;
    protected Context context = null;
    public WriteState() {
        this.writeService = SpringContextUtil.getBean(IModbus4jWriteService.class);
    }

    public void SetContext(Context context) {
        this.context = context;
    }

    public abstract <T> void Write(SlaveWrite<T> writeValue) throws ModbusTransportException, ErrorResponseException;

    /**
     * 获取参数类型名称
     *
     * @param writeValue
     * @param <T>
     * @return
     */
    protected <T> String GetWriteParamTypeName(SlaveWrite<T> writeValue) {
        return writeValue.getVal().getClass().getSimpleName();
    }
}
