package com.zc58s.springbootmybatis.controller;

import com.zc58s.springbootmybatis.enumeration.SexEnum;
import com.zc58s.springbootmybatis.pojo.User;
import com.zc58s.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/30 9:50
 * springboot-mybatis
 * com.zc58s.springbootmybatis.controller
 */
@Controller
@RequestMapping("/mybatis")
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {

        this.userService = userService;
    }

    @RequestMapping("/getuser")
    @ResponseBody
    public User getUser() {
        return this.userService.getUser(1L);
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public Map<String, Object> insertUser() {
        User user = new User();
        user.setUsername(UUID.randomUUID().toString().toUpperCase());
        user.setSex(SexEnum.FEMALE);
        user.setNote("有些事情不努力下都不知道什么是绝望");
        int update = this.userService.insertUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("", update == 1);
        result.put("data", user);
        return result;
    }
}
