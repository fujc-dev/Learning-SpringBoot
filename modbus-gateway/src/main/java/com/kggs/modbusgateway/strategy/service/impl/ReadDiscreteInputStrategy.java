package com.kggs.modbusgateway.strategy.service.impl;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.kggs.modbusgateway.bean.Slave;
import org.springframework.stereotype.Service;

/**
 * 读离散输入寄存器
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 10:10
 */
@Service("0x02")
public class ReadDiscreteInputStrategy extends StrategyBase {
    public ReadDiscreteInputStrategy(ModbusMaster master) {
        super(master);
    }

    @Override
    public void Read(int slaveId, int offset, int quantity) throws ModbusNumberException, ModbusProtocolException, ModbusIOException {
        if (this.master != null) {
            if (!this.master.isConnected()) {
                this.master.connect();
            }
            boolean[] registerValues = this.master.readDiscreteInputs(slaveId, offset, quantity);
            //这里还有一个操作，就是去重
            boolean[] newRegisterValues = new boolean[quantity];
            System.arraycopy(registerValues, offset, newRegisterValues, 0, quantity);
            for (boolean var : newRegisterValues) {
                System.out.println("Address: " + offset + ", Value: " + var);
            }
        }
    }
}
