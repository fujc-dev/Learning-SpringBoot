package com.kggs.modbusgateway.strategy.service.impl;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import org.springframework.stereotype.Service;

/**
 * 读线圈寄存器
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 10:09
 */
@Service("0x01")
public class ReadCoilsStrategy extends StrategyBase {
    public ReadCoilsStrategy(ModbusMaster master) {
        super(master);
    }

    @Override
    public void Read(int slaveId, int offset, int quantity) throws ModbusNumberException, ModbusProtocolException, ModbusIOException {
        if (this.master != null) {
            if (!this.master.isConnected()) {
                this.master.connect();
            }
            boolean[] registerValues = this.master.readCoils(slaveId, offset, quantity);
            boolean[] newRegisterValues = new boolean[quantity];
            System.arraycopy(registerValues, offset, newRegisterValues, 0, quantity);
            for (boolean var : newRegisterValues) {
                System.out.println("Address: " + offset + ", Value: " + var);
            }
        }

    }
}
