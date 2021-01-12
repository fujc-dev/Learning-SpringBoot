package com.zc58s.springbootbase.service.impl;

import com.zc58s.springbootbase.entity.User;
import com.zc58s.springbootbase.repository.UserRepository;
import com.zc58s.springbootbase.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private UserRepository repository;

    @Override
    public User findUserById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public Page<User> findUsersByUsername(String username, int pageIndex, int pageSize) {
        //Sort sort = Sort.by(Sort.Direction.DESC, "createTime"); //创建时间降序排序
        Pageable pageable = PageRequest.of(0, 20);
        return repository.findAllByUsername(username, pageable);
    }
}
