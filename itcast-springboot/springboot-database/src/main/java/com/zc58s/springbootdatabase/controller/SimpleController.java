package com.zc58s.springbootdatabase.controller;

import com.zc58s.springbootdatabase.service.PayeelistBankexecutService;
import com.zc58s.springbootdatabase.vo.IPackageIndexAndExecuteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 15:30
 * springboot-database
 * com.zc58s.springbootdatabase.controller
 */
@Controller
@RequestMapping("/simple")
public class SimpleController {

    @Autowired
    PayeelistBankexecutService payeelistBankexecutService;
    @RequestMapping(value = "/findPackageIndexAndExecuteList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<IPackageIndexAndExecuteList> findPackageIndexAndExecuteList(String state) {

        List<IPackageIndexAndExecuteList> packageIndexAndExecuteList = payeelistBankexecutService.findPackageIndexAndExecuteList();
        return packageIndexAndExecuteList;
    }
}
