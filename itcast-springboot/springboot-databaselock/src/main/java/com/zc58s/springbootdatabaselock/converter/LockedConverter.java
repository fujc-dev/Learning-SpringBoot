package com.zc58s.springbootdatabaselock.converter;

import com.zc58s.springbootdatabaselock.enums.Locked;

import javax.persistence.AttributeConverter;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/19 16:04
 */
public class LockedConverter implements AttributeConverter<Locked, String> {
    @Override
    public String convertToDatabaseColumn(Locked locked) {
        return locked.getId();
    }

    @Override
    public Locked convertToEntityAttribute(String s) {
        return Locked.getEnumById(s);
    }
}
