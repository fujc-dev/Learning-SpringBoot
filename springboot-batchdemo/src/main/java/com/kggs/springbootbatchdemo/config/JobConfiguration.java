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
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/5 7:03
 */
@Configuration
@EnableBatchProcessing
public class JobConfiguration {


    private final JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    public JobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job HelloWorld() {
        return jobBuilderFactory.get("HelloWorldJob")
                .start(StepHelloWorld())
                .build();
    }
    @Bean
    public Step StepHelloWorld() {
        return stepBuilderFactory.get("StepHelloWorld").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello Spring Batch");
                //步骤
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
}
