package com.kggs.bluecardsdk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/17 10:43
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @ResponseBody
    @RequestMapping("/send")
    public Map<String, Object> send() {
        Map<String, Object> maps = new HashMap<>();
        maps.put("status", false);
        return maps;
    }
}
