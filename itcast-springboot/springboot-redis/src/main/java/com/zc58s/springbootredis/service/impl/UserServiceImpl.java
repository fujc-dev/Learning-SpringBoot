package com.zc58s.springbootredis.service.impl;

import com.zc58s.springbootredis.dao.UserRepository;
import com.zc58s.springbootredis.pojo.User;
import com.zc58s.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/30 9:52
 * springboot-mybatis
 * com.zc58s.springbootredis.service
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {

        this.repository = repository;
    }

    @Override
    @Cacheable(value = "redis-cache", key = "'redis_user_'+#id")
    public User getUser(Long id) {
        return repository.get(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    @CachePut(value = "redis-cache", key = "'redis_user_'+#result.id")
    public int insertUser(User user) {
        return repository.insert(user);
    }

    @Override
    @CachePut(value = "redis-cache", condition = "#result != null ", key = "'redis_user_'+#user.id")
    public int updateUser(User user) {
        return repository.update(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public List<User> findUsers(String username, String node) {
        return repository.find(username, node);
    }

    @Override
    @CacheEvict(value = "redis-cache", key = "'redis_user_'+#id", beforeInvocation = false)
    public int deleteUser(Long id) {
        return repository.delete(id);
    }
}
