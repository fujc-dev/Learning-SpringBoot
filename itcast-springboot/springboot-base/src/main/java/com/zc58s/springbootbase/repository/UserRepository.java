package com.zc58s.springbootbase.repository;

import com.zc58s.springbootbase.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * springboot-base
 * com.zc58s.springbootbase.repository
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/12 10:35
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * <p>
     * {@link Pageable}：这是一个分页接口，查询时只需要传入一个{@link Pageable}接口的实现，
     * *指定pageNumber和pageSize即可。
     * *pageNumber：第几页
     * *pageSize：每页显示大小
     * </p>
     * <p>
     * {@link Page}：分页查询结果会封装到该类中，Page接口继承Slice接口，我们可以通过调用
     * getTotalPages和getContents等方法可以很方便的获取总页数和查询结论等。
     * </p>
     *
     * @return
     */
    Page<User> findAllByUsername(String username, Pageable pageable);
}
