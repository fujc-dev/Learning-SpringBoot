package com.kggs.modbusgateway.callback;

import com.kggs.modbusgateway.bean.Slave;

/**
 * 回调Modbus的数据，用于业务的相关操作
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/10 17:18
 */
public interface ModbusCallback {

    void Recive(Slave slave);
}
