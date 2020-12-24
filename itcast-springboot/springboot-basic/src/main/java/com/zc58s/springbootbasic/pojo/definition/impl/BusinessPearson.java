package com.zc58s.springbootbasic.pojo.definition.impl;

import com.zc58s.springbootbasic.pojo.definition.Animal;
import com.zc58s.springbootbasic.pojo.definition.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 * springboot-basic
 * com.zc58s.springbootbasic.pojo.definition.impl
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/24 16:10
 */
@Component
public class BusinessPearson implements Person {

    Animal animal = null;

    /**
     * 避免需要注入的对象没有被注入，使用构造函数强制注入，来保证类可以正常运行。
     * <p>
     * 在使用set方式时，这是一种选择注入，可有可无（方法掉没有掉成功无法被保证），即使没有注入这个依赖，那么也不会影响整个类的运行。
     * 在使用构造器方式时已经显式注明必须强制注入。通过强制指明依赖注入来保证这个类的运行。
     * </p>
     *
     * @param animal
     */
    @Autowired
    public BusinessPearson(@Qualifier("cat") Animal animal) {
        this.animal = animal;
    }

    @Override
    public void service() {
        this.animal.use();
    }


}
