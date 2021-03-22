package com.kggs.modbus4jgateway.strategy.service.impl;

import com.kggs.modbus4jgateway.bean.SlavePoint;
import com.serotonin.modbus4j.ModbusMaster;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void Read(int slaveId, List<SlavePoint> points) {
        if (points != null) {
            for (SlavePoint point : points) {
                Boolean val = this.readCoilStatus(slaveId, point.getOffset());
                System.out.println("Offset：" + point.getOffset() + "Value：" + val);
            }
        }
    }
}