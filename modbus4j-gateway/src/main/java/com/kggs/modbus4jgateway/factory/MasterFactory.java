package com.kggs.modbus4jgateway.factory;

import com.kggs.modbus4jgateway.bean.Master;
import com.serotonin.modbus4j.ModbusMaster;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/6/24 10:34
 */
public class MasterFactory {
    //维护当前系统中，所包含的所有的ModbusMaster对象
    private static Map<Master, ModbusMaster> masterMap = new HashMap<>();
    private static MasterFactory instance;

    private MasterFactory() {

    }

    public static synchronized MasterFactory getInstance() {
        if (instance == null) {
            instance = new MasterFactory();
        }
        return instance;
    }

    /**
     * @param master
     * @param modbusMaster
     */
    public void SetModbusMaster(Master master, ModbusMaster modbusMaster) {
        if (!masterMap.containsKey(master)) {
            masterMap.put(master, modbusMaster);
        }
    }

    /**
     * @param master
     * @return
     */
    public ModbusMaster GetModbusMaster(Master master) {
        return masterMap.get(master);
    }
}
