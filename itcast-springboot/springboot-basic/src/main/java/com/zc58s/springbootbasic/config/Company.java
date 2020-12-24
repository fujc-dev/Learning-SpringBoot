package com.zc58s.springbootbasic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
 * springboot-basic
 * com.zc58s.springbootbasic.pojo
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/24 14:59
 */

/**
 * @{@link Component}是标明那个类被扫描进入SpringIoc容器，并且默认是单例模式的。
 */
@Component
public class Company {
    /**
     * 注入的时候包含{@link Value}注解后，我在AppConfig中写了Bean注解，这个值居然不会被改变。
     * <p>
     * //TODO 为什么会这样子？这个Spring Ioc容器的机制吗?
     * </p>
     */
    @Value("四川旷谷信息工程有限公司")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
