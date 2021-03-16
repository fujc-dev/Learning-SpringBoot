package com.zc58s.springaopdemo.controller;

import com.zc58s.springaopdemo.pojo.User;
import com.zc58s.springaopdemo.service.UserService;
import com.zc58s.springaopdemo.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * springaop-demo
 * com.zc58s.springaopdemo.controller
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/29 9:32
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(Long id, String username, String node) {
        User user = new User(id, username, node);
        service.printUser(user);
        return user;
    }


    /**
     * @param id
     * @param username
     * @param node
     * @return
     */
    @RequestMapping("/vp")
    @ResponseBody
    public User validatorAndPrint(Long id, String username, String node) {
        User user = new User(id, username, node);
        UserValidator validator = (UserValidator) service;
        if (validator.validator(user)) {
            service.printUser(user);
        }
        return user;
    }
}
