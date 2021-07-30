package com.kggs.c9000sdk.controller;

import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import com.kggs.c9000sdk.service.ICsstLHB9000ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/27 16:02
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    private final ICsstLHB9000ClientService csstLHB9000ClientService;

    @Autowired
    public HomeController(ICsstLHB9000ClientService csstLHB9000ClientService) {
        this.csstLHB9000ClientService = csstLHB9000ClientService;
    }

    @ResponseBody
    @RequestMapping("/csst_place")
    public Map<String, Object> csst_lhb9000_client_operate_place(String szIP, int nPlaceType, int nAreaNo) {
        System.out.println("csst_lhb9000_client_operate_place Begin");
        Map<String, Object> map = new HashMap<>();
        try {
            boolean _exec_result = csstLHB9000ClientService.OperatePlace(szIP, nPlaceType, nAreaNo);
            map.put("status", _exec_result);
        } catch (CsstLHB9000Exception e) {
            e.printStackTrace();
            map.put("status", false);
        }
        System.out.println("csst_lhb9000_client_operate_place End");
        return map;
    }

    @ResponseBody
    @RequestMapping("/csst_remove")
    public Map<String, Object> csst_lhb9000_client_operate_remove(String szIP, int nRemoveType, int nAreaNo) {
        System.out.println("csst_lhb9000_client_operate_remove Begin");
        Map<String, Object> map = new HashMap<>();
        try {
            boolean _exec_result = csstLHB9000ClientService.OperateRemove(szIP, nRemoveType, nAreaNo);
            map.put("status", _exec_result);
            map.put("status", _exec_result);
        } catch (CsstLHB9000Exception e) {
            e.printStackTrace();
            map.put("status", false);
        }
        System.out.println("csst_lhb9000_client_operate_remove End");
        return map;
    }
}
