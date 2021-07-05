package com.kggs.springbootbatchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Batch 配置对象
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/5 7:03
 */
@Configuration
@EnableBatchProcessing
public class JobDemo {


    private  JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    public JobDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    /**
     * 默认情况下，会按照Step1、Step2、Step3依次执行
     */
    @Bean
    public Job JobDemoCreateJob() {
        return jobBuilderFactory.get("JobDemoCreateJob")
                //.start(Step1())
                //.next(Step2())
                //.next(Step3())
                //.build();
                .start(JobDemoStep1())
                .on("COMPLETED").to(JobDemoStep2())
                .from(JobDemoStep2()).on("COMPLETED").to(JobDemoStep3())
                .from(JobDemoStep3()).end()
                .build();

    }


    @Bean
    public Step JobDemoStep1() {
        return stepBuilderFactory.get("JobDemoStep1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("JobDemoStep1");
                //步骤
                return RepeatStatus.FINISHED;
            }
        }).build();
    }


    private Step JobDemoStep2() {
        return stepBuilderFactory.get("JobDemoStep2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("JobDemoStep2");
                //步骤
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    private Step JobDemoStep3() {
        return stepBuilderFactory.get("JobDemoStep3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("JobDemoStep3");
                //步骤
                return RepeatStatus.FINISHED;
            }
        }).build();

    }
}
