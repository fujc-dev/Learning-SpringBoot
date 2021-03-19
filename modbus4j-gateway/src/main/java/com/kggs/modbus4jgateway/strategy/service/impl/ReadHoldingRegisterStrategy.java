package com.kggs.modbus4jgateway.strategy.service.impl;

import com.kggs.modbus4jgateway.bean.SlavePoint;
import com.serotonin.modbus4j.ModbusMaster;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void Read(int slaveId, List<SlavePoint> points) {
        if (points != null) {
            for (SlavePoint point : points) {
                Number value = this.readHoldingRegister(slaveId, point.getOffset(), point.getDataType());
                System.out.println("Offset：" + point.getOffset() + " Value：" + value);
            }
        }
    }
}
