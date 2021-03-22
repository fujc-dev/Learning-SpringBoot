package com.kggs.modbus4jgateway.bean;

import com.serotonin.modbus4j.code.DataType;

/**
 * 写Modbus点位值数据模型
 *
 * <p>
 * 1、从机地址<br/>
 * 2、寄存器地址<br/>
 * 3、写入的值的数据类型 <br/>
 * 4、具体写入的值<br/>
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 14:55
 */
public class SlaveWrite<T> {
    /**
     * Modbus  Salve地址
     */
    private int slaveId = 1;

    /**
     * 寄存器的起始地址
     */
    private int offset = 0;

    /**
     * 数据类型，仅用于写模拟量时有效
     */
    private Integer dataType;

    private T val;

    public SlaveWrite(int slaveId, int offset, T val) {
        this.slaveId = slaveId;
        this.offset = offset;
        this.val = val;
        this.dataType = -1; //在写线圈状态、写单个保持寄存器时，不需要传数据类型
    }

    public SlaveWrite(int slaveId, int offset, T val, Integer dataType) {
        this.slaveId = slaveId;
        this.offset = offset;
        this.val = val;
        this.dataType = dataType; //
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

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }
}


