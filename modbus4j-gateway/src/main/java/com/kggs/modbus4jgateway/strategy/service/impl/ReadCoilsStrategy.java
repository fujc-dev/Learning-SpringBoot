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


    @Override
    public void Read(ModbusMaster master, int slaveId, List<SlavePoint> points) {
        if (points != null) {
            //一个设备的所有点位
            //存储的时候，按照offset绑定的点位，依次存储到数据库

            boolean[] vals = ReadSlaveByAllPoints(master,slaveId, points);
            for (boolean val : vals) {
                System.out.println("Value：" + val);
            }
            for (SlavePoint point : points) {
                Boolean val = this.readCoilStatus(master,slaveId, point.getOffset());
                System.out.println("Offset：" + point.getOffset() + "Value：" + val);
            }
        }
    }

    /**
     * 一次性读取设备上所有点位的开关量
     *
     * @param slaveId
     * @param points
     * @return
     */
    private boolean[] ReadSlaveByAllPoints(ModbusMaster master,int slaveId, List<SlavePoint> points) {
        boolean[] vals = new boolean[points.size()];
        for (int i = 0; i < points.size(); i++) {
            vals[i] = this.readCoilStatus(master,slaveId, points.get(i).getOffset());
        }
        return vals;
    }
}
