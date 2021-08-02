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
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("door_name") String doorName) {
        System.out.println("=====================Remote Door Opening Begin=====================");
        Map<String, Object> map = new HashMap<>();
        try {
            WG5000Factory.Open(serverIp, 60006, username, password, doorName);
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
        }
        System.out.println("=====================Remote Door Opening End=====================\r\n");
        return map;
    }

}
