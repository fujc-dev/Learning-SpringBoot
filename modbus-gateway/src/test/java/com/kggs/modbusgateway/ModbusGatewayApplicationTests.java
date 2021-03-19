package com.kggs.modbusgateway;

import com.kggs.modbusgateway.bean.Slave;
import com.kggs.modbusgateway.service.IModbusSlaveService;
import com.kggs.modbusgateway.utils.Modbus4jUtils;
import com.kggs.modbusgateway.utils.Modbus4jWriteUtils;
import com.kggs.modbusgateway.utils.SpringContextUtil;
import com.serotonin.modbus4j.code.DataType;
import org.junit.jupiter.api.Test;
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
        slave = new Slave(1, 0, 2, 0x03);
        slaveList.add(slave);
//        slave = new Slave(1, 0, 1, 0x01);
//        slaveList.add(slave);
//        slave = new Slave(1, 0, 1, 0x02);
//        slaveList.add(slave);
//        slave = new Slave(1, 0, 1, 0x04);
//        slaveList.add(slave);
        //salveService.Initialization("127.0.0.1");
        salveService.Start(slaveList, 1000);


    }

    /**
     *  modbus4j
     */
    @Test
    void  modbus4j(){
        try {
            Modbus4jUtils modbus4jUtils = new  Modbus4jUtils();
            Modbus4jWriteUtils modbus4jWriteUtils = new  Modbus4jWriteUtils();

            // 01测试
            //Boolean v011 = modbus4jUtils.readCoilStatus(1, 0);
            //Boolean v012 =modbus4jUtils. readCoilStatus(1, 1);
            //Boolean v013 =modbus4jUtils. readCoilStatus(1, 6);
           //System.out.println("v011:" + v011);
            //System.out.println("v012:" + v012);
           // System.out.println("v013:" + v013);
            // 02测试
            //Boolean v021 = modbus4jUtils.readInputStatus(1, 0);
            //Boolean v022 =modbus4jUtils. readInputStatus(1, 1);
            //Boolean v023 = modbus4jUtils.readInputStatus(1, 2);
            //System.out.println("v021:" + v021);
            //System.out.println("v022:" + v022);
            //System.out.println("v023:" + v023);

            // 03测试
            Number v031 =modbus4jUtils. readHoldingRegister(1, 0, DataType.FOUR_BYTE_FLOAT);// 注意,float
            Number v032 = modbus4jUtils.readHoldingRegister(1, 2,DataType.TWO_BYTE_INT_SIGNED);
            System.out.println("v031:" + v031);
            System.out.println("v032:" + v032);

            modbus4jWriteUtils.writeHoldingRegister(1,2,100,DataType.TWO_BYTE_INT_SIGNED);
            modbus4jWriteUtils.writeCoil(1,0,true);
            // 04测试
            //Number v041 =modbus4jUtils. readInputRegisters(1, 0, DataType.FOUR_BYTE_FLOAT);//
            //Number v042 = modbus4jUtils.readInputRegisters(1, 2, DataType.FOUR_BYTE_FLOAT);//
            //System.out.println("v041:" + v041);
            //System.out.println("v042:" + v042);
            // 批量读取
           // modbus4jUtils.batchRead();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
