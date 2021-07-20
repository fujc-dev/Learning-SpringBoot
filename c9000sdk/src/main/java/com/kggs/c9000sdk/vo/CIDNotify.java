package com.kggs.c9000sdk.vo;

import com.kggs.c9000sdk.vo.base.VoBase;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/20 14:36
 */
public class CIDNotify extends VoBase {
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
