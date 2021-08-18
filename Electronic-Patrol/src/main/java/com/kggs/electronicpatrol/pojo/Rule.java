package com.kggs.electronicpatrol.pojo;

import java.util.Date;

/**
 * 过滤规则，
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/18 11:03
 */
public class Rule {

    //先定义一个规则，表示数据必须界限与开始时间与结束时间内，为有效数据
    /**
     * 开始时间，时间格式为xx:xx
     */
    private String beginDate;
    /**
     * 结束时间，时间格式为xx:xx
     */
    private String endDate;


    public Rule(String beginDate, String endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
