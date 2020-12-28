package com.zc58s.springaopdemo.service.impl;

import com.zc58s.springaopdemo.pojo.User;
import com.zc58s.springaopdemo.service.UserService;
import org.springframework.stereotype.Service;

/*
 * springaop-demo
 * com.zc58s.springaopdemo.service.impl
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/28 17:52
 */
//
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void printUser(User user) {
        if (user == null) {
            throw new RuntimeException("检测用户参数是否为空");
        }
        System.out.print("id=" + user.getId());
        System.out.print("\tusername=" + user.getUsername());
        System.out.println("\tnode=" + user.getNode());
    }
}
