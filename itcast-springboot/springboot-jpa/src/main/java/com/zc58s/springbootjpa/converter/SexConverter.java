package com.zc58s.springbootjpa.converter;

import com.zc58s.springbootjpa.enumeration.SexEnum;

import javax.persistence.AttributeConverter;

/*
 * springboot-database
 * com.zc58s.springbootdatabase.converter
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/29 13:11
 */
public class SexConverter implements AttributeConverter<SexEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer i) {
        return SexEnum.getEnumById(i);
    }
}
