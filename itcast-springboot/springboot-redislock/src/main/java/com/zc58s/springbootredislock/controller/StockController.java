package com.zc58s.springbootredislock.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/14 9:40
 */
@Controller
@RequestMapping("/stock")
public class StockController {

    private final StringRedisTemplate redisTemplate;

    private final Redisson redisson;

    @Autowired
    public StockController(StringRedisTemplate redisTemplate, Redisson redisson) {
        this.redisTemplate = redisTemplate;
        this.redisson = redisson;
    }


    /**
     * 常规操作
     * <p>使用Spring自带的Redis框架来实现，lettuce实现的</p>
     * <b><i>问题分析</i></b>
     * <p>
     * 在并发处理时，就目前代码，假如同一时刻，我的库存为1，好几个用户同时访问了该方法，那么他们此时获取的库存都为1，
     * 并且执行了，减库存操作。这就会出现我们常说的超卖问题。
     * </p>
     *
     * <b><i>优化改造</i></b>
     * <p>
     * 这种情况我们首先想到的就是排队（队列）。我们所有的请求都进入队列里面，在队列里面一个一个的执行。
     * </p>
     * <p>
     * redis是天然的队列，使用redis的分布式锁，来优化程序。redis的SETNX就是分布式锁的关键。
     * </p>
     * <b><i>设想一下，改造后还有那些问题？</i></b>
     * <p>
     * 在超时时间端内，我们的业务没有执行完毕（减库存那一步还执行），那么其他用户来获取锁之后，
     * 获取是库存于上一个用户是一致的，那么此时，还是会出现超卖问题。
     * </p>
     * <b><i>超时解决方案</i></b>
     * <p>
     * 在我的任务没有执行完毕，那么我需要为我的任务自动为这个redis锁延迟过期时间。
     * </p>
     *
     * @return
     */
    @RequestMapping("/default")
    @ResponseBody
    public String defaultDeductStock() {
        //优化：在redis里面增加一个公共锁，所有的用户，都需要排队取锁，只有获取到锁的用户，才能操作库存。
        //1、创建一个锁key
        String lockKey = "redis-stock-lock";
        //2、创建一个锁的value，可能在真实业务中是用户的用户名或者授权码之类的
        String lockValue = UUID.randomUUID().toString().toLowerCase();
        //3、通过我们利用Redis的SETNX枷锁，设置一个超时时间为30s的锁，就是说，每个用户有30秒的时间去操作库存
        // Boolean setIfAbsent("redis-lock","randomUUID",30, TimeUnit.SECONDS);
        //每个用户30秒的时间才操作库存，30s之后，自动释放锁。
        //其他用户可以来获取这个锁。
        //这里设置超时锁，也有一个隐患，就是我的业务，在30s内未完成，锁就被别人拿走；
        //我后面的操作就不是原子性了。
        //就下面这段代码来讲，30秒肯定是能执行完毕的。
        try {
            Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 500, TimeUnit.MILLISECONDS);
            //4、检测用户是否拿到锁了，拿到锁之后，进行库存操作
            if (result) {
                //模拟获取库存业务，获取库存，我们默认在redis 服务器里面放置了1000的库存
                int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
                //我在查询库存之后，正好做了一些操作，使得我设置的锁的超时时间过期了。
                Thread.sleep(1000);
                //当商品还有库存时，我们执行-1，认为商品已经卖出了
                if (stock > 0) {
                    int realStock = stock - 1;
                    //更新库存
                    redisTemplate.opsForValue().set("stock", realStock + "");
                    System.out.println(realStock);
                } else {
                    System.out.println("扣减失败，库存不足");
                }
            } else {
                return "请稍后，网络延迟";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //执行完毕之后，我们要删除锁，为了保证锁的正常删除，我们可以将锁删除，放置在finally中
            if (lockValue.equals(redisTemplate.opsForValue().get(lockKey))) {
                redisTemplate.delete(lockKey);
            }
        }
        return "success";
    }

    /**
     * 基于Redisson实现的分布式锁
     *
     * @return
     */
    @RequestMapping("/deductStockByRedisson")
    @ResponseBody
    public String defaultDeductStockByRedisson() {
        String lockKey = "redis-lock";
        RLock redissonLock = redisson.getLock(lockKey);//（1）拿一个Redisson的锁对象
        try {
            redissonLock.lock();//（2）加锁，底层默认设置超时时间为30s
            //模拟获取库存业务，获取库存，我们默认在redis 服务器里面放置了1000的库存
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            //我在查询库存之后，正好做了一些操作，使得我设置的锁的超时时间过期了。
            //当商品还有库存时，我们执行-1，认为商品已经卖出了
            if (stock > 0) {
                int realStock = stock - 1;
                //更新库存
                redisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println(realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            //执行完毕之后，我们要删除锁，为了保证锁的正常删除，我们可以将锁删除，放置在finally中
            redissonLock.unlock();//（4）释放锁
        }
        return "success";
    }
}
