package com.kggs.modbusgateway.strategy.service.impl;

import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.kggs.modbusgateway.strategy.service.Strategy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 10:03
 */

public abstract class StrategyBase implements Strategy {

    protected ModbusMaster master = null;

    @Autowired
    public StrategyBase(ModbusMaster master) {
        this.master = master;
    }

    /**
     * 公共方法，可重载，将返回的数据入库，对于每一种功能码，设备Id返回的数据需要存储的表可能是不一致的。
     */
    protected void Save() {

    }

    /**
     * 公共方法，可重载，将返回的数据推到前台
     */
    protected void Send() {

    }
}
