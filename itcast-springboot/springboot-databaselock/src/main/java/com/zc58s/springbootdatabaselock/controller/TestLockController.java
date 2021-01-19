package com.zc58s.springbootdatabaselock.controller;

import com.zc58s.springbootdatabaselock.keys.LockKey;
import com.zc58s.springbootdatabaselock.service.DistributedLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/18 17:57
 */
@Controller
public class TestLockController {

    private final DistributedLockService lockService;

    @Autowired
    public TestLockController(DistributedLockService lockService) {
        this.lockService = lockService;
    }


    /**
     * 测试分布式数据库锁
     *
     * @return
     */
    @RequestMapping("/databaseLock")
    @ResponseBody
    public Map<String, Object> databaseLock() throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
        boolean unState = false;
        LockKey lockKey = new LockKey("updatestock", "updatestock");
        boolean state = lockService.tryLock(lockKey);
        map.put("lock", state);
        if (state) {
            //执行业务，执行完毕，删除锁
            //unState = lockService.unLock(lockKey);
           //map.put("unLock", unState);
        }
        Thread.sleep(1000000);
        map.put("success", true);
        System.out.println(" ----> 数据库加锁：" + state + "，解锁：" + unState + "");
        return map;
    }
}
