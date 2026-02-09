package menu.admin.config;

import menu.admin.job.GetMenuJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetMenuQuartzConfig {
    @Value("${crontab.getMenu}")
    private String crontab;

    @Bean
    public JobDetail getMenuJobDetail() {
        return JobBuilder.newJob(GetMenuJob.class)
                .withIdentity("getMenuJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger getMenuJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(getMenuJobDetail())
                .withIdentity("getMenuBatchTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(crontab))
                .build();
    }
}
