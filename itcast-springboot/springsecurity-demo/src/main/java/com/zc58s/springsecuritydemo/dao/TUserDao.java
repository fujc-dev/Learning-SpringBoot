package com.zc58s.springsecuritydemo.dao;

import com.zc58s.springsecuritydemo.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-08 08:49:43
 */
@Mapper
@Repository
public interface TUserDao {


    /**
     * 通过用户名查询角色信息
     *
     * @param username 用户名
     * @return
     */
    TUser findByUsername(String username);

}