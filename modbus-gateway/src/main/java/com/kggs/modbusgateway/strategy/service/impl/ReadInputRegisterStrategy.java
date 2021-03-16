package com.kggs.modbusgateway.strategy.service.impl;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.kggs.modbusgateway.bean.Slave;
import org.springframework.stereotype.Service;

/**
 * 读输入寄存器
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 10:11
 */
@Service("0x04")
public class ReadInputRegisterStrategy extends StrategyBase {
    public ReadInputRegisterStrategy(ModbusMaster master) {
        super(master);
    }

    @Override
    public void Read(int slaveId, int offset, int quantity) throws ModbusNumberException, ModbusProtocolException, ModbusIOException {
        if (this.master != null) {
            if (!this.master.isConnected()) {
                this.master.connect();
            }
            int[] registerValues = this.master.readInputRegisters(slaveId, offset, quantity);
            for (int var : registerValues) {
                System.out.println("Address: " + offset + ", Value: " + var);
            }
        }
    }
}
