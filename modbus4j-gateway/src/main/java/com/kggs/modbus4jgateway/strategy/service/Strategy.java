package com.kggs.modbus4jgateway.strategy.service;

import com.kggs.modbus4jgateway.bean.SlavePoint;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;

import java.util.List;

/**
 * 策略接口，用于根据不通的功能码，读取设备数据
 *
 * <p>0x01: 读线圈寄存器</p>
 * <p>0x02: 读离散输入寄存器</p>
 * <p>0x03: 读保持寄存器</p>
 * <p>0x04: 读输入寄存器</p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 9:56
 */
public interface Strategy {

    /**
     * 读取设备数据，以及将该数据进行对应的处理，入库、写如MQ等等；
     * <p>这些参数如果配置错了，就无法访问到数据</p>
     *
     * @param slaveId Slave 设备的固定Id
     * @param points  访问的寄存器的起始地址，从第几个口子开始取值，以及数据类型
     */
    void Read(ModbusMaster master, int slaveId, List<SlavePoint> points);
}
