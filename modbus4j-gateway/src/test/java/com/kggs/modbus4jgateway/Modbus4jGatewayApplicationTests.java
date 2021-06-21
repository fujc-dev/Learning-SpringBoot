package com.kggs.modbus4jgateway;

import com.kggs.modbus4jgateway.bean.Slave;
import com.kggs.modbus4jgateway.bean.SlavePoint;
import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.WriteHelper;
import com.kggs.modbus4jgateway.service.IModbus4jReadService;
import com.kggs.modbus4jgateway.utils.IntegerUtil;
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
        //int _hex = IntegerUtil.ConvertBy16Hex("0x0A");
        //System.out.println( _hex);
        //System.out.println("12313");
    }

}
