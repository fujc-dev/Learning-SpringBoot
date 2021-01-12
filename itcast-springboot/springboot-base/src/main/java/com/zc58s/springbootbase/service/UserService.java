package com.zc58s.springbootbase.service;

import com.zc58s.springbootbase.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 用户服务接口
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/12 10:43
 */
public interface UserService {
    /**
     * @param id
     * @return
     */
    User findUserById(String id);



    /**
     * 根据用户名查询，并分页显示
     *
     * @param username
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<User> findUsersByUsername(String username, int pageIndex, int pageSize);
}
