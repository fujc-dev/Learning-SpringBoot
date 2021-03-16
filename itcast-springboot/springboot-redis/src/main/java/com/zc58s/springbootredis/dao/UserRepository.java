package com.zc58s.springbootredis.dao;

import com.zc58s.springbootredis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/30 9:36
 * springboot-mybatis
 * com.zc58s.springbootredis.dao
 */
@Mapper
@Repository
public interface UserRepository {

    /**
     * 根据id获取单个用户
     *
     * @param id
     * @return
     */
    User get(Long id);

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 查询用户
     * <p>
     * 制定MyBatis的参数名称
     * </p>
     *
     * @param username
     * @param node
     * @return
     */
    List<User> find(String username, String node);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int delete(Long id);
}
