package com.zc58s.springiocdemo.pojo.definition.impl;


import com.zc58s.springiocdemo.pojo.definition.Animal;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/*
 * springboot-basic
 * com.zc58s.springbootbasic.pojo.definition.impl
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/24 16:26
 */
@Component
@Primary
public class Cat implements Animal {
    @Override
    public void use() {
        System.out.println("猫咪【" + Cat.class.getSimpleName() + "】是抓老鼠的");
    }
}
