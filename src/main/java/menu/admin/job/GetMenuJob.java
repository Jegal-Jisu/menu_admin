package menu.admin.job;


import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetMenuJob extends QuartzJobBean {
    private final JobOperator jobOperator;
    private final Job job;

    public GetMenuJob(JobOperator jobOperator, Job job) {
        this.jobOperator = jobOperator;
        this.job = job;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().getName(); // JobDetail에서 잡 이름을 가져옴
        try {
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
            jobOperator.start(job, jobParameters);
        } catch (Exception e) {
            log.error("fail to execute job : {}, {}", jobName, this.getClass().getSimpleName(), e);
            throw new RuntimeException(e);
        }
    }
}
