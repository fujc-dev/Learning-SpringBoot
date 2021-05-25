package com.zc58s.springbootinfdemo.jna.sdk;

import com.sun.jna.IntegerType;
import com.sun.jna.NativeLong;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/21 14:12
 */
public class UnsignedLong extends NativeLong {
    public UnsignedLong(long value) {
        super(8, true);
        this.setValue(value);
    }
}
