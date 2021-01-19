package com.zc58s.springbootdatabaselock.tasks;

import com.zc58s.springbootdatabaselock.service.ClearDistributedLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/19 11:25
 */
@Component
public class DistributedLockTask {

    private final ClearDistributedLockService eliminateService;

    @Autowired
    public DistributedLockTask(
            ClearDistributedLockService eliminateService
    ) {
        this.eliminateService = eliminateService;
    }


    @Scheduled(cron = "0/5 * *  * * ? ")   //每5秒执行一次
    public void clear() {
        this.eliminateService.clear();
    }
}
