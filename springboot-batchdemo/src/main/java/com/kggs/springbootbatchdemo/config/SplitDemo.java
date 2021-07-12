package com.kggs.springbootbatchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Split多Flow并行执行
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/5 9:59
 */
@Configuration
@EnableBatchProcessing
public class SplitDemo {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public SplitDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step CreateSplitDemoStep1() {
        return this.stepBuilderFactory.get("CreateSplitDemoStep1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("CreateSplitDemoStep1");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step CreateSplitDemoStep2() {
        return this.stepBuilderFactory.get("CreateSplitDemoStep2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("CreateSplitDemoStep2");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step CreateSplitDemoStep3() {
        return this.stepBuilderFactory.get("CreateSplitDemoStep3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("CreateSplitDemoStep3");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }


    @Bean
    public Flow CreateFlowDemo1() {
        return new FlowBuilder<Flow>("CreateFlowDemo1").start(CreateSplitDemoStep1()).build();
    }

    @Bean
    public Flow CreateFlowDemo2() {
        return new FlowBuilder<Flow>("CreateFlowDemo2").start(CreateSplitDemoStep2()).next(CreateSplitDemoStep3()).build();
    }

    @Bean
    public Job CreateSplitDemoJob() {
        return this.jobBuilderFactory.get("CreateSplitDemoJob")
                .start(CreateFlowDemo1()).split(new SimpleAsyncTaskExecutor())
                .add(CreateFlowDemo2()).end().build();
    }
}
