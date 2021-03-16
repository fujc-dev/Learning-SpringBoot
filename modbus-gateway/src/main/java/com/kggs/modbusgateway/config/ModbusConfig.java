package com.kggs.modbusgateway.config;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Modbus Slave配置
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/12 11:07
 */
@Configuration
public class ModbusConfig {
    @Value("${spring.modbus.ip}")
    public String ip;


    @Bean
    public ModbusMaster BuilderModbusMaster() {
        try {
            //构建Modbus Tcp连接参数
            TcpParameters parameters = new TcpParameters();
            InetAddress address = null;
            address = InetAddress.getByName(ip);
            parameters.setHost(address);
            parameters.setKeepAlive(true);
            parameters.setPort(Modbus.TCP_PORT);
            Modbus.setAutoIncrementTransactionId(true);
            return ModbusMasterFactory.createModbusMasterTCP((parameters));
        } catch (
                UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
