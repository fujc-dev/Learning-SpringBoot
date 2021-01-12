package com.zc58s.springbootbase.controller;

import com.zc58s.springbootbase.entity.User;
import com.zc58s.springbootbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/11 11:45
 * springboot-base
 * com.zc58s.springbootbase.controller
 */
@Controller
public class IndexController {

    @Value("${simple.name}")
    private String name;
    @Value("${simple.username}")
    private String username;
    @Value("${simple.password}")
    private String password;


    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/get")
    @ResponseBody
    public Map<String, Object> get() {
        //
        System.out.println(name);
        System.out.println(password);
        System.out.println(username);

        System.out.println("application.properties");

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("name", name);
        map.put("username", username);
        map.put("password", password);
        return map;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public Map<String, Object> getUser() {
        User u = this.userService.findUserById("1");
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", u);
        return map;
    }

}
