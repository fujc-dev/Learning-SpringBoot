package com.kggs.modbusgateway.bean;

/**
 * 这个对象是在数据库维护的Modbus Slave地址
 *
 * <p>
 * <p>
 * 目前这个{@link Slave}未包含功能码，默认使用的是04，Input
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/11 16:56
 */
public class Slave {

    /**
     * Modbus  Salve地址
     */
    private int slaveId = 1;

    /**
     * 寄存器的起始地址
     */
    private int offset = 0;

    /**
     * 寄存器数量
     */
    private int quantity = 10;

    /**
     * 这其中有涉及到线圈、离散输入、保持、输入四种寄存器
     * 0x01: 读线圈寄存器
     * 0x02: 读离散输入寄存器
     * 0x03: 读保持寄存器
     * 0x04: 读输入寄存器
     */
    private int code = 0x04;


    public Slave(int slaveId, int offset, int quantity) {
        this.slaveId = slaveId;
        this.offset = offset;
        this.quantity = quantity;
        this.code = 0x04; //读输入寄存器
    }

    public Slave(int slaveId, int offset, int quantity, int code) {

        this(slaveId, offset, quantity);
        this.code = code;
    }


    public int getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(int slaveId) {
        this.slaveId = slaveId;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

