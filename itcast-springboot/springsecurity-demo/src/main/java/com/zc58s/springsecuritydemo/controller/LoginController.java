package com.zc58s.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/8 9:42
 * springsecurity-demo
 * com.zc58s.springsecuritydemo.controller
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        //这边我们,默认是返到templates下的login.html
        return "views/login";
    }
}
