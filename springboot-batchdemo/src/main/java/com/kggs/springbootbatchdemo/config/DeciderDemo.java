package com.kggs.springbootbatchdemo.config;

import com.kggs.springbootbatchdemo.config.decider.MyDecider;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 决策器，当Step1执行结束的时候，才能进入下一个步骤，在简单的条件时，我们使用on。
 * 但是当复杂条件时，on就不能满足条件，所有框架引入了决策器。
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/5 10:11
 */
@Configuration
@EnableBatchProcessing
public class DeciderDemo {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DeciderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step CreateDeciderStep1() {
        return this.stepBuilderFactory.get("CreateDeciderStep1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("CreateDeciderStep1");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step CreateDeciderStep2() {
        return this.stepBuilderFactory.get("CreateDeciderStep2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("CreateDeciderStep2");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step CreateDeciderStep3() {
        return this.stepBuilderFactory.get("CreateDeciderStep3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("CreateDeciderStep3");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }


    @Bean
    public JobExecutionDecider CurrentExecutionDecider() {
        return new MyDecider();
    }

    @Bean
    public Job CreateDeciderDemoJob() {
        return this.jobBuilderFactory.get("CreateDeciderDemoJob")
                .start(CreateDeciderStep1())
                .next(CurrentExecutionDecider())
                .from(CurrentExecutionDecider()).on("1").to(CreateDeciderStep2())
                .from(CurrentExecutionDecider()).on("2").to(CreateDeciderStep3())
                .from(CreateDeciderStep3()).on("*").to(CurrentExecutionDecider()) //* 表示CreateDeciderStep3无论返回什么值，将继续再执行异常决策器
                .end()
                .build();
    }

}
