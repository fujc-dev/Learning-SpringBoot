package com.zc58s.springbootinfdemo.jna.exception;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/28 11:28
 */
public class LoginException extends RuntimeException {

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }
}
