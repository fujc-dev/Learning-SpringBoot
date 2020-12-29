package com.zc58s.springbootjpa.db;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/*
 * springboot-database
 * com.zc58s.springbootdatabase.db
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/29 11:02
 */
@Component
public class DataSourceShow implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        DataSource datasource = applicationContext.getBean(DataSource.class);
        System.out.println("=======================================");
        System.out.println(datasource.getClass().getName());
        System.out.println("=======================================");
    }
}
