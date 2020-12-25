package com.zc58s.springiocdemo.pojo.definition.impl;


import com.zc58s.springiocdemo.pojo.definition.Animal;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/*
 * springboot-basic
 * com.zc58s.springbootbasic.pojo.definition.impl
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/24 16:12
 */

@Primary
@Component
public class Dog implements Animal {
    @Override
    public void use() {
        System.out.println("狗【" + Dog.class.getSimpleName() + "】是可以来看门的");
    }
}
