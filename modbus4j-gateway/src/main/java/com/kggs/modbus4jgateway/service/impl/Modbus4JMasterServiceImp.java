package com.kggs.modbus4jgateway.service.impl;

import com.kggs.modbus4jgateway.bean.Master;
import com.kggs.modbus4jgateway.factory.MasterFactory;
import com.kggs.modbus4jgateway.service.IModbus4JMasterService;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.IpParameters;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/6/24 10:30
 */
@Service
public class Modbus4JMasterServiceImp implements IModbus4JMasterService {
    @Override
    public void Start(List<Master> masters) {
        masters.forEach(u -> {
            try {
                ModbusFactory modbusFactory = new ModbusFactory();
                IpParameters params = new IpParameters();
                params.setHost(u.getIp());
                params.setPort(502);
                ModbusMaster master = modbusFactory.createTcpMaster(params, true);// TCP 协议
                master.init();
                MasterFactory.getInstance().SetModbusMaster(u, master);
            } catch (ModbusInitException e) {
                e.printStackTrace();
            }
        });
    }
}
