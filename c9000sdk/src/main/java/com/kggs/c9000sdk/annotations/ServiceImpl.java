package com.kggs.c9000sdk.annotations;

import java.lang.annotation.*;

/**
 * 标记实现类,
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 14:20
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceImpl {
    Class classz();
}
