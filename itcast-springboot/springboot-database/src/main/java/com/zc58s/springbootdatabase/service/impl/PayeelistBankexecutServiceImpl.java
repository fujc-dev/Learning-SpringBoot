package com.zc58s.springbootdatabase.service.impl;

import com.zc58s.springbootdatabase.dao.PayeelistBankexecutRepository;
import com.zc58s.springbootdatabase.service.PayeelistBankexecutService;
import com.zc58s.springbootdatabase.vo.PackageIndexAndExecuteList;
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
public class PayeelistBankexecutServiceImpl implements PayeelistBankexecutService {

    private final PayeelistBankexecutRepository payeelistBankexecutRepository;

    @Autowired
    public PayeelistBankexecutServiceImpl(PayeelistBankexecutRepository payeelistBankexecutRepository) {
        this.payeelistBankexecutRepository = payeelistBankexecutRepository;
    }

    @Override
    public List<PackageIndexAndExecuteList> findPackageIndexAndExecuteList() {
        return payeelistBankexecutRepository.findPackageIndexAndExecuteList();
    }
}
