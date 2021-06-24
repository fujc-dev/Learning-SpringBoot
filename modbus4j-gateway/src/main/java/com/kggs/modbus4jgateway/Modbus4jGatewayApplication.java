package com.kggs.modbus4jgateway;

import com.kggs.modbus4jgateway.bean.Master;
import com.kggs.modbus4jgateway.bean.Slave;
import com.kggs.modbus4jgateway.bean.SlavePoint;
import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.WriteHelper;
import com.kggs.modbus4jgateway.service.IModbus4JMasterService;
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
        //多网关模式
        //第一步、启动并连接所有的Master
        Master master = new Master("127.0.0.1");
        List<Master> masters = new ArrayList<>();
        masters.add(master);
        IModbus4JMasterService masterService = SpringContextUtil.getBean(IModbus4JMasterService.class);
        masterService.Start(masters);
        //第二步、初始化SlaveId与点位寄存器地址，并与Master绑定
        List<Slave> slaveList = new ArrayList<>();
        Slave slave = null;
        //int _hex = IntegerUtil.ConvertBy16Hex("0x0A");
        slave = new Slave(master, 1, 0x03, new SlavePoint(0, DataType.FOUR_BYTE_FLOAT));
        slaveList.add(slave);
        slave = new Slave(master, 2, 0x03, new SlavePoint(0, DataType.FOUR_BYTE_FLOAT));
        slaveList.add(slave);
        //slave = new Slave(50, 0x02, new SlavePoint(40002));
        //slaveList.add(slave);
        //第三步、开启读线程，读取点位数据
        IModbus4jReadService salveService = SpringContextUtil.getBean(IModbus4jReadService.class);
        salveService.Start(slaveList);
        //WriteHelper.Write(new SlaveWrite<>(master, 50, 2, false));
        //Short _short = 1;
        //WriteHelper.Write(new SlaveWrite<Short>(1, 1, _short));
    }


}
