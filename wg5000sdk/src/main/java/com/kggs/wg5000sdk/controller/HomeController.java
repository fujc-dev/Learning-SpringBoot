package com.kggs.wg5000sdk.controller;


import com.kggs.wg5000sdk.factory.WG5000Factory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @ResponseBody
    @RequestMapping("/open")
    public Map<String, Object> RemoteDoorOpening(
            @RequestParam("server_ip") String serverIp,
            @RequestParam("user") String userName,
            @RequestParam("pwd") String passWord,
            @RequestParam("door_name") String doorName) {
        System.out.println("=====================Remote Door Opening Begin=====================");
        Map<String, Object> map = new HashMap<>();
        try {
            boolean _execute_status = WG5000Factory.Open(serverIp, userName, passWord, doorName);
            map.put("status", _execute_status);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
        }
        System.out.println("=====================Remote Door Opening End=====================\r\n");
        return map;
    }

}
