package com.zc58s.springbootdatabase.service.impl;

import com.zc58s.springbootdatabase.dao.PayeelistBankexecutRepository;
import com.zc58s.springbootdatabase.service.PayeelistBankexecutService;
import com.zc58s.springbootdatabase.vo.IPackageIndexAndExecuteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 15:29
 * springboot-database
 * com.zc58s.springbootdatabase.service.impl
 */
@Service
public class PayeelistBankexecutServiceImpl  implements PayeelistBankexecutService {

    @Autowired
    private PayeelistBankexecutRepository payeelistBankexecutRepository;

    @Override
    public List<IPackageIndexAndExecuteList> findPackageIndexAndExecuteList() {
        return payeelistBankexecutRepository.findPackageIndexAndExecuteList();
    }
}
