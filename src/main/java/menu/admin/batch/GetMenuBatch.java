package menu.admin.batch;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class GetMenuBatch {

    @Qualifier("TargetLoadTasklet")
    @Component
    public class TargetLoadTasklet implements Tasklet {
        @Override
        public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            chunkContext.getStepContext()
                    .getStepExecution()
                    .getJobExecution()
                    .getExecutionContext()
                    .put("jobName", this.getClass().getSimpleName());
            System.out.println("TargetLoadTasklet.execute");
            return RepeatStatus.FINISHED;
        }
    }

    @Qualifier("CrawlingTasklet")
    @Component
    public class CrawlingTasklet implements Tasklet {
        @Override
        public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            String test = (String) chunkContext.getStepContext()
                    .getJobExecutionContext()
                    .get("jobName");
            System.out.println("CrawlingTasklet.execute");
            System.out.println("test = " + test);

            return RepeatStatus.FINISHED;
        }
    }
}
