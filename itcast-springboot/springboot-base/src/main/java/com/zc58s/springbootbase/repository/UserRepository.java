package com.zc58s.springbootbase.repository;

import com.zc58s.springbootbase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * springboot-base
 * com.zc58s.springbootbase.repository
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/12 10:35
 */
public interface UserRepository extends JpaRepository<User, String> {
}
