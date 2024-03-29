package com.kggs.modbusgateway.strategy;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.kggs.modbusgateway.bean.Slave;
import com.kggs.modbusgateway.strategy.service.Strategy;
import com.kggs.modbusgateway.strategy.service.impl.ReadCoilsStrategy;
import com.kggs.modbusgateway.strategy.service.impl.ReadDiscreteInputStrategy;
import com.kggs.modbusgateway.strategy.service.impl.ReadHoldingRegisterStrategy;
import com.kggs.modbusgateway.strategy.service.impl.ReadInputRegisterStrategy;
import com.kggs.modbusgateway.utils.SpringContextUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置读取Modbus Slave环境对象
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 9:56
 */
public class Context {

    private Context() {
    }

    /**
     * 在Modbus Slave中可以看到Function，该ComboBox有4个选项值，我们称之为功能代码
     * 那么这里的读方法需要与功能代码一一对应
     * 读取对应（从机）的数据，readInputRegisters读取的写寄存器，功能码04
     * <p>
     * ————————————————
     *     0x01: 读线圈寄存器<p>
     *     0x02: 读离散输入寄存器<p>
     *     0x03: 读保持寄存器<p>
     *     0x04: 读输入寄存器<p>
     * <p>
     * 把值拿出来之后，需要检测这个值与缓存中的值是否一致，需要将不一致的值，推到前台
     * TODO  是否有需求1，将变化值推到界面
     * TODO  是否有需求2，变化的数据需要入库
     * <p>
     *     0x05: 写单个线圈寄存器 <p>
     *     0x06: 写单个保持寄存器<p>
     *     0x0f:  写多个线圈寄存器<p>
     *     0x10: 写多个保持寄存器<p>
     * ————————————————
     *
     * @param slave
     * @throws ModbusNumberException
     * @throws ModbusProtocolException
     * @throws ModbusIOException
     */
    public static void Read(Slave slave) throws ModbusNumberException, ModbusProtocolException, ModbusIOException {
        Strategy strategy = (Strategy) SpringContextUtil.getBean("0x0" + slave.getCode());
        if (strategy != null) {
            strategy.Read(slave.getSlaveId(), slave.getOffset(), slave.getQuantity());
        }
    }

    /**
     * 批量读取
     *     0x01: 读线圈寄存器
     *     0x02: 读离散输入寄存器
     *     0x03: 读保持寄存器
     *     0x04: 读输入寄存器
     *
     * @param slaves
     * @throws ModbusNumberException
     * @throws ModbusProtocolException
     * @throws ModbusIOException
     */
    public static void Read(List<Slave> slaves) throws ModbusNumberException, ModbusProtocolException, ModbusIOException {
        if (slaves != null) {
            for (Slave slave : slaves) {
                Read(slave);
            }
        }
    }
}
