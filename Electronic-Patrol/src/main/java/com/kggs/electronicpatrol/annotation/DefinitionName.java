package com.kggs.electronicpatrol.annotation;

import java.lang.annotation.*;

/**
 * 定义名称注解，用于控制读取Excel列
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/17 17:19
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface DefinitionName {

    /**
     * 列所在的索引，如果是合并列，那么取第一索引值
     *
     * @return
     */
    String cellIndex() default "A";

    /**
     * 列名
     *
     * @return
     */
    String cellName() default "";

    /**
     * 数据类型
     *
     * @return
     */
    Class dataType() default String.class;

}
