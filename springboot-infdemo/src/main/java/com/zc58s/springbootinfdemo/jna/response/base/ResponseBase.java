package com.zc58s.springbootinfdemo.jna.response.base;


import com.zc58s.springbootinfdemo.jna.base.Sdk;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/27 15:00
 */
public class ResponseBase {

    /**
     * 执行结果
     */
    private boolean status = false;
    private int code = Sdk.CODE_ERROR_INVALID_PARA;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        //当状态为true时，增加一个默认消息提示，操作成功
        if (status) {
            this.setCode(Sdk.CODE_SUCCESS);
        }
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
