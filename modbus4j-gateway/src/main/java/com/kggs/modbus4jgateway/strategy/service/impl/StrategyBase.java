package com.kggs.modbus4jgateway.strategy.service.impl;

import com.kggs.modbus4jgateway.strategy.service.Strategy;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 10:03
 */

public abstract class StrategyBase implements Strategy {


    /**
     * 读取[01 Coil Status 0x]类型 开关数据
     *
     * @param slaveId slaveId
     * @param offset  位置
     * @return 读取值
     * @throws ModbusTransportException 异常
     * @throws ErrorResponseException   异常
     * @throws ModbusInitException      异常
     */
    protected Boolean readCoilStatus(ModbusMaster master, int slaveId, int offset) {
        try {
            if (master != null) {
                // 01 Coil Status
                BaseLocator<Boolean> loc = BaseLocator.coilStatus(slaveId, offset);
                Boolean value = master.getValue(loc);
                return value;
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取[02 Input Status 1x]类型 开关数据
     *
     * @param slaveId
     * @param offset
     * @return
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public Boolean readInputStatus(ModbusMaster master,int slaveId, int offset) {
        try {
            if (master != null) {
                // 02 Input Status
                BaseLocator<Boolean> loc = BaseLocator.inputStatus(slaveId, offset);
                Boolean value = master.getValue(loc);
                return value;
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取[03 Holding Register类型 2x]模拟量数据
     *
     * @param slaveId  slave Id
     * @param offset   位置
     * @param dataType 数据类型,来自com.serotonin.modbus4j.code.DataType
     * @return
     */
    public Number readHoldingRegister(ModbusMaster master,int slaveId, int offset, int dataType) {
        try {
            if (master != null) {
                // 03 Holding Register类型数据读取
                BaseLocator<Number> loc = BaseLocator.holdingRegister(slaveId, offset, dataType);
                Number value = master.getValue(loc);
                return value;
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 读取[03 Holding Register类型 2x]模拟量数据
     *
     * <p>
     * 针对long类型读取
     * </p>
     *
     * @param slaveId
     * @param start
     * @param len
     * @return
     */
    private Number readHoldingRegisters(ModbusMaster master,int slaveId, int start, int len) {
        try {
            ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, start, len);
            ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master.send(request);
            if (response.isException()) {
                System.out.println("异常消息:" + response.getExceptionMessage());
            } else {
                System.out.println(Arrays.toString(response.getShortData()));
                short[] list = response.getShortData();
                for (int i = 0; i < list.length; i++) {
                    System.out.print(list[i] + " ");
                }
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 读取[04 Input Registers 3x]类型 模拟量数据
     *
     * @param slaveId  slaveId
     * @param offset   位置
     * @param dataType 数据类型,来自com.serotonin.modbus4j.code.DataType
     * @return 返回结果
     */
    public Number readInputRegisters(ModbusMaster master,int slaveId, int offset, int dataType) {
        try {
            if (master != null) {
                // 04 Input Registers类型数据读取
                BaseLocator<Number> loc = BaseLocator.inputRegister(slaveId, offset, dataType);
                Number value = master.getValue(loc);
                return value;
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 公共方法，可重载，将返回的数据入库，对于每一种功能码，设备Id返回的数据需要存储的表可能是不一致的。
     */
    protected void Save() {

    }

    /**
     * 公共方法，可重载，将返回的数据推到前台
     */
    protected void Send() {

    }
}
