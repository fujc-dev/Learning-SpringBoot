package com.zc58s.springbootinfdemo.jna.response;

import com.zc58s.springbootinfdemo.jna.response.base.ResponseBase;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/24 16:23
 */
public class SearchFileResponse extends ResponseBase {
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
