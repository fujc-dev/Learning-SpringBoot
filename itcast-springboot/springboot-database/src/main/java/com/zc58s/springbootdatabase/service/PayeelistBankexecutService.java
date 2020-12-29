package com.zc58s.springbootdatabase.service;

import com.zc58s.springbootdatabase.vo.IPackageIndexAndExecuteList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 14:15
 * springboot-database
 * com.zc58s.springbootdatabase.service
 *
 */
public interface PayeelistBankexecutService {
    //获得文章列表
    List<IPackageIndexAndExecuteList> findPackageIndexAndExecuteList();
}
