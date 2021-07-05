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

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/5 9:42
 */
@Configuration
@EnableBatchProcessing
public class FlowDemo {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public FlowDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }
    @Bean
    public Step CreateStep1() {
        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("CreateStep1");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
    @Bean
    public Step CreateStep2() {
        return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("CreateStep2");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
    @Bean
    public Step CreateStep3() {
        return stepBuilderFactory.get("step3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("CreateStep3");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    /**
     * 创建Flow对象，指明Flow对象包含那些Step
     *
     * @return
     */
    @Bean
    public Flow CreateFlow() {
        return new FlowBuilder<Flow>("CreateFlow1")
                .start(CreateStep1()).next(CreateStep2()).build();
    }

    /**
     * @return
     */
    @Bean
    public Job FlowDemoCreateJob() {
        return jobBuilderFactory.get("FlowDemoCreateJob").start(CreateFlow()).next(CreateStep3()).end().build();
    }
}
