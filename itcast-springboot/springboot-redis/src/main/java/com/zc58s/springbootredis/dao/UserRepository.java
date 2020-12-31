package com.zc58s.springbootredis.dao;

import com.zc58s.springbootredis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/30 9:36
 * springboot-mybatis
 * com.zc58s.springbootredis.dao
 */
@Mapper
@Repository
public interface UserRepository {

    User getUser(Long id);

    int insertUser(User user);
}
