package com.kggs.c9000sdk.vo;

import com.kggs.c9000sdk.vo.base.NotifyBase;

/**
 * CID码详细信息对象模型
 * //TODO 这个不知意义在哪里，我看说明文档中也有详细的定义，这个属于上层业务层面的，不知道怎么跑到接口中1去了
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 14:36
 */
public class CIDNotify extends NotifyBase {
    private String cidcode;
    private int cidlevel;
    private String cidtype;
    private String cidmemo;

    public String getCidcode() {
        return cidcode;
    }

    public void setCidcode(String cidcode) {
        this.cidcode = cidcode;
    }

    public int getCidlevel() {
        return cidlevel;
    }

    public void setCidlevel(int cidlevel) {
        this.cidlevel = cidlevel;
    }

    public String getCidtype() {
        return cidtype;
    }

    public void setCidtype(String cidtype) {
        this.cidtype = cidtype;
    }

    public String getCidmemo() {
        return cidmemo;
    }

    public void setCidmemo(String cidmemo) {
        this.cidmemo = cidmemo;
    }

    @Override
    public String toString() {
        return "CIDNotify{" +
                "cidcode='" + cidcode + '\'' +
                ", cidlevel=" + cidlevel +
                ", cidtype='" + cidtype + '\'' +
                ", cidmemo='" + cidmemo + '\'' +
                '}';
    }
}
