package com.kggs.modbus4jgateway.strategy.service.impl;


import com.kggs.modbus4jgateway.bean.SlavePoint;
import com.serotonin.modbus4j.ModbusMaster;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 读输入寄存器
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 10:11
 */
@Service("0x04")
public class ReadInputRegisterStrategy extends StrategyBase {
    @Override
    public void Read(int slaveId, List<SlavePoint> points)  {
        if (points != null) {
            for (SlavePoint point : points) {
                System.out.println("Offset：" + point.getOffset() + " Value：" + this.readInputRegisters(slaveId, point.getOffset(),point.getDataType()));
            }
        }
    }
}
