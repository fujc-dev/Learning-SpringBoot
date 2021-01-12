package com.zc58s.springbootbase.controller;

import com.zc58s.springbootbase.entity.User;
import com.zc58s.springbootbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/12 14:31
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/test")
    public String test(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user";
    }
}
