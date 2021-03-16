package com.zc58s.springiocdemo.pojo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/*
 * springioc-demo
 * com.zc58s.springiocdemo.pojo
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/25 11:00
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ScopeBean {
    private int id = 1;

    public ScopeBean() {
        System.out.println("初始化加载");
    }
}
