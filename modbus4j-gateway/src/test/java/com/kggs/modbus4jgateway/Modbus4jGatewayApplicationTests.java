package com.kggs.modbus4jgateway;

import com.kggs.modbus4jgateway.bean.Slave;
import com.kggs.modbus4jgateway.bean.SlavePoint;
import com.kggs.modbus4jgateway.service.IModbusSlaveService;
import com.kggs.modbus4jgateway.utils.SpringContextUtil;
import com.serotonin.modbus4j.code.DataType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Modbus4jGatewayApplicationTests {

    @Test
    void contextLoads() {
        IModbusSlaveService salveService = SpringContextUtil.getBean(IModbusSlaveService.class);
        List<Slave> slaveList = new ArrayList<>();
        SlavePoint point = new SlavePoint(0, DataType.FOUR_BYTE_FLOAT);
        Slave slave = new Slave(1, 0x03, point);
        slaveList.add(slave);
        salveService.Start(slaveList);
    }

}
