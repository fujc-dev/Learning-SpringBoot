package com.zc58s.springbootjpa.service;

import com.zc58s.springbootjpa.vo.PackageIndexAndExecuteList;

import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 14:15
 * springboot-database
 * com.zc58s.springbootdatabase.service
 */
public interface PayeelistBankexecutService {
    //获得文章列表
    List<PackageIndexAndExecuteList> findPackageIndexAndExecuteList();
}
