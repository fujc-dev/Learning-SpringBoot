package com.zc58s.springbootjpa.dao;

import com.zc58s.springbootjpa.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 15:59
 * springboot-database
 * com.zc58s.springbootdatabase.dao
 */
@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {
}
