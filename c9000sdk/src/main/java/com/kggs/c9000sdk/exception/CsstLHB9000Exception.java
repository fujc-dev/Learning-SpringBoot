package com.kggs.c9000sdk.exception;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 15:33
 */
public class CsstLHB9000Exception extends Exception {

    //无参构造方法
    public CsstLHB9000Exception() {
        super();
    }

    //有参的构造方法
    public CsstLHB9000Exception(String message) {
        super(message);
    }

    // 用指定的详细信息和原因构造一个新的异常
    public CsstLHB9000Exception(String message, Throwable cause) {
        super(message, cause);
    }

    //用指定原因构造一个新的异常
    public CsstLHB9000Exception(Throwable cause) {
        super(cause);
    }
}
