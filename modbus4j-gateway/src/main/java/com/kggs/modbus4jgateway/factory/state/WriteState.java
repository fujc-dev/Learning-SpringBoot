package com.kggs.modbus4jgateway.factory.state;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/3/19 16:29
 */
public abstract class WriteState {

    public abstract void Push(Context c);

    public abstract void Pull(Context c);

    public abstract void Write();
}
