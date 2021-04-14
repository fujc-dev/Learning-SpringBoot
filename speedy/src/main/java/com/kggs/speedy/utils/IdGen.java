package com.kggs.speedy.utils;

import java.util.UUID;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/14 11:50
 */
public class IdGen {
    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}