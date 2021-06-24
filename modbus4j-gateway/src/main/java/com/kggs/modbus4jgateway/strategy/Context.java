package com.kggs.modbus4jgateway.strategy;


import com.kggs.modbus4jgateway.bean.Slave;
import com.kggs.modbus4jgateway.factory.MasterFactory;
import com.kggs.modbus4jgateway.strategy.service.Strategy;
import com.kggs.modbus4jgateway.utils.SpringContextUtil;
import com.serotonin.modbus4j.ModbusMaster;

import java.util.List;

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
     * <p>
     * ————————————————
     *     0x01: 读线圈寄存器<p>
     *     0x02: 读离散输入寄存器<p>
     *     0x03: 读保持寄存器<p>
     *     0x04: 读输入寄存器<p>
     * <p>
     * 把值拿出来之后，需要检测这个值与缓存中的值是否一致，需要将不一致的值，存储并推到前台
     * TODO  是否有需求1，将变化值推到界面
     * TODO  是否有需求2，变化的数据需要入库
     * ————————————————
     *
     * @param slave
     */
    public static void Read(Slave slave) {
        Strategy strategy = (Strategy) SpringContextUtil.getBean("0x0" + slave.getCode());
        if (strategy != null) {
            ModbusMaster master = MasterFactory.getInstance().GetModbusMaster(slave.getMaster());
            strategy.Read(master, slave.getSlaveId(), slave.getPoints());
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
     */
    public static void Read(List<Slave> slaves) {
        if (slaves != null) {
            for (Slave slave : slaves) {
                Read(slave);
            }
        }
    }
}
