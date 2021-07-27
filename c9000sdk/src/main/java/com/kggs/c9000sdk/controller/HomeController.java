package com.kggs.c9000sdk.controller;

import com.kggs.c9000sdk.Bundle;
import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/csst_place")
    public Map<String, Object> csst_lhb9000_client_operate_place(String szIP, int nMachine, int nPlaceType, int nAreaNo) {
        Map<String, Object> map = new HashMap<>();
        try {
            Bundle.csst_lhb9000_client_operate_place(szIP, nMachine, nPlaceType, nAreaNo);
            map.put("status", true);
        } catch (CsstLHB9000Exception e) {
            e.printStackTrace();
            map.put("status", false);
        }
        map.put("status", true);
        return map;
    }

    @RequestMapping("/csst_remove")
    public Map<String, Object> csst_lhb9000_client_operate_remove(String szIP, int nMachine, int nRemoveType, int nAreaNo) {
        Map<String, Object> map = new HashMap<>();
        try {
            Bundle.csst_lhb9000_client_operate_remove(szIP, nMachine, nRemoveType, nAreaNo);
            map.put("status", true);
        } catch (CsstLHB9000Exception e) {
            e.printStackTrace();
            map.put("status", false);
        }
        return map;
    }
}
