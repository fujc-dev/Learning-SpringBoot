package com.kggs.modbus4jgateway.config;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.IpParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 10:18
 */
@Configuration
public class ModbusConfig {

    @Value("${spring.modbus.ip}")
    public String ip;


    @Bean
    public ModbusMaster BuilderModbusMaster() {
        try {
            ModbusFactory modbusFactory = new ModbusFactory();
            IpParameters params = new IpParameters();
            params.setHost(ip);
            params.setPort(502);
            ModbusMaster master = modbusFactory.createTcpMaster(params, true);// TCP 协议
            master.init();
            return master;
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
        return null;
    }
}
