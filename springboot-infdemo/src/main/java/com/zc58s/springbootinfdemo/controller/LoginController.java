package com.zc58s.springbootinfdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/4 14:50
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     *
     * @param szUrl 服务器地址
     * @param szUser 用户名
     * @param szPassword 密码
     * @return 登录结果
     */
    @ResponseBody
    @RequestMapping("/in")
    public Map<String, Object> in(String szUrl, String szUser, String szPassword) {
        Map<String, Object> map = new HashMap<>();

        return map;
    }
}
