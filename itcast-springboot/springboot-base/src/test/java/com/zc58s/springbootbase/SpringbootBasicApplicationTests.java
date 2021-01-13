package com.zc58s.springbootbase;

import com.zc58s.springbootbase.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class SpringbootBasicApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {

        String sqlStr = "SELECT id,username,password FROM t_user ";

        List<User> userList = jdbcTemplate.query(sqlStr, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        System.out.println("JdbcTemplate访问MySql数据库成功");
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }

    @Test
    void testRedis() {
        //增：key:name，value:fujc
        stringRedisTemplate.opsForValue().set("name", "fujc");
        String value = (String) stringRedisTemplate.opsForValue().get("name");
        System.out.println(value);
        //删除
        stringRedisTemplate.delete("name");
        //更新（覆盖），此时也算是新增
        stringRedisTemplate.opsForValue().set("name","fjc");
        //查询
         value = (String) stringRedisTemplate.opsForValue().get("name");
        System.out.println(value);
    }

}
