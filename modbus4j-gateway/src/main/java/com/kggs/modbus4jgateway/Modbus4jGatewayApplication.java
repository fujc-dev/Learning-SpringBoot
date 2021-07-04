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
        IModbus4JMasterService masterService = SpringContextUtil.getBean(IModbus4JMasterService.class);
        Master master = new Master("192.168.0.91");
        List<Master> masters = new ArrayList<>();
        masters.add(master);
        masterService.Start(masters);
        //第二步、初始化SlaveId与点位寄存器地址，并与Master绑定
        List<Slave> slaveList = new ArrayList<>();
        Slave slave = null;
        //int _hex = IntegerUtil.ConvertBy16Hex("0x0A");
        //多联机
        slave = new Slave(master, 50, 0x03, new SlavePoint(40645, DataType.TWO_BYTE_INT_UNSIGNED));
        slaveList.add(slave);
        slave = new Slave(master, 50, 0x03, new SlavePoint(40640, DataType.TWO_BYTE_INT_UNSIGNED));
        slaveList.add(slave);
        slave = new Slave(master, 50, 0x03, new SlavePoint(40641, DataType.TWO_BYTE_INT_UNSIGNED));
        slaveList.add(slave);
        slave = new Slave(master, 50, 0x03, new SlavePoint(40639, DataType.TWO_BYTE_INT_UNSIGNED));
        slaveList.add(slave);
        slave = new Slave(master, 50, 0x03, new SlavePoint(40659, DataType.TWO_BYTE_INT_UNSIGNED));
        slaveList.add(slave);
        //第三步、开启读线程，读取点位数据
         IModbus4jReadService salveService = SpringContextUtil.getBean(IModbus4jReadService.class);
        salveService.Start(slaveList);
        //WriteHelper.Write(new SlaveWrite<>(master, 50, 43081, true));
        //Integer _short =1;
        //WriteHelper.Write(new SlaveWrite<Integer>(master, 50, 43081, _short, DataType.TWO_BYTE_INT_SIGNED));
    }


}
