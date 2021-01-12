package com.zc58s.springbootbase.service.impl;

import com.zc58s.springbootbase.entity.User;
import com.zc58s.springbootbase.repository.UserRepository;
import com.zc58s.springbootbase.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * springboot-base
 * com.zc58s.springbootbase.service.impl
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/12 10:43
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository repository;

    @Override
    public User findUserById(String id) {
        return repository.findById(id).get();
    }
}
