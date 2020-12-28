package com.zc58s.springbootproxy.proxy;

import com.zc58s.springbootproxy.service.UserInterface;
import com.zc58s.springbootproxy.service.impl.User;

/*
 * springboot-proxy
 * com.zc58s.springbootproxy.proxy
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/28 10:09
 */
// 接口和真实角色对象就用上面代码
// 代理类，实现UserInterface接口
public class UserProxy implements UserInterface {
    // 持有真实角色对象
    private User user = new User();
    @Override
    public void sayHello() {
        System.out.println("invoking start....");
        // 在代理对象的sayHello()里调用真实角色的sayHello()
        user.sayHello();
        System.out.println("invoking stop....");
    }
}
