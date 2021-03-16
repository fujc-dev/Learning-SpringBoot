package com.zc58s.springbootjpa.controller;

import com.zc58s.springbootjpa.service.PayeelistBankexecutService;
import com.zc58s.springbootjpa.vo.PackageIndexAndExecuteList;
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
    public List<PackageIndexAndExecuteList> findPackageIndexAndExecuteList(String state) {

        List<PackageIndexAndExecuteList> packageIndexAndExecuteList = payeelistBankexecutService.findPackageIndexAndExecuteList();
        return packageIndexAndExecuteList;
    }
}
