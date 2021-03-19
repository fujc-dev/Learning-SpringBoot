package com.kggs.modbus4jgateway.bean;

import com.serotonin.modbus4j.code.DataType;

/**
 * Modbus Slave点位数据模型
 * <p>
 * 需要配置好点位地址以及数据类型。
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 10:35
 */
public class SlavePoint {

    /**
     * 寄存器的起始地址
     */
    private int offset = 0;
    /**
     * 数据类型，从Modbus过来的数据的类型，默认是有符号的整型，读模拟量时有效
     */
    private Integer dataType;

    /**
     * 构建Modbus Slave点位数据，包含短Int类型的Modbus数据类型，
     *
     * @param offset
     */
    public SlavePoint(int offset) {
        this.offset = offset;
        this.dataType = DataType.TWO_BYTE_INT_SIGNED; //默认读的是2位的Int值
    }

    /**
     * 构建Modbus Slave点位数据
     *
     * @param offset   访问寄存器的起始地址，起始地址，起始地址
     * @param dataType 数据类型，返回的数据类型
     */
    public SlavePoint(int offset, Integer dataType) {
        this.offset = offset;
        this.dataType = dataType;
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
}
