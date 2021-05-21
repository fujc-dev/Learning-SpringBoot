package com.zc58s.springbootinfdemo.jna.sdk;

import com.sun.jna.IntegerType;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/21 14:12
 */
public class UnsignedLong extends IntegerType {
    public UnsignedLong() {
        super(8, true);
    }
}
