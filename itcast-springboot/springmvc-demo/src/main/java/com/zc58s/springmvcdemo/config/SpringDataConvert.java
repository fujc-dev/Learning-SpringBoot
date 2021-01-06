package com.zc58s.springmvcdemo.config;

import com.zc58s.springmvcdemo.converter.StringToUserRequestConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/6 17:20
 * springmvc-demo
 * com.zc58s.springmvcdemo.config
 */
//@Configuration
public class SpringDataConvert {


    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Autowired
    public SpringDataConvert(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    @Bean
    protected void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();

            //添加转换OrderDetail 集合
            genericConversionService.addConverter(new StringToUserRequestConverter());

        }
    }
}
