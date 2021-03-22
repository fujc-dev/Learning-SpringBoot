package com.kggs.modbus4jgateway.factory;

import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.service.WriteSalveFactory;
import com.kggs.modbus4jgateway.service.IModbus4jWriteService;
import com.kggs.modbus4jgateway.utils.SpringContextUtil;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 15:16
 */
public class WriteHelper {


    /**
     * 基于modbus4j写ModbusSalve的全局的写方法
     *
     * @param writeValue
     * @param <T>
     */
    public static <T> void Write(SlaveWrite<T> writeValue) {
        WriteSalveFactory salveFactory = SpringContextUtil.getBean(WriteSalveFactory.class);
        if (salveFactory != null) {
            try {
                salveFactory.Write(writeValue);
            } catch (ErrorResponseException e) {
                e.printStackTrace();
            } catch (ModbusTransportException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写 [01 Coil Status(0x)]写一个 function ID = 5
     *
     * @param slaveId
     * @param offset
     * @param writeValue
     */
    @Deprecated
    public static void Write(int slaveId, int offset, boolean writeValue) throws ModbusTransportException {
        IModbus4jWriteService writeService = SpringContextUtil.getBean(IModbus4jWriteService.class);
        if (writeService != null) {
            writeService.WriteCoil(slaveId, offset, writeValue);
        }
    }

    /**
     * 写[01 Coil Status(0x)] 写多个 function ID = 15
     *
     * @param slaveId
     * @param offset
     * @param writeValue
     */
    @Deprecated
    public static void Write(int slaveId, int offset, boolean[] writeValue) throws ModbusTransportException {
        IModbus4jWriteService writeService = SpringContextUtil.getBean(IModbus4jWriteService.class);
        if (writeService != null) {
            writeService.WriteCoils(slaveId, offset, writeValue);
        }
    }

    /**
     * 写[03 Holding Register(4x)] 写一个 function ID = 6
     *
     * @param slaveId
     * @param offset
     * @param writeValue
     */
    @Deprecated
    public static void Write(int slaveId, int offset, short writeValue) throws ModbusTransportException {
        IModbus4jWriteService writeService = SpringContextUtil.getBean(IModbus4jWriteService.class);
        if (writeService != null) {
            writeService.WriteRegister(slaveId, offset, writeValue);
        }
    }

    /**
     * 写入[03 Holding Register(4x)]写多个 function ID=16
     *
     * @param slaveId
     * @param offset
     * @param sdata
     */
    @Deprecated
    public static void Write(int slaveId, int offset, short[] sdata) throws ModbusTransportException {
        IModbus4jWriteService writeService = SpringContextUtil.getBean(IModbus4jWriteService.class);
        if (writeService != null) {
            writeService.WriteRegisters(slaveId, offset, sdata);
        }
    }

    /**
     * 写入数字类型的模拟量（如:写入Float类型的模拟量、Double类型模拟量、整数类型Short、Integer、Long）
     *
     * @param slaveId
     * @param offset
     * @param value
     * @param dataType
     */
    @Deprecated
    public static void Write(int slaveId, int offset, Number value, int dataType) throws ModbusTransportException, ErrorResponseException {
        IModbus4jWriteService writeService = SpringContextUtil.getBean(IModbus4jWriteService.class);
        if (writeService != null) {
            writeService.WriteHoldingRegister(slaveId, offset, value, dataType);
        }
    }
}
