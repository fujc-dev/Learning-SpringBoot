package com.zc58s.springmvcdemo.service.impl;

import com.zc58s.springmvcdemo.dao.UserRepository;
import com.zc58s.springmvcdemo.entity.User;
import com.zc58s.springmvcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/30 9:52
 * springboot-mybatis
 * com.zc58s.springmvcdemo.service
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {

        this.repository = repository;
    }

    @Override
    public User getUser(Long id) {
        return repository.getUser(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public int insertUser(User user) {
        return repository.insertUser(user);
    }
}
