package com.kggs.springbootbatchdemo.config.decider;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * 决策器
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/5 10:18
 */
public class MyDecider implements JobExecutionDecider {
    private int count;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        this.count++;
        if (count % 2 == 0) {
            return new FlowExecutionStatus("1");
        }
        return new FlowExecutionStatus("2");
    }
}
