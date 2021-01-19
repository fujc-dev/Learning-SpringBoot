package com.zc58s.springbootdatabaselock.dao;

import com.zc58s.springbootdatabaselock.entity.MstLock;
import com.zc58s.springbootdatabaselock.keys.LockKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/18 11:44
 */
@Repository
public interface DatabaseLockRepository extends JpaRepository<MstLock, LockKey> {

}
