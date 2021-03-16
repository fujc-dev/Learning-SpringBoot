package com.kggs.modbusgateway;

import com.kggs.modbusgateway.bean.Slave;
import com.kggs.modbusgateway.service.IModbusSlaveService;
import com.kggs.modbusgateway.service.impl.ModbusSlaveServiceImp;
import com.kggs.modbusgateway.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GatewayApplication {


    public static void main(String[] args) {

        SpringApplication.run(GatewayApplication.class, args);

    }

}
