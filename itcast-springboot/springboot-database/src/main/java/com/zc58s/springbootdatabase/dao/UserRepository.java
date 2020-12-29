package com.zc58s.springbootdatabase.dao;

import com.zc58s.springbootdatabase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 使用方法命名的方式实现对数据库的查询
 * <p>以动词find/get开始，而以by代表按照上面内容进行查询；</p>
 * <p>
 * 例如：</p>
 * <p>getUserById：方法就是通过主键(id)对用户进行查询的，这样JPA就会根据方法命名生成SQL来查询数据库；</p>
 * <p>findByUsernameLike：方法是命名规则则多了一个like，它代表着采用模糊查询，也就是使用like关键字进行查询；</p>
 * <p>findByUsernameLikeOrNoteLike：这样的命名，则涉及到两个条件，一个是用户名username，另一个则是备注note，
 * 他们都采用了like，因此会执行模糊查询，而他们之间采用连接词or，所以SQL的生成也会添加Or。</p>
 *
 * <p>
 * findByUsernameLike 与 findUserByUsernameLike 等价
 * </p>
 * <p>
 * findByUsernameLikeOrNoteLike 与 findUserByUsernameLikeOrNoteLike等价
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @create time : 2020/12/29 13:14
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 不需要写脚本，按用户名模糊查询
     *
     * @param username
     * @return
     */
    List<User> findByUsernameLike(String username);

    List<User> findUserByUsernameLike(String username);

    /**
     * 根据用户名或者备注进行模糊查询
     *
     * @param username
     * @param note
     * @return
     */
    List<User> findUserByUsernameLikeOrNoteLike(String username, String note);

    List<User> findByUsernameLikeOrNoteLike(String username, String note);

    /**
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * @param id
     * @return
     */
    User getUserById(Long id);

}
