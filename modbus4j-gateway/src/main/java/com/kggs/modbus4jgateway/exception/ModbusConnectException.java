package com.kggs.modbus4jgateway.exception;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 13:00
 */
public class ModbusConnectException extends Exception {

    public ModbusConnectException(String message) {
        super(message);
    }

    public ModbusConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModbusConnectException(Throwable cause) {
        super(cause);
    }
}
