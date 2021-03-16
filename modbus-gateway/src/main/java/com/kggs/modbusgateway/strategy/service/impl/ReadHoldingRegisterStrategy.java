package com.kggs.modbusgateway.strategy.service.impl;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.kggs.modbusgateway.bean.Slave;
import com.kggs.modbusgateway.strategy.service.Strategy;
import org.springframework.stereotype.Service;

/**
 * 读保持寄存器
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 10:01
 */
@Service("0x03")
public class ReadHoldingRegisterStrategy extends StrategyBase {


    public ReadHoldingRegisterStrategy(ModbusMaster master) {
        super(master);
    }

    @Override
    public void Read(int slaveId, int offset, int quantity) throws ModbusNumberException, ModbusProtocolException, ModbusIOException {
        if (this.master != null) {
            if (!this.master.isConnected()) {
                this.master.connect();
            }
            int[] registerValues = this.master.readHoldingRegisters(slaveId, offset, quantity);
            for (int var : registerValues) {
                System.out.println("Address: " + offset + ", Value: " + var);
            }
        }
    }
}
