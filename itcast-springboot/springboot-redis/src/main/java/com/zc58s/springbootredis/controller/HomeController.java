package com.zc58s.springbootredis.controller;

import com.zc58s.springbootredis.enumeration.SexEnum;
import com.zc58s.springbootredis.pojo.User;
import com.zc58s.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/30 9:50
 * springboot-mybatis
 * com.zc58s.springbootredis.controller
 */
@Controller
@RequestMapping("/mybatis")
public class HomeController {

    private final UserService userService;

    private final RedisTemplate redisTemplate;

    @Autowired
    public HomeController(UserService userService, RedisTemplate redisTemplate) {

        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping("/get")
    @ResponseBody
    public User getUser() {
        return this.userService.getUser(1L);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insertUser() {
        User user = new User();
        user.setUsername(UUID.randomUUID().toString().toUpperCase());
        user.setSex(SexEnum.FEMALE);
        user.setNote("有些事情不努力下都不知道什么是绝望");
        int update = this.userService.insertUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("success", update == 1);
        result.put("data", user);
        return result;
    }


    @RequestMapping("/multi")
    @ResponseBody
    public Map<String, Object> multi() {

        this.redisTemplate.opsForValue().set("key1", UUID.randomUUID().toString().toUpperCase());
        List list = (List) this.redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch("key1");
                operations.multi();
                operations.opsForValue().increment("key1",1); //

                operations.opsForValue().set("key2", UUID.randomUUID().toString().toUpperCase());
                Object value2 = operations.opsForValue().get("key2");
                System.out.println("命令在队列，所以value值未null【" + value2 + "】");

                operations.opsForValue().set("key3", UUID.randomUUID().toString().toUpperCase());
                Object value3 = operations.opsForValue().get("key3");
                System.out.println("命令在队列，所以value值未null【" + value3 + "】");
                //执行exec命令
                //在提交事务的时候，如果被监听的key1被修改，则不执行事务，否则执行事务
                //另外，如本例中，有3出新增与修改，某一处出现错误，不会影响其他两处，所以在使用redis事务时，一定要在业务中先进行判断其对错。
                return operations.exec();
            }
        });
        //执行事务结果：[true,"27CAE76F-65F8-48D5-91CF-B9DEEE93CC7E",true,"C17185B2-9A8D-4CA3-BDF2-B3697DCBCFDE"]
        //不执行事务结果：[]
        System.out.println(list);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", list);
        return result;
    }
}
