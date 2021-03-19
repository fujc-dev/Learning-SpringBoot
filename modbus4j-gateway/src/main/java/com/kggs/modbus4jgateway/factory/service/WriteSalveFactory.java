package com.kggs.modbus4jgateway.factory.service;

import com.kggs.modbus4jgateway.bean.SlaveWrite;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 14:01
 */
public abstract class WriteSalveFactory {

    public abstract <T> void Write(SlaveWrite<T> writeValue);

}
