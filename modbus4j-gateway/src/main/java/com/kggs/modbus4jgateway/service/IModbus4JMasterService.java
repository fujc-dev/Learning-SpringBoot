package com.kggs.modbus4jgateway.service;

import com.kggs.modbus4jgateway.bean.Master;

import java.util.List;

/**
 * 多网关启动ModbusMaster
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/6/24 10:28
 */
public interface IModbus4JMasterService {

    /**
     * 启动多网关ModbusMaster
     *
     * @param masters
     */
    void Start(List<Master> masters);
}
