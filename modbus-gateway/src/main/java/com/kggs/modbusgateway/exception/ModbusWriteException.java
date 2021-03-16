package com.kggs.modbusgateway.exception;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 12:54
 */
public class ModbusWriteException extends Exception {
    public ModbusWriteException(String message) {
        super(message);
    }

    public ModbusWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModbusWriteException(Throwable cause) {
        super(cause);
    }
}
