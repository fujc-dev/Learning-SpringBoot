package com.kggs.modbus4jgateway.bean;

import com.serotonin.modbus4j.code.DataType;

import java.util.ArrayList;
import java.util.List;

/**
 * Modbus Slave数据模型
 * <p>
 * 1、SalveId
 * 2、点位所在的索引地址
 * 3、点位
 *
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 10:12
 */
public class Slave {
    /**
     * Modbus  Salve地址
     */
    private int slaveId = 1;


    /**
     * 这其中有涉及到线圈、离散输入、保持、输入四种寄存器
     * 0x01: 读线圈寄存器
     * 0x02: 读离散输入寄存器
     * 0x03: 读保持寄存器
     * 0x04: 读输入寄存器
     */
    private int code = 0x04;


    /**
     * Modbus Slave点位对象
     */
    private List<SlavePoint> points = new ArrayList<>();


    /**
     * 构建{@link Slave}对象
     *
     * @param slaveId 从机地址
     * @param code    功能码，此处的目的是用于注入
     */
    public Slave(int slaveId, int code) {
        this.slaveId = slaveId;
        this.code = code;
    }

    /**
     * 构建{@link Slave}对象
     *
     * @param slaveId 从机地址
     * @param code    功能码，此处的目的是用于注入
     * @param points  点位数据
     */
    public Slave(int slaveId, int code, List<SlavePoint> points) {
        this.slaveId = slaveId;
        this.code = code;
        this.points = points;
    }

    /**
     * 构建{@link Slave}对象
     *
     * @param slaveId 从机地址
     * @param code    功能码，此处的目的是用于注入
     * @param point  点位数据
     */
    public Slave(int slaveId, int code, SlavePoint point) {
        this.slaveId = slaveId;
        this.code = code;
        this.points.add(point);
    }


    public int getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(int slaveId) {
        this.slaveId = slaveId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<SlavePoint> getPoints() {
        return points;
    }

    public void setPoints(List<SlavePoint> points) {
        this.points = points;
    }
}
