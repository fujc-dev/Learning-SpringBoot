package com.kggs.modbus4jgateway;

import com.kggs.modbus4jgateway.bean.Slave;
import com.kggs.modbus4jgateway.bean.SlavePoint;
import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.WriteHelper;
import com.kggs.modbus4jgateway.service.IModbus4jReadService;
import com.kggs.modbus4jgateway.utils.IntegerUtil;
import com.kggs.modbus4jgateway.utils.SpringContextUtil;
import com.serotonin.modbus4j.code.DataType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Modbus4jGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Modbus4jGatewayApplication.class, args);
        IModbus4jReadService salveService = SpringContextUtil.getBean(IModbus4jReadService.class);
        List<Slave> slaveList = new ArrayList<>();
        Slave slave = null;
        int _hex = IntegerUtil.ConvertBy16Hex("0x0A");
        slave = new Slave(103, 0x03, new SlavePoint(_hex, DataType.FOUR_BYTE_FLOAT));
        slaveList.add(slave);
        //slave = new Slave(1, 0x01, new SlavePoint(6));
        //slaveList.add(slave);
        salveService.Start(slaveList);
        //WriteHelper.Write(new SlaveWrite<>(1,33 , false));
        //Short _short = 1;
        //WriteHelper.Write(new SlaveWrite<Short>(1, 1, _short));
    }


}
