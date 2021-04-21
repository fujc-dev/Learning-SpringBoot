package com.zc58s.springbootinfdemo.jna.service.business.infPtz.bean;

/**
 *  英飞拓视频设拍照对象模型
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/20 15:26
 */
public class InfImage {

    /**
     * 视频拍照存储地址
     */
    private  String path;
    /**
     * 当前拍照是否执行成功
     */
    private  Boolean flag;

    public InfImage(String path, Boolean flag) {
        this.path = path;
        this.flag = flag;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
