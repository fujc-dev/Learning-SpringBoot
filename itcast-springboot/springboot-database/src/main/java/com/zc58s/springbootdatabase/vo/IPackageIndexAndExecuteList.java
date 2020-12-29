package com.zc58s.springbootdatabase.vo;

import java.math.BigDecimal;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 15:05
 * springboot-database
 * com.zc58s.springbootdatabase.vo
 */
public interface IPackageIndexAndExecuteList {
    //以下3个get方法存储主表对应数据
    BigDecimal getMoneySum();

    BigDecimal getTotalIty();

    String getPayerAccount();

    //以下2个get方法存储子表对应数据
    String getPackageId();

    BigDecimal getMoney();
}
