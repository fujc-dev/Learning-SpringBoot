package com.zc58s.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/7 13:40
 * springsecurity-demo
 * com.zc58s.springsecuritydemo.controller
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("msg", "SpringBoot With Freemark hello world!");
        return "views/index";
    }

    @RequestMapping(value = "/welcome")

    public String home() {
        return "views/welcome";
    }
}
