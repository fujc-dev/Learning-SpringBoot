package com.zc58s.springbootjpa.dao;

import com.zc58s.springbootjpa.entity.M2MUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 16:37
 * springboot-database
 * com.zc58s.springbootdatabase.dao
 */
public interface M2MUserRepository extends JpaRepository<M2MUser, Integer> {
}
