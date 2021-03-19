package com.kggs.modbus4jgateway.strategy.service.impl;

import com.kggs.modbus4jgateway.strategy.service.Strategy;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 10:03
 */

public abstract class StrategyBase implements Strategy {
    @Autowired
    private final ModbusMaster master;

    protected StrategyBase() {
        master = null;
    }


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
    protected Boolean readCoilStatus(int slaveId, int offset) {
        try {
            if (this.master != null) {
                // 01 Coil Status
                BaseLocator<Boolean> loc = BaseLocator.coilStatus(slaveId, offset);
                Boolean value = this.master.getValue(loc);
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
    public Boolean readInputStatus(int slaveId, int offset) {
        try {
            if (this.master != null) {
                // 02 Input Status
                BaseLocator<Boolean> loc = BaseLocator.inputStatus(slaveId, offset);
                Boolean value = this.master.getValue(loc);
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
     * @throws ModbusTransportException 异常
     * @throws ErrorResponseException   异常
     * @throws ModbusInitException      异常
     */
    public Number readHoldingRegister(int slaveId, int offset, int dataType) {
        try {
            if (this.master != null) {
                // 03 Holding Register类型数据读取
                BaseLocator<Number> loc = BaseLocator.holdingRegister(slaveId, offset, dataType);
                Number value = this.master.getValue(loc);
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
     * 读取[04 Input Registers 3x]类型 模拟量数据
     *
     * @param slaveId  slaveId
     * @param offset   位置
     * @param dataType 数据类型,来自com.serotonin.modbus4j.code.DataType
     * @return 返回结果
     * @throws ModbusTransportException 异常
     * @throws ErrorResponseException   异常
     * @throws ModbusInitException      异常
     */
    public Number readInputRegisters(int slaveId, int offset, int dataType) {
        try {
            if (this.master != null) {
                // 04 Input Registers类型数据读取
                BaseLocator<Number> loc = BaseLocator.inputRegister(slaveId, offset, dataType);
                Number value = this.master.getValue(loc);
                return value;
            }
        }  catch (ModbusTransportException e) {
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
