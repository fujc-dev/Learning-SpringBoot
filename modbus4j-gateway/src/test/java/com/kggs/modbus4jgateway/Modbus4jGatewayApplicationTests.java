package com.kggs.modbus4jgateway;

import com.kggs.modbus4jgateway.bean.Slave;
import com.kggs.modbus4jgateway.bean.SlavePoint;
import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.WriteHelper;
import com.kggs.modbus4jgateway.service.IModbus4jReadService;
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
        //IModbus4jReadService salveService = SpringContextUtil.getBean(IModbus4jReadService.class);
        //List<Slave> slaveList = new ArrayList<>();
        //SlavePoint point = new SlavePoint(0, DataType.FOUR_BYTE_FLOAT);
        //Slave slave = new Slave(1, 0x03, point);
        //slaveList.add(slave);
        //salveService.Start(slaveList);
        WriteHelper.Write(new SlaveWrite<>(1, 0, new boolean[]{true, true}));
        Short _short = 1000;
        WriteHelper.Write(new SlaveWrite<Short>(1, 2, _short, DataType.FOUR_BYTE_FLOAT));
    }

}
