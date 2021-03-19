package com.kggs.modbus4jgateway.factory;

import com.kggs.modbus4jgateway.bean.SlaveWrite;
import com.kggs.modbus4jgateway.factory.service.WriteSalveFactory;
import com.kggs.modbus4jgateway.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

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
        WriteSalveFactory salveFactory =  SpringContextUtil.getBean(WriteSalveFactory.class);
        if (salveFactory != null) {
            salveFactory.Write(writeValue);
        }
    }
}
