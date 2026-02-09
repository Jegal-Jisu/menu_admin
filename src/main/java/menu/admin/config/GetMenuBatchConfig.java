package menu.admin.config;

import menu.admin.batch.GetMenuBatch;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class GetMenuBatchConfig {
    @Bean
    public Job getMenuJob(JobRepository jobRepository, Step loadStep, Step crowlingStep) {
        return new JobBuilder("getMenuJob", jobRepository)
                .start(loadStep)
                .next(crowlingStep)
                .build();
    }

    @Bean
    public Step loadStep(JobRepository jobRepository, PlatformTransactionManager tx, @Qualifier("TargetLoadTasklet") Tasklet targetLoadTasklet) {
        return new StepBuilder("loadStep", jobRepository)
                .tasklet(targetLoadTasklet, tx)
                .build();
    }

    @Bean
    public Step crowlingStep(JobRepository jobRepository, PlatformTransactionManager tx, @Qualifier("CrawlingTasklet") Tasklet crawlingTasklet) {
        return new StepBuilder("crowlingStep", jobRepository)
                .tasklet(crawlingTasklet, tx)
                .build();
    }
}
