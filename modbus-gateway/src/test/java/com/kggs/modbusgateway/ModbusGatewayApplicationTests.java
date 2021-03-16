package com.kggs.modbusgateway;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.kggs.modbusgateway.bean.Slave;
import com.kggs.modbusgateway.exception.ModbusConnectException;
import com.kggs.modbusgateway.exception.ModbusWriteException;
import com.kggs.modbusgateway.service.IModbusSlaveService;
import com.kggs.modbusgateway.service.impl.ModbusSlaveServiceImp;
import com.kggs.modbusgateway.utils.SpringContextUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ModbusGatewayApplicationTests {

    @Test
    void contextLoads() {
        IModbusSlaveService salveService = SpringContextUtil.getBean(IModbusSlaveService.class);
        //int serverAddress, int startAddress, int quantity)
        //int serverAddress, int startAddress, boolean flag
        // ModbusMaster master = SpringContextUtil.getBean(ModbusMaster.class);
        Slave slave = null;//new Slave(2, 0, 1, 0x01);
        //salveService.Write(slave, true);
        //slave = new Slave(1, 0, 1, 0x03);
        //salveService.Write(slave, 0);


        List<Slave> slaveList = new ArrayList<>();
        slave = new Slave(1, 0, 1, 0x03);
        slaveList.add(slave);
        //slave = new Slave(2, 0, 1, 0x01);
        //slaveList.add(slave);
        //slave = new Slave(3, 0, 1, 0x02);
        //slaveList.add(slave);
        //slave = new Slave(4, 0, 1, 0x04);
        //slaveList.add(slave);
        //salveService.Initialization("127.0.0.1");
        salveService.Start(slaveList, 1000);
    }

}
