package com.zc58s.springbootdatabase.controller;

import com.zc58s.springbootdatabase.dao.UserRepository;
import com.zc58s.springbootdatabase.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * springboot-database
 * com.zc58s.springbootdatabase.controller
 *
 * @author      : fjc.dane@gmail.com
 * @createtime : 2020/12/29 13:15
 */
@Controller
@RequestMapping("/jpa")
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {

        this.repository = repository;
    }

    @RequestMapping("/get_user")
    @ResponseBody
    public User getUser(Long id) {
        User user = repository.findById(id).get();
        return user;
    }

    @RequestMapping("/get_user_by_username")
    @ResponseBody
    public List<User> getUser() {
        List<User> users = repository.findUserByUsernameLike("傅均承");
        return users;
    }

}
